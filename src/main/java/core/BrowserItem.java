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
    private long id;

    public BrowserItem(long id, String name, String type) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
