package com.engagepoint.university.ep2013b.browser.showcase;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.component.BrowserFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "bean")
@ViewScoped
public class MainBean implements Serializable
{
    private String message = "Working!!!";
    private List<BrowserItem> list = new ArrayList<BrowserItem>();

    public MainBean() {
    }

    public String getMessage() {
        return message;
    }

    @PostConstruct
    public void  initItemList()
    {
        // Request data from convenient data service provider
        BrowserService data = BrowserFactory.getInstance("CMIS");
        list = data.getRootFolder();
    }

    public List<BrowserItem> getList() {
        return list;
    }

    public void setList(List<BrowserItem> list) {
        this.list = list;
    }
}
