package core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/3/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class BrowserItem implements Serializable {
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
