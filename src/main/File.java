package main;

import javax.faces.bean.ManagedBean;
//Hello :-)
@ManagedBean(name = "file", eager = true)
public class File {
    private String type;
    private String name;

    File(String name, String type) {
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
