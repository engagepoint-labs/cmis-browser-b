package com.engagepoint.university.ep2013b.browser.component;

import com.engagepoint.university.ep2013b.browser.api.BrowserService;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@FacesComponent("browserComponentButton")
public class BrowserComponentButton extends UINamingContainer {
    private BrowserService service;
    private String folderId;

    public BrowserComponentButton() {
        service = BrowserFactory.getInstance("CMIS");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        folderId = request.getParameter("folderId");
    }



    public BrowserService getService() {
        return service;
    }

    public void setService(BrowserService service) {
        this.service = service;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
}
