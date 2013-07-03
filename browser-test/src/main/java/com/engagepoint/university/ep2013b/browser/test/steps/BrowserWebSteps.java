package com.engagepoint.university.ep2013b.browser.test.steps;

import com.engagepoint.university.ep2013b.browser.test.pages.Pages;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BrowserWebSteps {

    private final Pages pages;


    public BrowserWebSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user goes on page by url '$url'")
    public void userIsOnHomePage(String url){
        pages.home().openByURL(url);
    }

    @Then("page is shown")
    public void pageIsShown() {
        pages.home().found("browser-showcase");
    }

    @Then("find text '$text'")
    public void findTitle(String text){
        pages.home().found(text);
    }

    @Given("user is on Index page")
    public void userIsOnIndexPage(){
        pages.index().open();
    }

    @When("user clicks tree leaf by xpath '$strPath'")
    public void userClickTreeLeafByXpath(String strPath){
        pages.home().clickByXpath(strPath);
    }

    @Then("in tree leaf by xpath '$strPath' is selected")
    public void treeLeafIsSelectedByXpath(String strPath){
        pages.home().isSelectedByXpath(strPath);
    }

    @When("user clicks tree leaf by name '$strPath'")
    public void userClickTreeLeafByName(String strPath){
        pages.home().clickByName(strPath);
    }

    @Then("in tree leaf by name '$strPath' is selected")
    public void treeLeafIsSelectedByName(String strPath){
        pages.home().isSelectedByName(strPath);
    }

    @When("user clicks tree leaf by id '$strPath'")
    public void userClickTreeLeafById(String strPath){
        pages.home().clickById(strPath);
    }

    @Then("in tree leaf by id '$strPath' is selected")
    public void treeLeafIsSelectedById(String strPath){
        pages.home().isSelectedById(strPath);
    }

    @When("user clicks tree leaf expand by xpath '$strPath'")
    public void userClickTreeLeaf(String strPath){
        pages.home().treeLeafExpandByXpath(strPath);
    }

    @Then("tree leaf by xpath '$strPath' is expanded")
    public void treeLeafSelected(String strPath){
        pages.home().treeExpandCheckByXpath(strPath);
    }


}