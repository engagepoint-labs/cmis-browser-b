package core;

import org.primefaces.model.LazyDataModel;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private List<BrowserItem> childrenList;
    private LazyDataModel<BrowserItem> lazyModel;

    public BrowserComponentTable() {
        browserItemsList = Service.getItems();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestId = request.getParameter("id");

        lazyModel = new LazyItemModel(Service.getLazyList());

//        if (requestId == null) {
//            childrenList = browserItemsList;
//        } else {
//            childrenList = findChildren(browserItemsList, requestId);
//        }
    }

    public List<BrowserItem> findChildren(List<BrowserItem> list, String requestId) {
        List<BrowserItem> tempList = new ArrayList<BrowserItem>();

        for (BrowserItem item : list) {

            if (item.getId().equals(requestId)) {
                return item.getChildren();
            }
            if (item.getChildren().size() != 0) {
                findChildren(item.getChildren(), requestId);
            }
        }
        return tempList;
    }

    public List<BrowserItem> getBrowserItemsList() {
        return browserItemsList;
    }

    public List<BrowserItem> getChildrenList() {
        return childrenList;
    }

    public LazyDataModel<BrowserItem> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<BrowserItem> lazyModel) {
        this.lazyModel = lazyModel;
    }
}