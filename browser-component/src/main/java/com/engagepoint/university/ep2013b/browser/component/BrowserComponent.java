package com.engagepoint.university.ep2013b.browser.component;


import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/2/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesComponent("BrowserComponent")
public class BrowserComponent extends org.primefaces.component.tree.Tree {

    @Override
    public String getFamily() {
        return null;
    }

    @Override
    public void encodeBegin(FacesContext arg0) throws IOException {

    }
}

