package com.engagepoint.university.ep2013b.browser.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowserFolder extends BrowserItem implements Serializable
{
    private List<BrowserItem> children = new ArrayList<BrowserItem>();
    private int totalPages = 0;

    public BrowserFolder()
    {
        super();
        super.setType(TYPE.FOLDER);
    }

    public BrowserFolder(String name)
    {
        super();
        super.setType(TYPE.FOLDER);
        super.setName(name);
    }

    public BrowserFolder(String name, BrowserFolder parent)
    {
        super();
        super.setType(TYPE.FOLDER);
        super.setName(name);
        super.setParent(parent);
    }

    public BrowserFolder(String id, String name)
    {
        super();
        super.setType(TYPE.FOLDER);
        super.setId(id);
        super.setName(name);
    }

    public BrowserFolder(String name, BrowserFolder parent, List<BrowserItem> children)
    {
        super();
        super.setType(TYPE.FOLDER);
        super.setName(name);
        super.setParent(parent);

        this.children = children;
    }

    public BrowserFolder(String name, BrowserFolder parent, List<BrowserItem> children, int totalPages) {
        super();
        super.setType(TYPE.FOLDER);
        super.setName(name);
        super.setParent(parent);

        this.children = children;
        this.totalPages = totalPages;
    }


    public BrowserFolder(String id, String name, BrowserFolder parent, List<BrowserItem> children) {

        super();
        super.setType(TYPE.FOLDER);
        super.setId(id);
        super.setName(name);
        super.setParent(parent);

        this.children = children;
    }

    public List<BrowserItem> getChildren()
    {
        return children;
    }

    public void setChildren(List<BrowserItem> children)
    {
        this.children.addAll(children);
    }

    public void setChild(BrowserItem child)
    {
        children.add(child);
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof BrowserFolder)) return false;

        return (this.getId().equals(((BrowserFolder)other).getId()));
    }

    @Override
    public String toString()
    {
        String result = "BrowserFolder ("+getId()+", "+getName()+", "+getType().name()+", ";

        result += (getParent() == null) ? "null" : getParent().getId();
        result += ", ";

        if (children.isEmpty()) result += "empty";
        else
        {
            result += "[";
            for (BrowserItem i : children)
            {
                result += i.getId() + ", ";
            }
            result += "]";
        }

        result += ")";

        return result;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}