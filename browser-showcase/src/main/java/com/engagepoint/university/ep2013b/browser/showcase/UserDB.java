package com.engagepoint.university.ep2013b.browser.showcase;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/25/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean (name="users")
@SessionScoped
public class UserDB implements Serializable {

    private ArrayList<User> db = new ArrayList<User>();

    public UserDB() {
    }

    public ArrayList<User> getDb() {
        return db;
    }

    public void setDb(ArrayList<User> db) {
        this.db = db;
    }

    @PostConstruct
    public void init(){

        int id = 0;

        User us = new User(++id, "Ivan", "Petrov");
        db.add(us);
        us = new User(++id, "Dmitro","Ivanov");
        db.add(us);
        us = new User(++id, "Danila","Severny");
        db.add(us);
        us = new User(++id, "Pavlo","Demidov");
        db.add(us);
        us = new User(++id, "Sergio","Suvorov");
        db.add(us);
        us = new User(++id, "Fedir","Olenev");
        db.add(us);
        us = new User(++id, "Stas","Kamarin");
        db.add(us);


    }


}
