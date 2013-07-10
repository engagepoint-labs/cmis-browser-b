package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Non paging methods
    @Override
    public BrowserItem findFolderById(String id)
    {
        Folder current = (Folder)session.getObject(id);
        return findFolder(current, 0, 0);
    }

    @Override
    public BrowserItem findFolderByPath(String path)
    {
        Folder current = (Folder)session.getObjectByPath(path);
        return findFolder(current, 0, 0);
    }


    // Paging methods
    @Override
    public BrowserItem findFolderById(String id, int page, int rowCount)
    {
        Folder current = (Folder)session.getObject(id);
        return findFolder(current, page, rowCount);
    }

    @Override
    public BrowserItem findFolderByPath(String path, int page, int rowCount)
    {
        Folder current = (Folder)session.getObjectByPath(path);
        return findFolder(current, page, rowCount);
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
    public String getCurrentLocationById(String id)
    {
        Folder current = (Folder)session.getObject(id);
        return getCurrentLocation(current);
    }

    private List<BrowserItem> findParents(Folder current)
    {
        List<BrowserItem> parents = new ArrayList<BrowserItem>();

        // Create all parents folders
        Folder parent;
        BrowserItem item;

        while (!current.isRootFolder())
        {
//            parent = current.getParents().get(0);
            parent = current.getFolderParent();

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

    private BrowserItem findFolder(Folder current, int page, int rowCounts)
    {
        BrowserItem result;
        List<BrowserItem> parents = findParents(current);

        // Fill children of each parent folder
        for(BrowserItem i : parents)
        {
            ItemIterable<CmisObject> children = current.getChildren();

            // if enabled paging (paging only for selected folder, other parents without)
            if (((page != 0) && (rowCounts != 0)) && (i.equals(parents.get(0))))
            {
                long skip = (page-1)*rowCounts;
                children = current.getChildren().skipTo(skip).getPage(rowCounts);
            }

            for (CmisObject o : children)
            {
                BrowserItem child, subchild;

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

                    if (o instanceof Folder)
                    {
                        for (CmisObject s : ((Folder) o).getChildren())
                        {
                            subchild = new BrowserItem();
                            subchild.setId(s.getId());
                            subchild.setName(s.getName());
                            subchild.setType((s instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE);
                            subchild.setParent(child);

                            child.setChild(subchild);
                        }
                    }

                }
                else child = parents.get(index);

                i.setChild(child);
            }

            current = current.getFolderParent();
        }

        result = parents.get(0);
        return result;
    }

    private int getTotalPagesFromFolder(Folder current, int rowCounts)
    {
        ItemIterable<CmisObject> children = current.getChildren();

        long total = children.getTotalNumItems();
        int totalPages = Math.round((float)total/ rowCounts);

        return totalPages;
    }

    private String getCurrentLocation(Folder current)
    {
        return current.getPath();
    }

    public Session connect()
    {
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // ATOM
//        final String url = "http://localhost:8080/server/atom11";
//        parameter.put(SessionParameter.ATOMPUB_URL, url);
//        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

        // WSDL
        final String url = "http://localhost:18080/server/services/";
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


    ////   simple search
    public  List<BrowserItem> simpleSearch(String id, String parameter){

        BrowserItem item;
        ArrayList<BrowserItem> browserItems = new ArrayList<BrowserItem>();

        String  inFolders = "SELECT cmis:objectId, cmis:name FROM cmis:folder   WHERE IN_TREE(?) and cmis:name LIKE ?";
        String  inDocums  = "SELECT cmis:objectId, cmis:name FROM cmis:document WHERE IN_TREE(?) and cmis:name LIKE ?";


        if(id != null && !id.isEmpty() && parameter != null && !parameter.isEmpty()){


            QueryStatement qq =  session.createQueryStatement(inDocums);

            qq.setString(1,id);
            qq.setStringLike(2,"%"+parameter+"%");

            System.out.println("query string = " + qq.toQueryString());

            ItemIterable<QueryResult> results = qq.query(false);

            int ii = 1;
            for(QueryResult hit: results) {

                System.out.println("  -------------------------------  result N = " + ii++);


                item = new BrowserItem();
                item.setId(hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString());
                item.setName(hit.getPropertyByQueryName("cmis:name").getFirstValue().toString());
                item.setType(BrowserItem.TYPE.FILE);

                System.out.println("item = "+item);
                browserItems.add(item);

             }


            qq =  session.createQueryStatement(inFolders);

            qq.setString(1,id);
            qq.setStringLike(2,"%"+parameter+"%");

            System.out.println("query string = " + qq.toQueryString());

            results = qq.query(false);


            for(QueryResult hit: results) {

                System.out.println("  -------------------------------  result N = " + ii++);


                item = new BrowserItem();
                item.setId(hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString());
                item.setName(hit.getPropertyByQueryName("cmis:name").getFirstValue().toString());
                item.setType(BrowserItem.TYPE.FOLDER);

                System.out.println("item = "+item);
                browserItems.add(item);

            }

        }  // valid id & parameter string

        return browserItems;
    }
}
