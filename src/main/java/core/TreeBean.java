package core;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/26/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;
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

    @ManagedProperty("#{listBean}")
    private ListBean listBean;

    public TreeBean() {
    }

    public void init(ComponentSystemEvent event) {
        root = new DefaultTreeNode("Root", null);

        TreeNode node0 = new DefaultTreeNode(new User(1L, "root", "Ivanov"), root);
        TreeNode node1 = new DefaultTreeNode(new User(4L, "root2", "Timkov"), root);
        TreeNode node2 = new DefaultTreeNode(new User(6L, "root3", "Panov"), root);

        TreeNode node01 = new DefaultTreeNode(new User(2L, "rootChild1", "Ivanov"), node0);
        TreeNode node02 = new DefaultTreeNode(new User(3L, "rootChild2", "Petrov"), node0);

        TreeNode node10 = new DefaultTreeNode(new User(5L, "root2Child1", "Senkov"), node1);

        node1.setSelected(true);

//        for (User user : listBean.getUsers()) {
//
//            if(id.equals(user.getId())) {
//
//            }
//        }
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
}
