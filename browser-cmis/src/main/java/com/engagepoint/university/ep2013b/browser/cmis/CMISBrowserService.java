package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public BrowserItem findFolderById(String id) {
        Folder current = (Folder) session.getObject(id);
        return findFolder(current, 0, 0);
    }

    @Override
    public BrowserItem findFolderByPath(String path) {
        Folder current = (Folder) session.getObjectByPath(path);
        return findFolder(current, 0, 0);
    }


    // Paging methods
    @Override
    public BrowserItem findFolderById(String id, int page, int rowCount) {
        Folder current = (Folder) session.getObject(id);
        return findFolder(current, page, rowCount);
    }

    @Override
    public BrowserItem findFolderByPath(String path, int page, int rowCount) {
        Folder current = (Folder) session.getObjectByPath(path);
        return findFolder(current, page, rowCount);
    }

    @Override
    public String getCurrentLocationById(String id) {
        Folder current = (Folder) session.getObject(id);
        return getCurrentLocation(current);
    }


    private List<BrowserItem> findParents(Folder current) {
        List<BrowserItem> parents = new ArrayList<BrowserItem>();

        // Create all parents folders
        Folder parent;
        BrowserItem item;

        while (!current.isRootFolder()) {
            parent = current.getFolderParent();

            item = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);
            parents.add(item);

            current = parent;
        }

        item = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);

        parents.add(item);


        for (int i = 0; i < parents.size() - 1; ++i) {
            parents.get(i).setParent(parents.get(i + 1));
        }

        return parents;
    }

    private BrowserItem findFolder(Folder current, int page, int rowCounts) {
        BrowserItem result;
        List<BrowserItem> parents = findParents(current);

        // Fill children of each parent folder
        for (BrowserItem i : parents) {
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
                BrowserItem child, subchild;

                // check if already exists (is one of the parents)
                BrowserItem find = new BrowserItem();
                find.setId(o.getId());
                int index = parents.indexOf(find);

                if (index == -1) {
                    child = new BrowserItem();
                    child.setId(o.getId());
                    child.setName(o.getName());
                    child.setType((o instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE);
                    child.setParent(i);

                    if (o instanceof Folder) {
                        for (CmisObject s : ((Folder) o).getChildren()) {
                            subchild = new BrowserItem();
                            subchild.setId(s.getId());
                            subchild.setName(s.getName());
                            subchild.setType((s instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE);
                            subchild.setParent(child);

                            child.setChild(subchild);
                        }
                    }

                } else child = parents.get(index);

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
    public BrowserItem simpleSearch(String id, String parameter, int pageNum, int rowCounts) {

        BrowserItem item;
        ArrayList<BrowserItem> browserItems = new ArrayList<BrowserItem>();
        int totalPages = 0;


        String inFolders =
                "SELECT cmis:objectId, cmis:name FROM cmis:folder   WHERE IN_FOLDER(?) and cmis:name LIKE ?";
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


            int ii = 1;
            for (QueryResult hit : results.skipTo(skip).getPage(rowCounts)) {

                item = new BrowserItem(
                        hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString(),
                        hit.getPropertyByQueryName("cmis:name").getFirstValue().toString(),
                        BrowserItem.TYPE.FILE
                );

                browserItems.add(item);

            }

            long total = results.getTotalNumItems();

            long rest = total % rowCounts;

            totalPages = (int) (total - rest) / rowCounts;

            if ( rest > 0) {
                totalPages++;
            }

        }  // valid id & parameter string

        item = new BrowserItem("", BrowserItem.TYPE.FOLDER, null, browserItems, totalPages);

        return item;
    }



    //// ==========================================   advanced search by several parameters
    ////   Document Type, Date from .. to ... ,  Content Type,  Size,  Contains Text

    public BrowserItem advancedSearch(String id, Object param, int pageNum, int rowCounts) {

        AdvSearchParams parameter = (AdvSearchParams) param;

        BrowserItem item;
        ArrayList<BrowserItem> browserItems = new ArrayList<BrowserItem>();
        int totalPages = 0;

        QueryStatement prepQuery = session.createQueryStatement(parameter.createQueryString());


        ItemIterable<QueryResult> results = parameter.setQueryParams(prepQuery).query(false);
        //int rowCounts = 2;
        long skip = 0;

        if (((pageNum != 0) && (rowCounts != 0))) {
            skip = (pageNum - 1) * rowCounts;
        }


        int ii = 1;
        for (QueryResult hit : results.skipTo(skip).getPage(rowCounts)) {

            item = new BrowserItem(
                    hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString(),
                    hit.getPropertyByQueryName("cmis:name").getFirstValue().toString(),
                    BrowserItem.TYPE.FILE
            );

            browserItems.add(item);

        }

        long total = results.getTotalNumItems();


        long rest = total % rowCounts;

        totalPages = (int) (total - rest) / rowCounts;

        if ( rest > 0) {
            totalPages++;
        }

        // valid id & parameter string

        item = new BrowserItem("", BrowserItem.TYPE.FOLDER, null, browserItems, totalPages);

        return item;
    }


    @Override
    public BrowserItem createFolder(String id, String name, String type) throws CmisBaseException {

        Map<String, String> folderProps = new HashMap<String, String>();

        folderProps.put(PropertyIds.OBJECT_TYPE_ID, type);
        folderProps.put(PropertyIds.NAME, name);
        folderProps.put(PropertyIds.PARENT_ID, id);

        Folder parent = (Folder) session.getObject(id);

        Folder newFolder = null;
        BrowserItem result = new BrowserItem();

        newFolder = parent.createFolder(folderProps);
        result = new BrowserItem(newFolder.getId(), newFolder.getName(), BrowserItem.TYPE.FOLDER);

//
//        try {
//            newFolder = parent.createFolder(folderProps);
//            result = new BrowserItem(newFolder.getId(), newFolder.getName(), BrowserItem.TYPE.FOLDER);
//        } catch (CmisBaseException e) {
//            // TODO: exception handling task
//            e.printStackTrace();
//        }

        return result;
    }

    @Override
    public BrowserItem editFolder(String id, String name, String type) throws CmisBaseException {

        Map<String, String> folderProps = new HashMap<String, String>();

        folderProps.put(PropertyIds.OBJECT_TYPE_ID, type);
        folderProps.put(PropertyIds.NAME, name);
        folderProps.put(PropertyIds.PARENT_ID, id);

        Folder current = (Folder) session.getObject(id);

        Folder newFolder = null;
        BrowserItem result = new BrowserItem();

        current.updateProperties(folderProps);
        result = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);

//
//        try {
//
//            current.updateProperties(folderProps);
//            result = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);
//        } catch (CmisBaseException e) {
//            // TODO: exception handling task
//            e.printStackTrace();
//        }

        return result;

    }


    @Override
    public void deleteFolder(String id) throws CmisBaseException {

        Folder current = (Folder) session.getObject(id);

        if(current.getId().equals("")){
            return;
        }

        if(current.getChildren().getTotalNumItems()>0){
            //System.out.println(" find children:  "+current.getName());
            deleteDescendants(current.getChildren());
        }
        current.delete();

    }



    @Override
    public Map<String, String> getTypeList(String type) {

        Map<String, String> result = new HashMap<String, String>();

        ItemIterable<ObjectType> typeList = session.getTypeChildren(type, true);
        roundTrip(typeList,1, result);

        return result;

    }

    private String countStr(String template, int count){

        String result = "";

        for (int i=0;i<count; i++){
            result = template  + result;
        }

        return result;
    }


    private  void  roundTrip(ItemIterable<ObjectType> list,int level, Map<String, String> result){

        String prefix =  countStr(" - ",level);

        for(ObjectType tt : list){
            //System.out.println(""+level+prefix+tt.getDisplayName()+"  ["+tt.getId()+"]");
            result.put(prefix+tt.getDisplayName(), tt.getId());

            if (tt.getChildren().getTotalNumItems() > 0) {
                roundTrip(tt.getChildren(),++level, result);
            }
        }
    }


   private void deleteDescendants  (ItemIterable<CmisObject> list) {

       for(CmisObject tt : list){

           if(tt instanceof Folder){
               if (((Folder) tt).getChildren().getTotalNumItems() > 0) {
                   //System.out.println(" -- find children:  ["+ tt.getId()+"]  "+tt.getName());
                   deleteDescendants(((Folder)tt).getChildren());
               }
               else {
                   System.out.println(" ...  deleting folder: ["+ tt.getId()+"]  "+tt.getName());
                   try{
                   tt.delete();
                   } catch (CmisBaseException e){
                       e.printStackTrace();
                   }

               }

           }else {
               //System.out.println(" ...  deleting document: ["+tt.getProperty(PropertyIds.PARENT_ID)+"]  "+tt.getName());
               try {
                   tt.delete();
               } catch (CmisBaseException e){
                    e.printStackTrace();
               }

           }

           }
   } ////   deleteDescendants




}
