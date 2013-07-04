package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CMISBrowserService  {

    private Session connect(){
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // ATOM
        //final String url = "http://localhost:8080/server/atom11";
        //parameter.put(SessionParameter.ATOMPUB_URL, url);
        //parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

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


    public List<BrowserItem> reachToRootFolder(Folder folder){
        List<BrowserItem> parentList = new ArrayList<BrowserItem>();
        List<BrowserItem> childrenList = new ArrayList<BrowserItem>();
        BrowserItem file;
        BrowserItem parent;
        while (!folder.isRootFolder()){
            ItemIterable<CmisObject> children =folder.getChildren();

            for (CmisObject o : children)
            {
                file = new BrowserItem(o.getId(),o.getName(),
                        (folder instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                        new BrowserItem(folder.getName()));

                childrenList.add(file);

            }

            parent = new BrowserItem(folder.getId(),folder.getName(),
                    (folder instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                    childrenList);
            parentList.add(parent);
            folder = folder.getFolderParent();

        }

        return parentList;
    }


    public BrowserItem findFolderById(String id) {
        List<BrowserItem> findItems=new ArrayList<BrowserItem>();

        Folder folderById =(Folder) connect().getObject(id);
        findItems = reachToRootFolder(folderById);

        for(BrowserItem bi:findItems){
            System.out.println("parent "+ bi.getId()+"   "+bi.getName());
            if(id.equals(bi.getId())){
                return bi;
            }

            else{
                for (BrowserItem chi:bi.getChildren()){
                    System.out.println("children "+ chi.getId()+"   "+chi.getName());
                   if(id.equals(chi.getId())){
                        return chi;
                    }
                }
            }
        }
      return null;
    }


    public BrowserItem findFolderByPath(String path) {
        List<BrowserItem> findItems=new ArrayList<BrowserItem>();

        Folder folderByPath =(Folder) connect().getObject(path);
         String id=folderByPath.getId();
        findItems = reachToRootFolder(folderByPath);

        for(BrowserItem bi:findItems){
            System.out.println("parent "+ bi.getId()+"   "+bi.getName());
            if(id.equals(bi.getId())){
                return bi;
            }

            else{
                for (BrowserItem chi:bi.getChildren()){
                    System.out.println("children "+ chi.getId()+"   "+chi.getName());
                    if(id.equals(chi.getId())){
                        return chi;
                    }
                }
            }
        }
        return null;
    }

}
