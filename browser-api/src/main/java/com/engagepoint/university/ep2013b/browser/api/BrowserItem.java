package com.engagepoint.university.ep2013b.browser.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowserItem implements Serializable
{
    public enum TYPE {FILE, FOLDER}

    private String id = "";
    private TYPE type = TYPE.FILE;
    private String name = "";
    private BrowserFolder parent = null;

    public BrowserItem()
    {
    }

    public BrowserItem(String name)
    {
        this.name = name;
    }

    public BrowserItem(String name, TYPE type)
    {
        this.name = name;
        this.type = type;
    }

    public BrowserItem(String name, TYPE type, BrowserFolder parent)
    {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public BrowserItem(String id, String name, TYPE folder) {
        this.id = id;
        this.name = name;
        this.type = folder;
    }


    public BrowserItem(String id, String name, TYPE type, BrowserFolder parent)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parent = parent;
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public TYPE getType()
    {
        return type;
    }

    public void setType(TYPE type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BrowserFolder getParent()
    {
        return parent;
    }

    public void setParent(BrowserFolder parent)
    {
        this.parent = parent;
    }


    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof BrowserItem)) return false;

        return (this.getId().equals(((BrowserItem)other).getId()));
    }

    @Override
    public String toString()
    {
        String result = "BrowserItem ("+getId()+", "+getName()+", "+getType().name()+", ";

        result += (parent == null) ? "null" : parent.getId();
        result += ")";

        return result;
    }
}