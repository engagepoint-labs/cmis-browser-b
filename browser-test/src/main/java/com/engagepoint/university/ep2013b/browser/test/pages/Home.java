package com.engagepoint.university.ep2013b.browser.test.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Home extends AbstractPage {

    public Home(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://localhost:8080/browser/home.xhtml"); //  --- > url in story
        manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void treeExpand(String str_xpath){

        System.out.println("str_xpath == "+str_xpath);
         //findElement(By.xpath(".//*[@id='form:tree:0']/span/span[1]")).click();
        ///    .//*[@id='form:tree:0']//*[contains(@class,'ui-tree-toggler')]
        findElement(By.xpath(str_xpath)).click();

        manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void treeClick(){

        findElement(By.xpath(".//*[@id='form:tree:0:link']")).click();

        manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void treeClickCheck(){

         findElement(By.xpath(".//*[@id='form:tree:0']/span[@aria-selected='true']"));

    }

    public void treeExpandCheck(String str_xpath){

        //findElement(By.xpath(".//*[@id='form:tree:0' and @aria-expanded='true']"));
        //  .//*[@id='form:tree:0']//*[contains(@class,'ui-treenode-content')]

    }



}