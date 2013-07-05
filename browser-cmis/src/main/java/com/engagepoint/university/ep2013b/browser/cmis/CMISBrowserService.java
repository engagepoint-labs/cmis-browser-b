package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import java.util.*;

public class CMISBrowserService implements BrowserService
{
    // Unique Service Provider name
    private static final String SERVICE_NAME = "CMIS";
    private Session session;

    public CMISBrowserService()
    {
        session = connect();
    }

    // Should return SERVICE_NAME
    @Override
    public String getServiceName()
    {
        return SERVICE_NAME;
    }

    @Override
    public int getTotalPagesFromFolderById(String id, int rowCounts)
    {
        Folder current = (Folder)session.getObject(id);
        return getTotalPagesFromFolder(current, rowCounts);
    }

    @Override
    public int getTotalPagesFromFolderByPath(String path, int rowCounts)
    {
        Folder current = (Folder)session.getObjectByPath(path);
        return getTotalPagesFromFolder(current, rowCounts);
    }

    @Override
    public BrowserItem findFolderById(String id, int pagenum, int rowCounts)
    {
        Folder current = (Folder)session.getObject(id);
        return findFolder(current, false, true, pagenum, rowCounts);
    }

    @Override
    public BrowserItem findFolderByPath(String path, int pagenum, int rowCounts)
    {
        Folder current = (Folder)session.getObjectByPath(path);
        return findFolder(current, false, true, pagenum, rowCounts);
    }

    @Override
    public BrowserItem findFolderById(String id, boolean includeOnlyFolders)
    {
        Folder current = (Folder)session.getObject(id);
        return findFolder(current, includeOnlyFolders, false, 0, 0);
    }

    @Override
    public BrowserItem findFolderByPath(String path, boolean includeOnlyFolders)
    {
        Folder current = (Folder)session.getObjectByPath(path);
        return findFolder(current, includeOnlyFolders, false, 0, 0);
    }


    private List<BrowserItem> findParents(Folder current)
    {
        List<BrowserItem> parents = new ArrayList<BrowserItem>();

        // Create all parents folders
        Folder parent;
        BrowserItem item;

        while (!current.isRootFolder())
        {
            parent = current.getParents().get(0);

            item = new BrowserItem();
            item.setId(current.getId());
            item.setName(current.getName());
            item.setType(BrowserItem.TYPE.FOLDER);

            parents.add(item);

            current = parent;
        }

        item = new BrowserItem();
        item.setId(current.getId());
        item.setName(current.getName());
        item.setType(BrowserItem.TYPE.FOLDER);

        parents.add(item);


        for (int i = 0; i < parents.size()-1; ++i)
        {
            parents.get(i).setParent(parents.get(i+1));
        }

        return parents;
    }


    private BrowserItem findFolder(Folder current, boolean includeOnlyFolders, boolean isEnablePaging, int pagenum, int rowCounts)
    {
        BrowserItem result;
        List<BrowserItem> parents = findParents(current);

        // Fill children of each parent folder
        for(BrowserItem i : parents)
        {
            current = (Folder)session.getObject(i.getId());

            ItemIterable<CmisObject> children = current.getChildren();

            // Paging only for selected folder
//            if (i.equals(result) && isEnablePaging)
//            {
//                long skip = (pagenum-1)*rowCounts;
//
//                children = children.skipTo(skip).getPage(rowCounts);
//            }


            for (CmisObject o : children)
            {
//                if (!(o instanceof Folder) && !(i.equals(result))) continue;

                BrowserItem child;

                // check if already exists (is one of the parents)
                BrowserItem find = new BrowserItem();
                find.setId(o.getId());
                int index = parents.indexOf(find);

                if (index == -1)
                {
                    child = new BrowserItem();
                    child.setId(o.getId());
                    child.setName(o.getName());
                    child.setType((o instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE);
                    child.setParent(i);
                }
                else child = parents.get(index);


//                // Sub Childs
                if ((o instanceof Folder))
                {
                    Folder level2 = (Folder)session.getObject(child.getId());
                    ItemIterable<CmisObject> subchildren = level2.getChildren();

                    for (CmisObject s : subchildren)
                    {
                        BrowserItem subchild = new BrowserItem();
                        subchild.setId(s.getId());
                        subchild.setName(s.getName());
                        subchild.setType((s instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE);
                        subchild.setParent(child);

                        child.setChild(subchild);
                    }
                }

                i.setChild(child);
            }
        }

        result = parents.get(0);

//        ..............
//        List<BrowserItem> list = result.getChildren();
//        List<BrowserItem> a = new ArrayList<BrowserItem>();
//        for (BrowserItem i : list)
//        {
//            if (!((i.getType() == BrowserItem.TYPE.FOLDER) &&
//                (i.getChildren().isEmpty()) &&
//                !(i.getName().equals("My_Folder-2-0") &&
//                   !(i.getName().equals("My_Folder-2-1")))))
//            {
//                a.add(i);
//            }
//        }
//
//        result.setChildren(a);
//        ...............


        return result;
    }


    private int getTotalPagesFromFolder(Folder current, int rowCounts)
    {
        ItemIterable<CmisObject> children = current.getChildren();

        long total = children.getTotalNumItems();
        int totalPages = (int)Math.round((float)total/ rowCounts);

        return totalPages;
    }


    public Session connect()
    {
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // WSDL
        final String url = "http://localhost:8080/server/services/";
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.WEBSERVICES.value());
        parameter.put(SessionParameter.WEBSERVICES_ACL_SERVICE,             url+"ACLService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE,       url+"DiscoveryService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE,     url+"MultiFilingService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE,      url+"NavigationService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_OBJECT_SERVICE,          url+"ObjectService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_POLICY_SERVICE,          url+"PolicyService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE,    url+"RelationshipService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE,      url+"RepositoryService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_VERSIONING_SERVICE,      url+"VersioningService?wsdl");

        Repository repository = sessionFactory.getRepositories(parameter).get(0);
        parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());

        return sessionFactory.createSession(parameter);
    }
}
