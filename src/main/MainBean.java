package main;

import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "bean", eager = true)
public class MainBean implements Serializable
{
    private List<File> list;
    private Session session;

    public MainBean() {
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // ATOM
//        parameter.put(SessionParameter.ATOMPUB_URL, "http://lab2:8080/chemistry-opencmis-server-inmemory-0.9.0/atom");//"http://repo.opencmis.org/inmemory/atom/");
//        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

       // WSDL - not working (even in Workbench)
       // Error: Cannot initialize Web Services service object [RepositoryService]: null

        final String url = "http://lab2:8080/cmis-browser/services11/";
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
        session = sessionFactory.createSession(parameter);
    }

    public List<File> getList() {
        Folder root = session.getRootFolder();
        ItemIterable<CmisObject> children = root.getChildren();

        list = new ArrayList<File>();

        File file;
        Document doc;

        for (CmisObject o : children) {
            Property<String> p = o.getProperty(PropertyIds.CREATION_DATE);

            file = new File(o.getName(), p.getValueAsString());
            list.add(file);
        }

        return list;
    }
}