package com.engagepoint.university.ep2013b.browser.component;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/2/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesComponent("BrowserComponent")
public class BrowserComponent extends UIComponentBase implements Serializable {

    @Override
    public String getFamily() {
        return null;
    }

    @Override
    public void encodeBegin(FacesContext arg0) throws IOException {

    }
}

