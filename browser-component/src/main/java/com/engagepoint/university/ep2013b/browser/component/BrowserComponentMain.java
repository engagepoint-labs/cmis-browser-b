package com.engagepoint.university.ep2013b.browser.component;


import javax.annotation.PostConstruct;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

@FacesComponent("browserComponentMain")
public class BrowserComponentMain extends UINamingContainer {

    public BrowserComponentMain() {

        Object attrs =   this.getAttributes().get("controller");


    }

    @PostConstruct
    public void init(){



    }


}
