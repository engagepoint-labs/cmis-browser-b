package core;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/4/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesComponent("browserComponentTable")
public class BrowserComponentTable extends UINamingContainer {
    private List<BrowserItem> browserItemsList;

    public BrowserComponentTable() {
        browserItemsList = Service.getItems();
    }



    public List<BrowserItem> getBrowserItemsList() {
        return browserItemsList;
    }
}
