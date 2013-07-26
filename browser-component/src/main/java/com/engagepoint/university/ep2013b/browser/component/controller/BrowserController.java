package com.engagepoint.university.ep2013b.browser.component.controller;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.cmis.SearchParams;
import org.apache.chemistry.opencmis.client.runtime.util.AbstractPageFetcher;
import org.primefaces.model.TreeNode;

import java.util.List;

public interface BrowserController
{
	// Initialize
	public void init();

    // Tree
	public TreeNode getRoot();
	public String getCurrentLocation();
	public TreeNode getSelectedNode();
	public void setSelectedNode(TreeNode selectedNode);

    // Table
	public List<BrowserItem> getDataList();
	public String getFolderId();

	// Search
	public String getSearchCriteria();
	public void setSearchCriteria(String searchCriteria);
	public SearchParams getSearchParams();
	public void setSearchParams(SearchParams advancedSearchParams);
	public void searchSimple();
	public void searchAdvanced();

	// Pagination
	public interface BrowserPage
	{
		public void first();
		public void next();
		public void previous();
		public void last();

		public boolean isPrevious();
		public boolean isNext();
	}

	public BrowserPage getPage();

	// Folder Management
	public interface BrowserFolderPanel
	{
		public void showNewFolderPanel();
		public void showEditFolderPanel();
		public void hide();
		public boolean isShow();
		public boolean isShowSaveButton();
		public boolean isShowEditButton();

		public String createFolder(String link);
		public String editFolder(String link);
		public void deleteFolder(String link);

		public String getName();
		public void setName(String name);
		public String getType();
		public void setType(String type);
	}

	public BrowserFolderPanel getFolderPanel();

}

