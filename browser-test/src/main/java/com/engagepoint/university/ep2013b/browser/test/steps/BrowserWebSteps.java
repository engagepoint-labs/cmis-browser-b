package com.engagepoint.university.ep2013b.browser.test.steps;

import com.engagepoint.university.ep2013b.browser.test.pages.TestedPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BrowserWebSteps {

    private final TestedPage testedPage;
    private String prevSelectedFolder = null;
    private String currSelectedFolder = null;


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
        setSelectedValues(strPath);
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
        setSelectedValues(strPath);
    }

    @Then("in the tree folder by name '$strPath' is selected")
    public void treeLeafIsSelectedByName(String strPath){
        testedPage.isSelectedByName(strPath);
    }

    @When("user clicks tree folder by id '$strPath'")
    public void userClickTreeLeafById(String strPath){
        testedPage.clickById(strPath);
        setSelectedValues(strPath);
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

    //// ==================================================================
    ////   table pagination buttons

    @When("user clicks table button by xpath '$strPath'")
    public void userClickTableButton(String strPath){
        testedPage.clickByXpath(strPath);
    }

    @Then("in the table button by xpath '$strPath' is enabled")
    public void tableButtonIsEnabled(String strPath){
        testedPage.isEnabledByXpath(strPath);
    }

    @Then("in the table button by xpath '$strPath' is disabled")
    public void tableButtonIsDisabled(String strPath){
        testedPage.isDisabledByXpath(strPath);
    }

    //// ==================================================================
    //// native navigation buttons

    private void setSelectedValues(String currPath){

        prevSelectedFolder = currSelectedFolder;
        currSelectedFolder = currPath;
    }


    @When("user clicks navigation button Back")
    public void userClickBack(){
        //testedPage.clickByXpath();
        testedPage.navigate().back();

    }

    @When("user clicks navigation button Forward")
    public void userClickForward(){
        //testedPage.clickByXpath();
        testedPage.navigate().forward();

    }

    // after Back move
    @Then("in the tree folder by previous xpath is selected")
    public void treeFolderPrevIsSelected(){
        testedPage.isSelectedByXpath(prevSelectedFolder);
    }

    // after Forward move
    @Then("in the tree folder by current xpath is selected")
    public void treeFolderCurrIsSelected(){
        testedPage.isSelectedByXpath(currSelectedFolder);
    }

    // ---------------------------------------------------------------------------------------------------------------
    @Given("opened '$url'")
    public void open(String url)
    {
        testedPage.open(url);
    }

    @When("clicks on '$xpath'")
    public void click(String xpath)
    {
        testedPage.click(xpath);
    }

    @When("types '$text' on '$xpath'")
    public void type(String text, String xpath)
    {
        testedPage.type(text, xpath);
    }

    @Then("find '$text' on '$xpath'")
    public void find(String text, String xpath)
    {
        testedPage.find(text, xpath);
    }
    // ---------------------------------------------------------------------

    @When("use backspace in '$xpath'")
    public void useBackspace(String xpath) throws InterruptedException {
        testedPage.clearText(xpath);
    }

    @When("use right click on '$xpath'")
    public void useRightClick(String xpath) {
        testedPage.rightClick(xpath);
    }

    @Then("'$strPath' is visible on the page")
    public void visible(String strPath){
        testedPage.isDisplayedByXpath(strPath);
    }

    @Then("wait")
    public void waitForSec() throws InterruptedException {
        Thread.sleep(5000);
    }

}