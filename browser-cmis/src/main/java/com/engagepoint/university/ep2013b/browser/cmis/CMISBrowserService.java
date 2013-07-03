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

    public List<BrowserItem> getRootFolder() {
        Folder root = connect().getRootFolder();
        ItemIterable<CmisObject> children = root.getChildren();

        List<BrowserItem> list = new ArrayList<BrowserItem>();

        BrowserItem file;

        for (CmisObject o : children) {
            Property<String> p = o.getProperty(PropertyIds.CREATION_DATE);

            file = new BrowserItem(o.getName(), p.getValueAsString());
            list.add(file);
        }

        return list;
    }



    public List<BrowserItem> findFolderById(String id, boolean includeOnlyFolders) {

        ItemIterable<QueryResult> results = connect().query("SELECT cmis:name FROM cmis:folder WHERE IN_FOLDER('" + id + "')", false);
        ItemIterable<QueryResult> results1 = connect().query("SELECT cmis:path FROM cmis:folder where cmis:objectId LIKE '" + id + "'", false);
        ItemIterable<QueryResult> results2 = connect().query("SELECT cmis:name FROM cmis:document WHERE IN_FOLDER('" + id + "')", false);

        List<BrowserItem> list = new ArrayList<BrowserItem>();

        BrowserItem file;
        BrowserItem file1;
        BrowserItem file2;

        for (QueryResult hit1 : results1) {
            for (PropertyData<?> property1 : hit1.getProperties()) {

                String queryName1 = property1.getQueryName();
                Object value1 = property1.getFirstValue();

                file1 = new BrowserItem(value1.toString(), queryName1);
                list.add(file1);
            }
        }


        for (QueryResult hit : results) {
            for (PropertyData<?> property : hit.getProperties()) {

                String queryName = property.getQueryName();
                Object value = property.getFirstValue();

                file = new BrowserItem(value.toString(), queryName);
                list.add(file);
            }
        }

        if(includeOnlyFolders==false){
            for (QueryResult hit1 : results2) {
                for (PropertyData<?> property2 : hit1.getProperties()) {

                    String queryName2 = property2.getQueryName();
                    Object value2 = property2.getFirstValue();

                    file2 = new BrowserItem(value2.toString(), queryName2);
                    list.add(file2);
                }
            }

        }


          return list;


    }

    public List<BrowserItem> findFolderByPath(String path,boolean includeOnlyFolders) {




        ItemIterable<QueryResult> results2 = connect().query("SELECT cmis:objectId FROM cmis:folder WHERE cmis:path LIKE '"+path+"'", false);
        String value2 = "";
        for (QueryResult hit1 : results2) {
            for (PropertyData<?> property1 : hit1.getProperties()) {
                value2 = property1.getFirstValue().toString();
            }
        }


        ItemIterable<QueryResult> results = connect().query("SELECT cmis:name FROM cmis:folder WHERE IN_FOLDER('" + value2 + "') ", false);

        ItemIterable<QueryResult> results1 = connect().query("SELECT cmis:path FROM cmis:folder where cmis:path LIKE '"+path+"' ", false);
        ItemIterable<QueryResult> results3 = connect().query("SELECT cmis:name FROM cmis:document WHERE IN_FOLDER('" + value2 + "')", false);

        List<BrowserItem> list = new ArrayList<BrowserItem>();

        BrowserItem file;
        BrowserItem file1;
        BrowserItem file3;


        for (QueryResult hit1 : results1) {
            for (PropertyData<?> property1 : hit1.getProperties()) {

                String queryName1 = property1.getQueryName();
                Object value1 = property1.getFirstValue();

                file1 = new BrowserItem(value1.toString(), queryName1);
                list.add(file1);
            }
        }


        for (QueryResult hit : results) {
            for (PropertyData<?> property : hit.getProperties()) {

                String queryName = property.getQueryName();
                Object value = property.getFirstValue();

                file = new BrowserItem(value.toString(), queryName);
                list.add(file);
            }
        }

        if(includeOnlyFolders==false){
            for (QueryResult hit1 : results3) {
                for (PropertyData<?> property3 : hit1.getProperties()) {

                    String queryName3 = property3.getQueryName();
                    Object value3 = property3.getFirstValue();

                    file3 = new BrowserItem(value3.toString(), queryName3);
                    list.add(file3);
                }
            }
        }

        return list;
    }
}
