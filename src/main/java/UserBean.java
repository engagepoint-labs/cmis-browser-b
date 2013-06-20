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

    private String name;
    private String lastName;
    private List<UserBean> usersList = new ArrayList<UserBean>();


    public UserBean() {
        addUser(new UserBean("Ivan","Ivanov"));
        addUser(new UserBean("Alex","Petrov"));
        addUser(new UserBean("Sergey","Timkov"));
        addUser(new UserBean("Oleg","Alkov"));
    }

    public UserBean(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserBean> getUsersList() {
        return usersList;
    }

    public void addUser(UserBean ub) {
        usersList.add(ub);
    }
}
