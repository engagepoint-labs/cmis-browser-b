package com.engagepoint.university.ep2013b.browser.component;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
* Created with IntelliJ IDEA.
* User: vladimir.ovcharov
* Date: 7/2/13
* Time: 3:17 PM
* To change this template use File | Settings | File Templates.
*/
@FacesComponent("browserComponentTree")
public class BrowserComponentTree extends UINamingContainer {
    private TreeNode root;
    private boolean isSelected = false;
    private BrowserService service;
    private String folderId;


    public BrowserComponentTree()
    {
        service = BrowserFactory.getInstance("CMIS");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        folderId = request.getParameter("folderId");

        BrowserItem currentFolder = null;
        if (folderId == null)
        {
            currentFolder = service.findFolderByPath("/");
            folderId = currentFolder.getId();
        }
        else currentFolder = service.findFolderById(folderId);


        root = new DefaultTreeNode("Root", null);
        // create tree from RootFolder (for showing all parents of current folder)
        BrowserItem rootFolder = getRootFolder(currentFolder);
        makeTree(rootFolder, root);
    }

    // Find Root from any folder
    private BrowserItem getRootFolder(BrowserItem item)
    {
        while (item.getParent() != null)
        {
            item = item.getParent();
        }

        return item;
    }

    // fill tree recursively
    private void makeTree(BrowserItem item, TreeNode parent)
    {
        TreeNode node = new DefaultTreeNode(item, parent);

        // if selected folder, than select it and expand all parents
        if (item.getId().equals(folderId))
        {
            node.setSelected(true);

            for (TreeNode i = node; i != null; i = i.getParent())
            {
                i.setExpanded(true);
            }
        }

        for (BrowserItem child : item.getChildren())
        {
            // tree includes only folders
            if (child.getType() == BrowserItem.TYPE.FOLDER)
            {
                makeTree(child, node);
            }
        }
    }


    public TreeNode getRoot() {
        return root;
    }

}