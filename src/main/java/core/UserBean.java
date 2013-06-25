package core;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/20/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "userBean", eager = true)
@RequestScoped
public class UserBean implements Serializable {

    @ManagedProperty("#{listBean}")
    ListBean listBean;

    List<User> list;

    public UserBean() {
    }

    @PostConstruct
    public void init() {
        list = listBean.getUsers();
    }

    public Collection<User> getUsersList() {
        return list;
    }

    public ListBean getListBean() {
        return listBean;
    }

    public void setListBean(ListBean listBean) {
        this.listBean = listBean;
    }
}
