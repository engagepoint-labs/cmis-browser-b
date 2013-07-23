package com.engagepoint.university.ep2013b.browser.component;


import javax.annotation.PostConstruct;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

@FacesComponent("browserComponentMain")
public class BrowserComponentMain extends UINamingContainer {

    public BrowserComponentMain() {


    }

    @PostConstruct
    public void init(){
        System.out.println("  ----   BrowserComponentMain");

        //Object attrs =   this.getAttributes().get("controller");



    }


}
