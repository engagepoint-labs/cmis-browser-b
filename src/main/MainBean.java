package main;

import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
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

        parameter.put(SessionParameter.ATOMPUB_URL, "http://lab2:8080/chemistry-opencmis-server-inmemory-0.9.0/atom");//"http://repo.opencmis.org/inmemory/atom/");
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

        Repository repository = sessionFactory.getRepositories(parameter).get(0);
        parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());
        session = sessionFactory.createSession(parameter);
    }

    public List<File> getList() {
        Folder root = session.getRootFolder();
        ItemIterable<CmisObject> children = root.getChildren();

        list = new ArrayList<File>();

        File file;
        for (CmisObject o : children) {
            file = new File(o.getName(), o.getType().getDisplayName());
            list.add(file);
        }

        return list;
    }
}