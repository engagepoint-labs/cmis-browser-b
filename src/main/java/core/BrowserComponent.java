package core;

import org.primefaces.component.tree.Tree;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/2/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesComponent("browserComponent")
public class BrowserComponent extends UIComponentBase implements NamingContainer {
     private TreeNode root;

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    //listner onexpand

    @Override
    public void encodeBegin(FacesContext arg0) throws IOException {

        root = new DefaultTreeNode("Root", null);
        List<BrowserItem> list = Service.getItems();
        for (BrowserItem browserItem : list) {
            new DefaultTreeNode(browserItem, root);
        }
        super.encodeBegin(arg0);
    }

    public TreeNode getRoot() {
        return root;
    }
}