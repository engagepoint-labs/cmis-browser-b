package com.engagepoint.labs.b.web;

import com.engagepoint.labs.b.core.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "bean")
@ViewScoped
public class MainBean implements Serializable
{
    private String message = "Working!!!";
    private CMISHelper core = new CMISHelper();

    public MainBean() {
    }

    public String getMessage() {
        return message;
    }

    public List<BrowserItem> getList() {
        return core.getRootFolder();
    }
}
