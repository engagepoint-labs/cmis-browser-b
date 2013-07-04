package com.engagepoint.university.ep2013b.browser.showcase;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/27/13
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private List<User> children;

    public User() {
        children = new ArrayList<User>();
    }

    public User(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        children = new ArrayList<User>();
    }

    public List<User> getChildren() {
        return children;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }

    public void addChild(User user) {
        children.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
