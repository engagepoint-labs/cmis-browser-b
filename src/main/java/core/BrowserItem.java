package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String id;
    private List<BrowserItem> children;

    public BrowserItem(String id, String name, String type) {
        this.name = name;
        this.type = type;
        this.id = id;
        children = new ArrayList<BrowserItem>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BrowserItem> getChildren() {
        return children;
    }

    public void addChild(BrowserItem item) {
        children.add(item);
    }
}
