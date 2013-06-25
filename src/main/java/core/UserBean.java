package core;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/20/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(eager = true)
@RequestScoped
public class UserBean implements Serializable {

    private List<User> usersList = new ArrayList<User>();

    public UserBean() {}

    @PostConstruct
    public void init() {
        addUser(new User("Ivan","Ivanov"));
        addUser(new User("Alex","Petrov"));
        addUser(new User("Sergey","Timkov"));
        addUser(new User("Oleg","Alkov"));
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void addUser(User u) {
        usersList.add(u);
    }
}
