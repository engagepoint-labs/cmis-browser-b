package com.engagepoint.university.ep2013b.browser.component;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;

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
    private BrowserService service;
    private String folderId;


    public BrowserComponentTable() {
        service = BrowserFactory.getInstance("CMIS");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        folderId = request.getParameter("folderId");

        if(folderId == null) {
            folderId = "100";
        }

        BrowserItem currentFolder = service.findFolderById(folderId, false);
        browserItemsList = currentFolder.getChildren();

    }



    public List<BrowserItem> getBrowserItemsList() {
        return browserItemsList;
    }

    public String getFolderId() {
        return folderId;
    }
}