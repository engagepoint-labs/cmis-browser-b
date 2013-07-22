package com.engagepoint.university.ep2013b.browser.component.controller;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: evgeniy.shevchenko
 * Date: 7/22/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBrowserController implements BrowserController {
    private int currentPage;
    @PostConstruct
    public void init() {
        currentPage=0;
    }

    public void tablePrev() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void tableNext() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void tableFirst() {
        currentPage--;
    }

    public void tableLast() {
        currentPage++;
    }

}
