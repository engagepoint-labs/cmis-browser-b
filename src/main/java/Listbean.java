import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/20/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class Listbean implements Serializable {

    private List<User> list = new ArrayList<User>();
    private User selected;

    public Listbean() {
        int id = 0;
        User us = new User(++id, "Ivan", "Petrov");
        list.add( us);
        us = new User(++id, "Dmitro","Ivanov");
        list.add( us);
        us = new User(++id, "Danila","Severny");
        list.add( us);
        us = new User(++id, "Pavlo","Demidov");
        list.add( us);
        us = new User(++id, "Sergio","Suvorov");
        list.add( us);
        us = new User(++id, "Fedir","Olenev");
        list.add( us);
        us = new User(++id, "Stas","Kamarin");
        list.add(us);
    }




    public List<User> getList() {
        return list;
    }

    public User getSelected() {
        return selected;
    }



    public String select(User us){
        selected = us;
        return "edit";
    }


}
