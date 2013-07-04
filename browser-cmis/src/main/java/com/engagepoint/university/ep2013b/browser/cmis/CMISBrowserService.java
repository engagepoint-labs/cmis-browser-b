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


public class CMISBrowserService {

    public Session connect(){
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


    public List<BrowserItem> findFolderById(String id) {

        Folder root =(Folder) connect().getObject(id);
        ItemIterable<CmisObject> children = root.getChildren();

        Folder parent = root.getFolderParent();
        ItemIterable<CmisObject> childrenOfParent = parent.getChildren();

        Folder grandParent = parent.getFolderParent();
        ItemIterable<CmisObject> childrenOfGrandParent = grandParent.getChildren();



        List<BrowserItem> list = new ArrayList<BrowserItem>();
        List<BrowserItem> listForChildren = new ArrayList<BrowserItem>();
        List<BrowserItem> listForParentChildren = new ArrayList<BrowserItem>();
        List<BrowserItem> listForGrandParentChildren = new ArrayList<BrowserItem>();



        BrowserItem fileForList;
        BrowserItem fileForChildren;
        BrowserItem fileForParentChildren;
        BrowserItem fileForGrandParentChildren;


        for (CmisObject o : children) {

            fileForChildren = new BrowserItem(o.getName(),
                    (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                    new BrowserItem(root.getName()));
            listForChildren.add(fileForChildren);


            for(CmisObject par: childrenOfParent){

                fileForParentChildren = new BrowserItem(par.getName(),
                        (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                        new BrowserItem(parent.getName())
                );
                listForParentChildren.add(fileForParentChildren);

                for(CmisObject grPar:childrenOfGrandParent){
                    fileForGrandParentChildren= new BrowserItem(grPar.getName(),
                            (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                            new BrowserItem(grandParent.getName()));
                    listForGrandParentChildren.add(fileForGrandParentChildren);

                    fileForList = new BrowserItem(root.getName(),
                            (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,

                            new BrowserItem(parent.getName(),
                                    (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                                    new BrowserItem(grandParent.getName(),
                                            (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                                            listForGrandParentChildren),
                                    listForParentChildren)
                            , listForChildren );
                    list.add(fileForList);
                }
            }
        }

        return list;

    }

    public List<BrowserItem> findFolderByPath(String path) {

        Folder root =(Folder) connect().getObjectByPath(path);
        ItemIterable<CmisObject> children = root.getChildren();

        Folder parent = root.getFolderParent();
        ItemIterable<CmisObject> childrenOfParent = parent.getChildren();

        Folder grandParent = parent.getFolderParent();
        ItemIterable<CmisObject> childrenOfGrandParent = grandParent.getChildren();



        List<BrowserItem> list = new ArrayList<BrowserItem>();
        List<BrowserItem> listForChildren = new ArrayList<BrowserItem>();
        List<BrowserItem> listForParentChildren = new ArrayList<BrowserItem>();
        List<BrowserItem> listForGrandParentChildren = new ArrayList<BrowserItem>();



        BrowserItem fileForList;
        BrowserItem fileForChildren;
        BrowserItem fileForParentChildren;
        BrowserItem fileForGrandParentChildren;

        for (CmisObject o : children) {

            fileForChildren = new BrowserItem(o.getName(),
                    (o instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                    new BrowserItem(root.getName()));
            listForChildren.add(fileForChildren);


            for(CmisObject par: childrenOfParent){

                fileForParentChildren = new BrowserItem(par.getName(),
                    (par instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                    new BrowserItem(parent.getName())
                    );
            listForParentChildren.add(fileForParentChildren);

                for(CmisObject grPar:childrenOfGrandParent){
                    fileForGrandParentChildren = new BrowserItem(grPar.getName(),
                            (grPar instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                            new BrowserItem(grandParent.getName()));
                    listForGrandParentChildren.add(fileForGrandParentChildren);

                    fileForList = new BrowserItem(root.getName(),
                        (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,

                        new BrowserItem(parent.getName(),
                                (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                                new BrowserItem(grandParent.getName(),
                                        (root instanceof Folder) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE,
                                        listForGrandParentChildren),
                                listForParentChildren)
                        , listForChildren );
                list.add(fileForList);
                }
           }
        }

        return list;
    }
}
