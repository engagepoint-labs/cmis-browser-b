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
@ManagedBean(name="userBean", eager=true)
@RequestScoped
public class UserBean implements Serializable {

    private List<User> usersList = new ArrayList<User>();

    public UserBean() {}

    @PostConstruct
    public void init() {
        usersList.add(new User("Ivan","Ivanov"));
        usersList.add(new User("Alex","Petrov"));
        usersList.add(new User("Sergey","Timkov"));
        usersList.add(new User("Oleg","Alkov"));
    }

    public List<User> getUsersList() {
        return usersList;
    }


}
