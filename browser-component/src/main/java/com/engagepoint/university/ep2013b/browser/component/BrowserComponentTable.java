package com.engagepoint.university.ep2013b.browser.component;


import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/4/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesComponent("browserComponentTable")
public class BrowserComponentTable extends UINamingContainer {
    private List<BrowserItem> browserItemsList;
    private Service service;
    private String folderId;


    public BrowserComponentTable() {
        service = new Service();
        browserItemsList = Service.getItems();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        folderId= request.getParameter("folderId");

    }

    public List<BrowserItem> getBrowserItemsList() {
        return browserItemsList;
    }

    public String getFolderId() {
        return folderId;
    }
}