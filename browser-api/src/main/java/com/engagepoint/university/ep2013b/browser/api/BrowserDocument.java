package com.engagepoint.university.ep2013b.browser.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowserDocument extends BrowserItem implements Serializable
{
    public BrowserDocument()
    {
        super();
        super.setType(TYPE.FILE);
    }

    public BrowserDocument(String name)
    {
        super();
        super.setType(TYPE.FILE);
        super.setName(name);
    }

    public BrowserDocument(String name, BrowserFolder parent)
    {
        super();
        super.setType(TYPE.FILE);
        super.setName(name);
        super.setParent(parent);
    }

    public BrowserDocument(String id, String name)
    {
        super();
        super.setType(TYPE.FILE);
        super.setId(id);
        super.setName(name);
    }


    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof BrowserDocument)) return false;

        return (this.getId().equals(((BrowserDocument)other).getId()));
    }

    @Override
    public String toString()
    {
        String result = "BrowserDocument ("+getId()+", "+getName()+", "+getType().name()+", ";

        result += (getParent() == null) ? "null" : getParent().getId();
        result += ")";

        return result;
    }
}