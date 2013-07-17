package com.engagepoint.university.ep2013b.browser.test.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TestedPage extends WebDriverPage {

    private final WebDriverProvider driverProvider;

    public TestedPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        this.driverProvider = driverProvider;
   }

    /////    service

    public void found(String text) {
        found(getPageSource(), text);
    }

    public void found(String pageSource, String text) {
        if (!pageSource.contains(escapeHtml(text))) {
            fail("Text: '" + text + "' not found in page '" + pageSource + "'");
        }
    }

    public void found(List<String> texts) {
        for (String text : texts) {
            found(text);
        }
    }

    private String escapeHtml(String text) {
        return text.replace("<", "&lt;").replace(">", "&gt;");
    }

   //// core

    public void openByURL(String url) {

        get(url); //  --- > url in story
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void findText(String text){

        found(text);
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void pageIsShown(){

        found("browser-showcase");
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

    public void isDisabledByXpath(String strPath) {

        assertFalse(findElement(By.xpath(strPath)).isEnabled());
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

        findElement(By.xpath(strXpath)).isDisplayed();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public void isVisibleByXpath(String strXpath) {

        //String ss = "";
        findElement(By.xpath(strXpath)).isDisplayed();
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    // ---------------------------------------------------------------------------------------------------------------
    public void open(String url)
    {
        get(url);
    }

    public void click(String xpath)
    {
        WebElement element = findElement(By.xpath(xpath));
        element.click();
    }

    public void type(String text, String xpath)
    {
        WebElement element = findElement(By.xpath(xpath));
        element.sendKeys(text);
    }

    public void find(final String text, final String xpath)
    {
        // wait until element changed its text, if timeout than not found
        (new WebDriverWait(this, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d)
            {
                WebElement element = findElement(By.xpath(xpath));
                return element.getText().equals(text);
            }
        });

    }
    // ----------------------------------------------------------------------

    public void clearText(String xpath) {
        WebElement element = findElement(By.xpath(xpath));
        element.clear();
    }

    public void rightClick(String xpath) {
        WebElement element = findElement(By.xpath(xpath));
        WebDriver webDriver = driverProvider.get();
        Actions action = new Actions(webDriver);
        action.contextClick(element).perform();
    }

    public void select(String text, String xpath) {
        WebElement element = findElement(By.xpath(xpath));
        Select select = new Select(element);
        select.selectByValue(text);
    }
}
