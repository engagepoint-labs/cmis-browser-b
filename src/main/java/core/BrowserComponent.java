package core;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/2/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesComponent("browserComponent")
public class BrowserComponent extends UINamingContainer {
    private TreeNode root;
    private List<BrowserItem> browserItemsList;
    boolean isSelected = false;
    private String id;
    private String requestId;


    public BrowserComponent() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        requestId = request.getParameter("id");

        browserItemsList = Service.getItems();
        root = new DefaultTreeNode("Root", null);
        createNode(browserItemsList, root);

        if (!isSelected && root.getChildCount() > 0) {
            root.getChildren().get(0).setSelected(true);
        }


//        makeTree(browserItemsList, root, id);
    }


    public void onNodeExpand(NodeExpandEvent event) throws IOException {
        new DefaultTreeNode(new BrowserItem("11","XXXX","sdsdasds"),event.getTreeNode() );

    }

    @Override
    public void encodeBegin(FacesContext arg0) throws IOException {
        super.encodeBegin(arg0);
    }

    private void createNode(List<BrowserItem> list, TreeNode node) {
        for (BrowserItem item : list) {

            TreeNode tempRoot = new DefaultTreeNode(item, node);
            if (item.getId().equals(requestId)) {
                tempRoot.setSelected(true);
                isSelected = true;
                TreeNode parent = tempRoot.getParent();
                while (parent != null) {
                    parent.setExpanded(true);
                    parent = parent.getParent();
                }
            }
            if (item.getChildren().size() != 0) {
                createNode(item.getChildren(), tempRoot);
            }
        }
    }

    private void makeTree(List<BrowserItem> list, TreeNode root, String id) {


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TreeNode getRoot() {
        return root;
    }

    public List<BrowserItem> getBrowserItemsList() {
        return browserItemsList;
    }
}