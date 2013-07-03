package com.engagepoint.university.ep2013b.browser.api;

import java.io.Serializable;

public class BrowserItem implements Serializable
{
    private String type;
    private String name;

    public BrowserItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}