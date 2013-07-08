package com.engagepoint.university.ep2013b.browser.test.steps;

import com.engagepoint.university.ep2013b.browser.test.pages.TestedPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BrowserWebSteps {

    private final TestedPage testedPage;

    public BrowserWebSteps(TestedPage page) {
        this.testedPage = page;
    }

    @Given("user goes on page by url '$url'")
    public void userIsOnHomePage(String url){
        testedPage.openByURL(url);
    }

    @Then("page is shown")
    public void pageIsShown() {
        testedPage.pageIsShown();
    }

    @Then("find text '$text'")
    public void findTitle(String text){
        testedPage.found(text);
    }

    @Then("on the page the tree by xpath '$strPath' is visible")
    public void pageTreeIsDisplayedByXpath(String strPath){
        testedPage.isDisplayedByXpath(strPath);
    }


    @Then("on the page the table by xpath '$strPath' is visible")
    public void pageTableIsDisplayedByXpath(String strPath){
        testedPage.isDisplayedByXpath(strPath);
    }


    @When("user clicks in the tree folder by xpath '$strPath'")
    public void userClickTreeLeafByXpath(String strPath){
        testedPage.clickByXpath(strPath);
    }

    @Then("in the tree folder by xpath '$strPath' is selected")
    public void treeLeafIsSelectedByXpath(String strPath){
        testedPage.isSelectedByXpath(strPath);
    }

    @Then("in the table folder child by xpath '$strPath' is visible")
    public void treeLeafIsDisplayedByXpath(String strPath){
        testedPage.isDisplayedByXpath(strPath);
    }

    @When("user clicks in the tree folder by name '$strPath'")
    public void userClickTreeLeafByName(String strPath){
        testedPage.clickByName(strPath);
    }

    @Then("in the tree folder by name '$strPath' is selected")
    public void treeLeafIsSelectedByName(String strPath){
        testedPage.isSelectedByName(strPath);
    }

    @When("user clicks tree folder by id '$strPath'")
    public void userClickTreeLeafById(String strPath){
        testedPage.clickById(strPath);
    }

    @Then("in the tree folder by id '$strPath' is selected")
    public void treeLeafIsSelectedById(String strPath){
        testedPage.isSelectedById(strPath);
    }

    @When("user clicks tree folder toggler by xpath '$strPath'")
    public void userClickTreeLeaf(String strPath){
        testedPage.treeLeafExpandByXpath(strPath);
    }

    @Then("in the tree folder by xpath '$strPath' is expanded")
    public void treeLeafSelected(String strPath){
        testedPage.treeExpandCheckByXpath(strPath);
    }




}