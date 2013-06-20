package com.engagepoint.labs.b.pages;

import org.jbehave.web.selenium.WebDriverProvider;

import java.util.concurrent.TimeUnit;

public class Home extends AbstractPage {

    public Home(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://localhost:8080/CMIS-Browser/home.xhtml"); //  --- > url in story
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}