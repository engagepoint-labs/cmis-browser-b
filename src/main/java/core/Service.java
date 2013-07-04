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
        BrowserItem root0 = new BrowserItem("1", "name1", "type1");
        BrowserItem root0Child0 = new BrowserItem("4", "name4", "type2");
        BrowserItem root0Child1 = new BrowserItem("5", "name5", "type2");
        BrowserItem root0Child2 = new BrowserItem("6", "name6", "type1");
        root0.addChild(root0Child0);

        BrowserItem root0Child1Child0 = new BrowserItem("10", "name10", "type2");
        root0Child1.addChild(root0Child1Child0);

        root0.addChild(root0Child1);

        root0.addChild(root0Child2);
        list.add(root0);

        BrowserItem root1 = new BrowserItem("2", "name2", "type2");
        BrowserItem root1Child0 = new BrowserItem("7", "name7", "type2");
        BrowserItem root1Child1 = new BrowserItem("8", "name8", "type1");
        root1.addChild(root1Child0);
        root1.addChild(root1Child1);
        list.add(root1);

        BrowserItem root2 = new BrowserItem("3", "name3", "type2");
        BrowserItem root2Child0 = new BrowserItem("9", "name9", "type2");
        root2.addChild(root2Child0);
        list.add(root2);
        return list;

    }
}
