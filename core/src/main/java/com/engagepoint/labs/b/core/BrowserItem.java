package com.engagepoint.labs.b.core;

import java.io.Serializable;

public class BrowserItem   implements Serializable
{
    private String type;
    private String name;

    BrowserItem(String name, String type) {
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