package com.engagepoint.labs.b.web;

import com.engagepoint.labs.b.core.*;

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
//    private CMISHelper core = new CMISHelper();
    private List<BrowserItem> list = new ArrayList<BrowserItem>();

    public MainBean() {
    }

    public String getMessage() {
        return message;
    }

    @PostConstruct
    public void  initItemList(){
        list =  new CMISHelper().getRootFolder();
    }

    public List<BrowserItem> getList() {
        return list;
    }

    public void setList(List<BrowserItem> list) {
        this.list = list;
    }


//    public List<BrowserItem> getList() {
//        return core.getRootFolder();
//    }


}
