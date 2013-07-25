package com.engagepoint.university.ep2013b.browser.component.controller;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.cmis.SearchParams;
import org.apache.chemistry.opencmis.client.runtime.util.AbstractPageFetcher;
import org.primefaces.model.TreeNode;

import java.util.List;

public interface BrowserController
{
	public void first();
	public void next();
	public void previous();
	public void last();

	public boolean isPrevious();
	public boolean isNext();

	void init();

    // Tree
    TreeNode getRoot();
    String getCurrentLocation();
    TreeNode getSelectedNode();
    void displaySelectedSingle();
    void deleteNode();


    // Table
    List<BrowserItem> getDataList();
    String getFolderId();
    BrowserItem getSelectedItem();
    void setSelectedItem(BrowserItem selectedItem);
    String getSearchCriteria();
    void setSearchCriteria(String searchCriteria);
    SearchParams getSearchParams();
    void setSearchParams(SearchParams advancedSearchParams);
    void searchSimple();
	public void searchAdvanced();

    public void showPanel(int flag);
    public void hidePanel();
    public boolean isShowEditFolderPanel();

    public BrowserItem getNewFolderItem();

    public void setNewFolderItem(BrowserItem newFolderItem);

    public String createFolder(String link);
    public String editFolder(String link);
    public void deleteFolder(String link);
    public void setNameNewFolder(String name);
    public String getNameNewFolder();
    public  void setOperationFlag(int operationFlag);
    public int   getOperationFlag();
    public void onNodeExpand();
}

