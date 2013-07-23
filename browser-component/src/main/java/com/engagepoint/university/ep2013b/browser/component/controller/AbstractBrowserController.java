package com.engagepoint.university.ep2013b.browser.component.controller;


import com.engagepoint.university.ep2013b.browser.api.BrowserFolder;
import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.cmis.AdvSearchParams;
import com.engagepoint.university.ep2013b.browser.component.BrowserFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractBrowserController implements BrowserController {

    private BrowserService service;
    private String folderId;

    // Tree
    private TreeNode root;
    private boolean isSelected = false;
    private String currentLocation;
    private TreeNode selectedNode;

    // Table
    private Integer pageNum;
    private int pagesCount;
    private BrowserItem selectedItem = null;

    // List which should be displayed
    private List<BrowserItem> dataList;
    private String searchCriteria = "none";
    private BrowserFolder currentFolder = null;

    // Maximum of rows per page
    private static final int rowCounts = 2;

    private AdvSearchParams advancedSearchParams = new AdvSearchParams();

    private boolean showEditFolderPanel = false;

    private String newFolderName;
    private String newFolderType;


    public void init() {
        System.out.println(" -------------  @PostConstruct  AbstractBrowserController   init()");

        service = BrowserFactory.getInstance("CMIS");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        folderId = request.getParameter("folderId");

        // Tree

        if ((folderId == null) || ("".equals(folderId))|| ("null".equals(folderId)))
        {
            currentFolder = service.findFolderByPath("/");
            folderId = currentFolder.getId();
        }
        else currentFolder = service.findFolderById(folderId);


        root = new DefaultTreeNode("Root", null);
        // create tree from RootFolder (for showing all parents of current folder)
        BrowserFolder rootFolder = getRootFolder(currentFolder);
        makeTree(rootFolder, root);
        currentLocation = service.getCurrentLocationById(folderId);


        // Table
        searchCriteria = request.getParameter("searchCriteria");

        String paramPageNum = request.getParameter("pageNum");

        if (paramPageNum == null || "".equals(paramPageNum)) pageNum = 1;
        else pageNum = Integer.parseInt(paramPageNum);


    }


    public void showPanel()
    {
        System.out.println("showPanel");
        showEditFolderPanel = true;
    }

    public void hidePanel()
    {
        System.out.println("hidePanel");
        showEditFolderPanel = false;
    }

    public boolean isShowEditFolderPanel()
    {
        return showEditFolderPanel;
    }

    public String getNewFolderName() {
        return newFolderName;
    }

    public void setNewFolderName(String newFolderName) {
        this.newFolderName = newFolderName;
    }

    public String getNewFolderType() {
        return newFolderType;
    }

    public void setNewFolderType(String newFolderType) {
        this.newFolderType = newFolderType;
    }

    public void saveNewFolder()
    {
        System.out.println("Save Folder - success");
        System.out.println("name = " + newFolderName);
        System.out.println("type = " + newFolderType);
        service.createFolder(folderId, newFolderName, newFolderType);

        showEditFolderPanel = false;
    }



    // Find Root from any folder
    public BrowserFolder getRootFolder(BrowserFolder item)
    {
        while (item.getParent() != null)
        {
            item = item.getParent();
        }

        return item;
    }

    // fill tree recursively
    public void makeTree(BrowserFolder item, TreeNode parent)
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
                makeTree((BrowserFolder) child, node);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle() {
        if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //  delete Folder
    public void deleteNode() {
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);

        selectedNode = null;
    }

    // TODO: Should have better name
    // Process received parameters and decided what data to show (folder items or search results)
    public void businessLogic()
    {
        System.out.println("businessLogic()");
        System.out.println("\tfolderID       = " + folderId);
        System.out.println("\tpage           = " + pageNum);
        System.out.println("\tsimple search  = " + searchCriteria);

        if ((searchCriteria == null) && advancedSearchParams.isEmpty())
        {
            // not searching
            if((folderId == null) || ("".equals(folderId))|| ("null".equals(folderId)))
            {
                // first time at the page
                currentFolder = service.findFolderByPath("/", 1, rowCounts);
            }
            else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);

            folderId = currentFolder.getId();
        }
        else
        {
            if ((searchCriteria != null))
            {
                // simple search
                currentFolder = service.simpleSearch(folderId, searchCriteria, pageNum, rowCounts);
            }
            else
            {
                // advanced search
                advancedSearchParams.setFolderId(folderId);

                System.out.println("\tadvanced search (isEmpty = "+ advancedSearchParams.isEmpty() +"):");
                System.out.println("\t\tid           = " + advancedSearchParams.getFolderId());
                System.out.println("\t\tDocumentType = " + advancedSearchParams.getDocumentType());
                System.out.println("\t\tDateFrom     = " + advancedSearchParams.getDateFrom());
                System.out.println("\t\tDateTo       = " + advancedSearchParams.getDateTo());
                System.out.println("\t\tContentType  = " + advancedSearchParams.getContentType());
                System.out.println("\t\tSize         = " + advancedSearchParams.getSize());
                System.out.println("\t\tText         = " + advancedSearchParams.getText());

                currentFolder = service.advancedSearch(folderId, advancedSearchParams, pageNum, rowCounts);
            }
        }

        pagesCount = currentFolder.getTotalPages();
        dataList = currentFolder.getChildren();
    }

    public void simple()
    {
//        state.put("searchCriteria", searchCriteria);
////        state.put("advancedSearch", advancedSearchParams);
//        state.put("pageNum", 1);

        businessLogic();
    }


    // Method executed when dataTable renders (during loading page or ajax request)
    public List<BrowserItem> getDataList()
    {
        businessLogic();
        return dataList;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public boolean isPrevAllowed() {
        return pageNum > 1;
    }

    public boolean isNextAllowed() {
        return pageNum + 1 <= pagesCount;
    }

    public void firstPage()
    {
        pageNum = 1;
        businessLogic();
    }

    public void nextPage()
    {
        pageNum++;
        businessLogic();
    }

    public void prevPage()
    {
        pageNum--;
        businessLogic();
    }

    public void lastPage()
    {
        pageNum = pagesCount;
        businessLogic();
    }
    public int getNextPageNum() {
        return pageNum + 1;
    }

    public int getPrevPageNum() {
        return pageNum - 1;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public BrowserItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(BrowserItem selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSearchCriteria()
    {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria)
    {
        this.searchCriteria = ((searchCriteria == null) || "".equals(searchCriteria) || "null".equals(searchCriteria)) ? null : searchCriteria;
    }

    public AdvSearchParams getAdvancedSearchParams() {
        return advancedSearchParams;
    }

    public void setAdvancedSearchParams(AdvSearchParams advancedSearchParams) {
        this.advancedSearchParams = advancedSearchParams;
    }
}
