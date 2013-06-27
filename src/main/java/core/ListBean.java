package core;

import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.*;


@ManagedBean(name = "listBean")
@ViewScoped
public class ListBean {
    private LazyPersonDataModel lazyModel;

    @ManagedProperty("#{usersBean}")
    UsersBean usersBean;

    public UsersBean getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    public ListBean() {

    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyPersonDataModel(usersBean);
    }

    public LazyPersonDataModel getLazyModel() {
        return lazyModel;
    }
}
