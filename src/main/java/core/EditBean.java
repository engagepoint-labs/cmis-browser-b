package core;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/25/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "editBean", eager = true)
@ViewScoped
public class EditBean {
    User user;
    private Long id;
    @ManagedProperty("#{listBean}")
    ListBean listBean;

    private String lastName;
    private String firstName;

    public ListBean getListBean() {
        return listBean;
    }

    public void setListBean(ListBean listBean) {
        this.listBean = listBean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EditBean() {
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
        user = listBean.getUserById(getId());
        lastName = user.getLastName();
        firstName = user.getName();
    }

    public void check(){

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
