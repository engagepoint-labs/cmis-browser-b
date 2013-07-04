package core;

import org.primefaces.model.LazyDataModel;

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
    private static List<BrowserItem> lazyList;

    public static List<BrowserItem> getItems() {
        list = new ArrayList<BrowserItem>();
        BrowserItem root0 = new BrowserItem("1", "name1", "type1");
        BrowserItem root0Child0 = new BrowserItem("4", "name14", "type2");
        BrowserItem root0Child1 = new BrowserItem("5", "name15", "type2");
        BrowserItem root0Child2 = new BrowserItem("6", "name16", "type1");
        root0.addChild(root0Child0);

        BrowserItem root0Child1Child0 = new BrowserItem("10", "name110", "type2");
        root0Child1.addChild(root0Child1Child0);

        root0.addChild(root0Child1);

        root0.addChild(root0Child2);
        list.add(root0);

        BrowserItem root1 = new BrowserItem("2", "name2", "type2");
        BrowserItem root1Child0 = new BrowserItem("7", "name27", "type2");
        BrowserItem root1Child1 = new BrowserItem("8", "name28", "type1");
        BrowserItem root1Child2 = new BrowserItem("11", "name211", "type1");
        root1.addChild(root1Child0);
        root1.addChild(root1Child1);
        root1.addChild(root1Child2);
        list.add(root1);

        BrowserItem root2 = new BrowserItem("3", "name3", "type2");
        BrowserItem root2Child0 = new BrowserItem("9", "name39", "type2");
        root2.addChild(root2Child0);
        list.add(root2);
        return list;

    }

    public static List<BrowserItem> getLazyList() {
        lazyList = new ArrayList<BrowserItem>();
        lazyList.add(new BrowserItem("1", "name1", "type1"));
        lazyList.add(new BrowserItem("2", "name2", "type1"));
        lazyList.add(new BrowserItem("3", "name3", "type1"));
        lazyList.add(new BrowserItem("4", "name4", "type1"));
        lazyList.add(new BrowserItem("5", "name5", "type1"));
        lazyList.add(new BrowserItem("6", "name6", "type1"));
        lazyList.add(new BrowserItem("7", "name7", "type1"));
        lazyList.add(new BrowserItem("8", "name8", "type1"));
        lazyList.add(new BrowserItem("9", "name9", "type1"));
        lazyList.add(new BrowserItem("10", "name10", "type1"));
        lazyList.add(new BrowserItem("11", "name11", "type1"));
        lazyList.add(new BrowserItem("12", "name12", "type1"));
        lazyList.add(new BrowserItem("13", "name13", "type1"));
        lazyList.add(new BrowserItem("14", "name14", "type1"));
        lazyList.add(new BrowserItem("15", "name15", "type1"));
        lazyList.add(new BrowserItem("16", "name16", "type1"));
        lazyList.add(new BrowserItem("17", "name17", "type1"));
        lazyList.add(new BrowserItem("18", "name18", "type1"));
        lazyList.add(new BrowserItem("19", "name19", "type1"));
        lazyList.add(new BrowserItem("20", "name20", "type1"));
        lazyList.add(new BrowserItem("21", "name21", "type1"));
        lazyList.add(new BrowserItem("22", "name22", "type1"));
        lazyList.add(new BrowserItem("23", "name23", "type1"));
        lazyList.add(new BrowserItem("24", "name24", "type1"));
        lazyList.add(new BrowserItem("25", "name25", "type1"));
        lazyList.add(new BrowserItem("26", "name26", "type1"));
        lazyList.add(new BrowserItem("27", "name27", "type1"));
        lazyList.add(new BrowserItem("28", "name28", "type1"));
        lazyList.add(new BrowserItem("29", "name29", "type1"));
        lazyList.add(new BrowserItem("30", "name30", "type1"));
        lazyList.add(new BrowserItem("31", "name31", "type1"));
        lazyList.add(new BrowserItem("32", "name32", "type1"));
        lazyList.add(new BrowserItem("33", "name33", "type1"));
        lazyList.add(new BrowserItem("34", "name34", "type1"));
        lazyList.add(new BrowserItem("35", "name35", "type1"));
        lazyList.add(new BrowserItem("36", "name36", "type1"));
        lazyList.add(new BrowserItem("37", "name37", "type1"));
        lazyList.add(new BrowserItem("38", "name38", "type1"));
        lazyList.add(new BrowserItem("39", "name39", "type1"));
        lazyList.add(new BrowserItem("40", "name40", "type1"));

        return lazyList;
    }

    public List<BrowserItem> getItems(int start, int count) {
        int toIndex = start + count;
        if (toIndex > getLazyList().size()) {
            toIndex = getLazyList().size();
        }
        return new ArrayList<BrowserItem>(getLazyList().subList(start, toIndex));
    }

    public int getItemsCount() {
        return getLazyList().size();
    }

}
