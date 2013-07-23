package com.engagepoint.university.ep2013b.browser.component.controller;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.cmis.AdvSearchParams;
import org.primefaces.model.TreeNode;

import java.util.List;

public interface BrowserController {
    void init();

    // Tree
    TreeNode getRoot();
    String getCurrentLocation();
    TreeNode getSelectedNode();
    void displaySelectedSingle();
    void deleteNode();

    // Table
    void businessLogic();
    List<BrowserItem> getDataList();
    String getFolderId();

    boolean isPrevAllowed();
    boolean isNextAllowed();

    String getSearchCriteria();
    void setSearchCriteria(String searchCriteria);
    AdvSearchParams getAdvancedSearchParams();
    void setAdvancedSearchParams(AdvSearchParams advancedSearchParams);
    void firstPage();
    void nextPage();
    void prevPage();
    void lastPage();
    void simple();

    public void showPanel();
    public void hidePanel();
    public boolean isShowEditFolderPanel();

    public String getNewFolderName();
    public void setNewFolderName(String name);

    public String getNewFolderType();
    public void setNewFolderType(String type);
    public void saveNewFolder();
}
