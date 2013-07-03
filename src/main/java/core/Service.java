package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/2/13
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Service {

    private static List<BrowserItem> list = new ArrayList<BrowserItem>();

    public static List<BrowserItem> getItems() {
        list.add(new BrowserItem("name1","type1"));
        list.add(new BrowserItem("name2","type1"));
        list.add(new BrowserItem("name3","type2"));
        list.add(new BrowserItem("name4","type1"));
        list.add(new BrowserItem("name5","type2"));
        list.add(new BrowserItem("name6","type2"));
        list.add(new BrowserItem("name7","type2"));
        list.add(new BrowserItem("name8","type1"));
        list.add(new BrowserItem("name9","type1"));
        list.add(new BrowserItem("name10","type1"));

        return list;
    }
}
