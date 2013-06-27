package core;

import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;


@ManagedBean(name = "editBean")
@ViewScoped
public class EditBean {
    private User user;
    private Long id;
    private String lastName;
    private String firstName;

    @ManagedProperty("#{usersBean}")
    UsersBean usersBean;

    public UsersBean getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    public EditBean() {
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void init(ComponentSystemEvent event) {
        user = usersBean.getUserById(getId());
        lastName = user.getLastName();
        firstName = user.getName();
    }



    public String save() {
        user.setName(firstName);
        user.setLastName(lastName);
        return "save";
    }

    public User getUser() {
        return user;
    }
}
