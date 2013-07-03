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

    private static List<BrowserItem> list;

    public static List<BrowserItem> getItems() {
        list = new ArrayList<BrowserItem>();
        list.add(new BrowserItem(1, "name1","type1"));
        list.add(new BrowserItem(2, "name2","type1"));
        list.add(new BrowserItem(3, "name3","type2"));
        list.add(new BrowserItem(4, "name4","type1"));
        list.add(new BrowserItem(5, "name5","type2"));
        list.add(new BrowserItem(6, "name6","type2"));
        list.add(new BrowserItem(7, "name7","type2"));
        list.add(new BrowserItem(8, "name8","type1"));
        list.add(new BrowserItem(9, "name9","type1"));
        list.add(new BrowserItem(10, "name10","type1"));

        return list;
    }
}
