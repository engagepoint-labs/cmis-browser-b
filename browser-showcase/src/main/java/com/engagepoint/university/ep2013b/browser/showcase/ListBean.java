package com.engagepoint.university.ep2013b.browser.showcase;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/27/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;


@ManagedBean(name = "listBean", eager = true)
@SessionScoped
public class ListBean implements Serializable {
    private List<User> users = new ArrayList<User>();

    public ListBean() {
    }

    @PostConstruct
    public void init() {
        User root = new User(1L, "level1", "Ivanov");
        User rootChild1 = new User(2L, "level1Child1", "Ivanov");
        User rootChild2 = new User(3L, "level1Child2", "Petrov");
        root.addChild(rootChild1);
        root.addChild(rootChild2);

        users.add(root);
        User root2 = new User(4L, "level2", "Timkov");

        User root2Child1 = new User(5L, "level2Child1", "Senkov");
        root2.addChild(root2Child1);
        users.add(root2);

        User root3 = new User(6L, "level3", "Panov");
        users.add(root3);
    }

    public List<User> getUsers() {
        return users;
    }
}