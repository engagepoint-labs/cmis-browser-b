package core;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/26/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "treeBean", eager = true)
@ViewScoped
public class TreeBean implements Serializable {
    private User user;
    private Long id;
    private String lastName;
    private String firstName;

    private TreeNode root;
    private TreeNode selectedNode;

    private List<TreeNode> listNodes = new ArrayList<TreeNode>();

    @ManagedProperty("#{listBean}")
    private ListBean listBean;

    public TreeBean() {
    }

    public void init(ComponentSystemEvent event) {
        root = new DefaultTreeNode("Root", null);
        createNode(listBean.getUsers(), root);
    }

    private void createNode(List<User> list, TreeNode node) {
        for (User user : list) {

            TreeNode tempRoot = new DefaultTreeNode(user, node);

            if (user.getChildren().size() != 0) {
                createNode(user.getChildren(), tempRoot);
            }
        }

        listNodes.get(1).setSelected(true);
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle(ActionEvent event) {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public ListBean getListBean() {
        return listBean;
    }

    public void setListBean(ListBean listBean) {
        this.listBean = listBean;
    }

    public List<TreeNode> getListNodes() {
        return listNodes;
    }

    public void setListNodes(List<TreeNode> listNodes) {
        this.listNodes = listNodes;
    }
}
