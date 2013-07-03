package com.engagepoint.university.ep2013b.browser.test.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Home extends AbstractPage {

    public Home(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void openByURL(String url) {

        get(url); //  --- > url in story
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void findText(String text){

        found(text);
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



    //// ==========================================================
    //// locations by all three ways :
    ////  by xpath
    public void clickByXpath(String strPath){

        findElement(By.xpath(strPath)).click();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isSelectedByXpath(String strPath){

         findElement(By.xpath(strPath)).isSelected();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isEnabledByXpath(String strPath){

        findElement(By.xpath(strPath)).isEnabled();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isDisplayedByXpath(String strPath){

        findElement(By.xpath(strPath)).isDisplayed();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    //// by id
    public void clickById(String strPath){

        findElement(By.id(strPath)).click();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isSelectedById(String strPath){

        findElement(By.id(strPath)).isSelected();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isEnabledById(String strPath){

        findElement(By.id(strPath)).isEnabled();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isDisplayedById(String strPath){

        findElement(By.id(strPath)).isDisplayed();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //// by name
    public void clickByName(String strPath){

        findElement(By.name(strPath)).click();

        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isSelectedByName(String strPath){

        findElement(By.name(strPath)).isSelected();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isEnabledByName(String strPath){

        findElement(By.name(strPath)).isEnabled();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void isDisplayedByName(String strPath){

        findElement(By.name(strPath)).isDisplayed();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    //// =============================================================
    ////  tree toggler  - only by xpath (there are no id and name)

    public void treeLeafExpandByXpath(String strXpath){

        findElement(By.xpath(strXpath)).click();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void treeExpandCheckByXpath(String strXpath){

        //// ".//*[@id='form:tree:0']//*[contains(@class,'ui-treenode-children')]"
        findElement(By.xpath(strXpath)).isDisplayed();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


}