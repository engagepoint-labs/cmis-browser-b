package com.engagepoint.labs.b.steps;

import com.engagepoint.labs.b.pages.Pages;
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




}