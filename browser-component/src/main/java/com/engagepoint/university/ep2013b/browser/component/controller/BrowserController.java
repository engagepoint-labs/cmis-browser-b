package com.engagepoint.university.ep2013b.browser.component.controller;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.cmis.AdvSearchParams;

import org.primefaces.model.TreeNode;
import java.util.List;

public interface BrowserController {
    void init();

    // Tree
    BrowserItem getRootFolder(BrowserItem item);
    void makeTree(BrowserItem item, TreeNode parent);
    TreeNode getRoot();
    String getCurrentLocation();
    TreeNode getSelectedNode();
    void displaySelectedSingle();
    void deleteNode();

    // Table
    void businessLogic();
    List<BrowserItem> getDataList();
    String getFolderId();
    void setPageNum(Integer pageNum);
    Integer getPageNum();
    boolean isPrevAllowed();
    boolean isNextAllowed();
    int getNextPageNum();
    int getPrevPageNum();
    int getPagesCount();
    BrowserItem getSelectedItem();
    void setSelectedItem(BrowserItem selectedItem);
    String getSearchCriteria();
    void setSearchCriteria(String searchCriteria);
    AdvSearchParams getAdvancedSearchParams();
    void setAdvancedSearchParams(AdvSearchParams advancedSearchParams);
}
