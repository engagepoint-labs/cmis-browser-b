package core;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/25/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name="editBean", eager=true)
@ViewScoped
public class EditBean {
    User user;
    private Long id;
    @ManagedProperty("#{listBean}")
    ListBean listBean;

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

    @PostConstruct
    public void init() {
        user = listBean.getUserById(getId());
    }

    public void save() {

    }

    public User getUser() {
        return user;
    }
}
