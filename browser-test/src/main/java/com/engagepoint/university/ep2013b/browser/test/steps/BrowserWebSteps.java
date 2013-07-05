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

    @When("user clicks tree leaf by xpath '$strPath'")
    public void userClickTreeLeafByXpath(String strPath){
        testedPage.clickByXpath(strPath);
    }

    @Then("in tree leaf by xpath '$strPath' is selected")
    public void treeLeafIsSelectedByXpath(String strPath){
        testedPage.isSelectedByXpath(strPath);
    }

    @When("user clicks tree leaf by name '$strPath'")
    public void userClickTreeLeafByName(String strPath){
        testedPage.clickByName(strPath);
    }

    @Then("in tree leaf by name '$strPath' is selected")
    public void treeLeafIsSelectedByName(String strPath){
        testedPage.isSelectedByName(strPath);
    }

    @When("user clicks tree leaf by id '$strPath'")
    public void userClickTreeLeafById(String strPath){
        testedPage.clickById(strPath);
    }

    @Then("in tree leaf by id '$strPath' is selected")
    public void treeLeafIsSelectedById(String strPath){
        testedPage.isSelectedById(strPath);
    }

    @When("user clicks tree leaf expand by xpath '$strPath'")
    public void userClickTreeLeaf(String strPath){
        testedPage.treeLeafExpandByXpath(strPath);
    }

    @Then("tree leaf by xpath '$strPath' is expanded")
    public void treeLeafSelected(String strPath){
        testedPage.treeExpandCheckByXpath(strPath);
    }


}