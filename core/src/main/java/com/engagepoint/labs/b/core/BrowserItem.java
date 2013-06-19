package com.engagepoint.labs.b.core;

public class BrowserItem
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