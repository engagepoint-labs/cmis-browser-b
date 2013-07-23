package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserDocument;
import com.engagepoint.university.ep2013b.browser.api.BrowserFolder;
import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;

import java.io.Serializable;
import java.util.*;

public class CMISBrowserService implements BrowserService {

    // Unique Service Provider name
    private static final String SERVICE_NAME = "CMIS";
    private Session session;

    public CMISBrowserService() {
        session = connect();
    }

    // Should return SERVICE_NAME
    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    // Non paging methods
    @Override
    public BrowserFolder findFolderById(String id) {
        Folder current = (Folder) session.getObject(id);
        return findFolder(current, 0, 0);
    }

    @Override
    public BrowserFolder findFolderByPath(String path) {
        Folder current = (Folder) session.getObjectByPath(path);
        return findFolder(current, 0, 0);
    }


    // Paging methods
    @Override
    public BrowserFolder findFolderById(String id, int page, int rowCount) {
        Folder current = (Folder) session.getObject(id);
        return findFolder(current, page, rowCount);
    }

    @Override
    public BrowserFolder findFolderByPath(String path, int page, int rowCount) {
        Folder current = (Folder) session.getObjectByPath(path);
        return findFolder(current, page, rowCount);
    }

    @Override
    public String getCurrentLocationById(String id) {
        Folder current = (Folder) session.getObject(id);
        return getCurrentLocation(current);
    }


    private List<BrowserFolder> findParents(Folder current) {
        List<BrowserFolder> parents = new ArrayList<BrowserFolder>();

        // Create all parents folders
        Folder parent;
        BrowserFolder item;

        while (!current.isRootFolder()) {
            parent = current.getFolderParent();

            item = new BrowserFolder();
            item.setId(current.getId());
            item.setName(current.getName());
            parents.add(item);

            current = parent;
        }

        item = new BrowserFolder();
        item.setId(current.getId());
        item.setName(current.getName());

        parents.add(item);

        for (int i = 0; i < parents.size() - 1; ++i) {
            parents.get(i).setParent(parents.get(i + 1));
        }

        return parents;
    }

    private BrowserFolder findFolder(Folder current, int page, int rowCounts) {
        BrowserFolder result;
        List<BrowserFolder> parents = findParents(current);

        // Fill children of each parent folder
        for (BrowserFolder i : parents) {
            ItemIterable<CmisObject> children = current.getChildren();

            // if enabled paging (paging only for selected folder, other parents without)
            if (((page != 0) && (rowCounts != 0)) && (i.equals(parents.get(0)))) {

                // count total pages
                long total = children.getTotalNumItems();
                long rest = total % rowCounts;
                int totalPages = (int) (total - rest) / rowCounts;

                if (rest > 0) totalPages++;

                i.setTotalPages(totalPages);

                // count skipped records
                long skip = (page - 1) * rowCounts;
                children = current.getChildren().skipTo(skip).getPage(rowCounts);
            }

            for (CmisObject o : children) {
                BrowserItem child;
                BrowserFolder subchild;

                // check if already exists (is one of the parents)
                BrowserItem find = new BrowserItem();
                find.setId(o.getId());
                int index = parents.indexOf(find);

                if (index == -1)
                {
                    if (o instanceof Folder)
                    {
                        child = new BrowserFolder();

                        for (CmisObject s : ((Folder) o).getChildren())
                        {
                            subchild = new BrowserFolder();
                            subchild.setId(s.getId());
                            subchild.setName(s.getName());
                            subchild.setParent((BrowserFolder)child);

                            ((BrowserFolder)child).setChild(subchild);
                        }
                    }
                    else
                    {
                        child = new BrowserDocument();
                    }

                    child.setId(o.getId());
                    child.setName(o.getName());
                    child.setParent(i);

                }
                else child = parents.get(index);

                i.setChild(child);
            }

            current = current.getFolderParent();
        }

        result = parents.get(0);
        return result;
    }

    private String getCurrentLocation(Folder current) {
        return current.getPath();
    }

    public Session connect() {
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // ATOM
//        final String url = "http://localhost:8080/server/atom11";
//        parameter.put(SessionParameter.ATOMPUB_URL, url);
//        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

        // WSDL
        final String url = "http://localhost:18080/server/services/";
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.WEBSERVICES.value());
        parameter.put(SessionParameter.WEBSERVICES_ACL_SERVICE, url + "ACLService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE, url + "DiscoveryService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE, url + "MultiFilingService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE, url + "NavigationService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_OBJECT_SERVICE, url + "ObjectService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_POLICY_SERVICE, url + "PolicyService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE, url + "RelationshipService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE, url + "RepositoryService?wsdl");
        parameter.put(SessionParameter.WEBSERVICES_VERSIONING_SERVICE, url + "VersioningService?wsdl");

        Repository repository = sessionFactory.getRepositories(parameter).get(0);
        parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());

        return sessionFactory.createSession(parameter);
    }


    ////  ===============================================   simple search of partial/full name
    public BrowserFolder simpleSearch(String id, String parameter, int pageNum, int rowCounts) {

        BrowserDocument item;
        BrowserFolder result = new BrowserFolder();

        String inDocums =
                "SELECT cmis:objectId, cmis:name FROM cmis:document WHERE IN_FOLDER(?) and cmis:name LIKE ?";


        if (id != null && !id.isEmpty() && parameter != null && !parameter.isEmpty()) {

            QueryStatement query = session.createQueryStatement(inDocums);

            query.setString(1, id);
            query.setStringLike(2, "%" + parameter + "%");


            ItemIterable<QueryResult> results = query.query(false);
            //int rowCounts = 2;
            long skip = 0;

            if (((pageNum != 0) && (rowCounts != 0))) {
                skip = (pageNum - 1) * rowCounts;
            }


            results = results.skipTo(skip).getPage(rowCounts);
            for (QueryResult hit : results)
            {
                item = new BrowserDocument();
                item.setId(hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString());
                item.setName(hit.getPropertyByQueryName("cmis:name").getFirstValue().toString());

                result.setChild(item);
            }

            long total = results.getTotalNumItems();
            long rest = total % rowCounts;
            int totalPages = (int) (total - rest) / rowCounts;

            if ( rest > 0) {
                totalPages++;
            }

            result.setTotalPages(totalPages);

        }  // valid id & parameter string

        return result;
    }



    //// ==========================================   advanced search by several parameters
    ////   Document Type, Date from .. to ... ,  Content Type,  Size,  Contains Text

    public BrowserFolder advancedSearch(String id, Object param, int pageNum, int rowCounts) {

        AdvSearchParams parameter = (AdvSearchParams) param;

        BrowserItem item;
        BrowserFolder result = new BrowserFolder();

        QueryStatement prepQuery = session.createQueryStatement(parameter.createQueryString());


        ItemIterable<QueryResult> results = parameter.setQueryParams(prepQuery).query(false);
        //int rowCounts = 2;
        long skip = 0;

        if (((pageNum != 0) && (rowCounts != 0))) {
            skip = (pageNum - 1) * rowCounts;
        }


        results = results.skipTo(skip).getPage(rowCounts);
        for (QueryResult hit : results) {

            item = new BrowserDocument();
            item.setId(hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString());
            item.setName(hit.getPropertyByQueryName("cmis:name").getFirstValue().toString());

            result.setChild(item);
        }

        long total = results.getTotalNumItems();
        long rest = total % rowCounts;
        int totalPages = (int) (total - rest) / rowCounts;

        if ( rest > 0) {
            totalPages++;
        }

        result.setTotalPages(totalPages);

        return result;
    }

    @Override
    public BrowserFolder createFolder(String id, String name, String type) {

        Map<String, String> folderProps = new HashMap<String, String>();

        folderProps.put(PropertyIds.OBJECT_TYPE_ID, type);
        folderProps.put(PropertyIds.NAME, name);
        folderProps.put(PropertyIds.PARENT_ID, id);

        Folder parent = (Folder) session.getObject(id);

        BrowserFolder result = new BrowserFolder();

        try {
            Folder newFolder = parent.createFolder(folderProps);

            result = new BrowserFolder();
            result.setId(newFolder.getId());
            result.setName(newFolder.getName());

        } catch (CmisBaseException e) {
            // TODO: exception handling task
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public BrowserFolder editFolder(String id, String name, String type) {

        Map<String, String> folderProps = new HashMap<String, String>();

        folderProps.put(PropertyIds.OBJECT_TYPE_ID, type);
        folderProps.put(PropertyIds.NAME, name);
        folderProps.put(PropertyIds.PARENT_ID, id);

        Folder current = (Folder) session.getObject(id);

        BrowserFolder result = new BrowserFolder();

        try {

            current.updateProperties(folderProps);

            result = new BrowserFolder();
            result.setId(current.getId());
            result.setName(current.getName());
        } catch (CmisBaseException e) {
            // TODO: exception handling task
            e.printStackTrace();
        }

        return result;

    }



    @Override
    public void deleteFolder(String id) {

        Folder current = (Folder) session.getObject(id);

        if(current.getId().equals("")){
            return;
        }


        try {
            current.delete();
        } catch (CmisBaseException e) {
            // TODO: exception handling task
            e.printStackTrace();
        }

    }



    @Override
    public Map<String, String> getTypeList(String type) {

        Map<String, String> result = new HashMap<String, String>();

        ItemIterable<ObjectType> typeList = session.getTypeChildren(type, true);

        for (ObjectType tt : typeList) {
            result.put(" - " + tt.getDisplayName(), tt.getId());

            //System.out.println(" = "+tt.getDisplayName()+"  ["+tt.getId()+"]");
            if (tt.getChildren().getTotalNumItems() > 0) {
                for (ObjectType ch : tt.getChildren()) {
                    result.put(" - - - " + ch.getDisplayName(), ch.getId());
                    //System.out.println(" === "+ch.getDisplayName()+"  ["+ch.getId()+"]");
                }

            }

        }
        return result;

    }
}
