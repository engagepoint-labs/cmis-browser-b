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

    @Given("user is on Home page")
    public void userIsOnHomePage(){
        pages.home().open();
    }

    @Then("Find title $title user")
    public void findTitle(String title){
        pages.home().found(title);
    }

    @Given("user is on Index page")
    public void userIsOnIndexPage(){
        pages.index().open();
    }

    @Then("Find text $title")
    public void findText(String title){
        pages.index().found(title);
    }

    @When("user clicks tree $root")
    public void userClickTreeNode(String root){
        pages.home().treeClick(root);
    }

    @Then("node $node is found")
    public void treeNodeIsSelected(String node){
        pages.home().found(node);
    }

    @Then("node $node is selected")
    public void treeNodeSelected(String node){
        pages.home().treeCheck(node);
    }

}