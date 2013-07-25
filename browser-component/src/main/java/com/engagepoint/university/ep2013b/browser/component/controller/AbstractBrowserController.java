package com.engagepoint.university.ep2013b.browser.component.controller;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.cmis.AdvSearchParams;
import com.engagepoint.university.ep2013b.browser.component.BrowserFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AbstractBrowserController implements BrowserController
{
	private BrowserService service;
	private String folderId;

	// Tree
	private TreeNode root;
	private String currentLocation;
	private TreeNode selectedNode;

	// Table
	private Page page = new Page(2);			// Maximum of rows per number
	private BrowserItem selectedItem = null;

	// List which should be displayed
	private List<BrowserItem> dataList;
	private String searchCriteria = "none";

	BrowserItem table, tree;

	private AdvSearchParams advancedSearchParams = new AdvSearchParams();

	private boolean showEditFolderPanel = false;

	private BrowserItem newFolderItem = new BrowserItem();
	private String nameNewFolder = "";
	private int operationFlag = 0;


	private class Page
	{
		private int number = 1;		// current number
		private int total = 1;		// total pages
		private int max;			// items per number

		public Page (int max)	{ this.max = max; }
	}

	public void first()		{ page.number = 1;			updateTable();}
	public void next()		{ page.number++; 			updateTable();}
	public void previous()	{ page.number--;			updateTable();}
	public void last()		{ page.number = page.total;	updateTable();}

	public boolean isPrevious()	{ return page.number > 1; }
	public boolean isNext()		{ return page.number + 1 <= page.total; }

	public AbstractBrowserController()
	{
	}

	@PostConstruct
	public void init()
	{
		service = BrowserFactory.getInstance("CMIS");

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		folderId = request.getParameter("folderId");

		System.out.println("init()");

		updateTable();
		updateTree();
	}

	private void updateTable()
	{
		System.out.println("updateTable()");

		table = (folderId == null) ?
				service.findTableByPath("/", 1, page.max) :
				service.findTableById(folderId, page.number, page.max);

		page.total = table.getTotalPages();
		dataList = table.getChildren();

		System.out.println("\tfolderId = " + folderId);
		System.out.println("\tpage.number = " + page.number);
		System.out.println("\tpage.total = " + page.total);
	}

	private void updateTree()
	{
		System.out.println("updateTree()");

		tree = (folderId == null) ?
				service.findTreeByPath("/") :
				service.findTreeById(folderId);

		folderId = tree.getId();

		root = new DefaultTreeNode("Root", null);
		makeTree(tree.getRootFolder(), root);

		currentLocation = tree.getLocation();

		System.out.println("\tfolderId = " + folderId);
		System.out.println("\tcurrentLocation = " + currentLocation);
	}


	public void showPanel(int flag)
	{
		showEditFolderPanel = true;
		operationFlag = flag;
	}

	public void hidePanel()
	{
		showEditFolderPanel = false;
	}

	public boolean isShowEditFolderPanel()
	{
		return showEditFolderPanel;
	}

	public BrowserItem getNewFolderItem()
	{
		return newFolderItem;
	}

	public void setNewFolderItem(BrowserItem newFolderItem)
	{
		this.newFolderItem = newFolderItem;
	}


	// fill tree recursively
	public void makeTree(BrowserItem item, TreeNode parent)
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

	public TreeNode getRoot()
	{
		return root;
	}

	public String getCurrentLocation()
	{
		return currentLocation;
	}

	public TreeNode getSelectedNode()
	{
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode)
	{
		this.selectedNode = selectedNode;
	}

	public void displaySelectedSingle()
	{
		if (selectedNode != null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	//  delete Folder
	public void deleteNode()
	{
		selectedNode.getChildren().clear();
		selectedNode.getParent().getChildren().remove(selectedNode);
		selectedNode.setParent(null);

		selectedNode = null;
	}

//	// TODO: Should have better name
//	// Process received parameters and decided what data to show (folder items or search results)
//	public void businessLogic()
//	{
////        System.out.println("businessLogic()");
////        System.out.println("\tfolderID       = " + folderId);
////        System.out.println("\tpage           = " + pageNum);
////        System.out.println("\tsimple search  = " + searchCriteria);
//
//		if ((searchCriteria == null) && advancedSearchParams.isEmpty())
//		{
//			// not searching
//			if ((folderId == null) || ("".equals(folderId)) || ("null".equals(folderId)))
//			{
//				// first time at the number
//				currentFolder = service.findFolderByPath("/", 1, rowCounts);
//			} else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);
//
//			folderId = currentFolder.getId();
//		} else
//		{
//			if ((searchCriteria != null))
//			{
//				// simple search
//				currentFolder = service.simpleSearch(folderId, searchCriteria, pageNum, rowCounts);
//			} else
//			{
//				// advanced search
//				advancedSearchParams.setFolderId(folderId);
//
//				System.out.println("\tadvanced search (isEmpty = " + advancedSearchParams.isEmpty() + "):");
//				System.out.println("\t\tid           = " + advancedSearchParams.getFolderId());
//				System.out.println("\t\tDocumentType = " + advancedSearchParams.getDocumentType());
//				System.out.println("\t\tDateFrom     = " + advancedSearchParams.getDateFrom());
//				System.out.println("\t\tDateTo       = " + advancedSearchParams.getDateTo());
//				System.out.println("\t\tContentType  = " + advancedSearchParams.getContentType());
//				System.out.println("\t\tSize         = " + advancedSearchParams.getSize());
//				System.out.println("\t\tText         = " + advancedSearchParams.getText());
//
//				currentFolder = service.advancedSearch(folderId, advancedSearchParams, pageNum, rowCounts);
//			}
//		}
//
//		pagesCount = currentFolder.getTotalPages();
//		dataList = currentFolder.getChildren();
//	}

	public void simple()
	{
//        state.put("searchCriteria", searchCriteria);
////        state.put("advancedSearch", advancedSearchParams);
//        state.put("pageNum", 1);
	}


	// Method executed when dataTable renders (during loading number or ajax request)
	public List<BrowserItem> getDataList()
	{
		return dataList;
	}

	public String getFolderId()
	{
		return folderId;
	}


	public BrowserItem getSelectedItem()
	{
		return selectedItem;
	}

	public void setSelectedItem(BrowserItem selectedItem)
	{
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

	public AdvSearchParams getAdvancedSearchParams()
	{
		return advancedSearchParams;
	}

	public void setAdvancedSearchParams(AdvSearchParams advancedSearchParams)
	{
		this.advancedSearchParams = advancedSearchParams;
	}

	@Override
	public String createFolder(String link)
	{

		try
		{

			BrowserItem newFolder = service.createFolder(folderId, newFolderItem.getName(), "cmis:folder");
			showEditFolderPanel = false;
			//System.out.println("" + link + "?faces-redirect=true");
			init();
			//newFolderItem = "";
		}
		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Error during creating folder by name: " + newFolderItem.getName(), null));
		}

		return link + "?faces-redirect=true";
	}

	@Override
	public String editFolder(String link)
	{

		try
		{
			BrowserItem newFolder = service.editFolder(folderId, newFolderItem.getName(), "cmis:folder");
			showEditFolderPanel = false;
			//System.out.println("" + link + "?faces-redirect=true");
			init();
			//nameNewFolder = "";
		}
		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Error during editing folder by name: " + newFolderItem.getName(), null));
		}
		return link + "?faces-redirect=true";

	}


	@Override
	public void deleteFolder(String link)
	{

//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//
//		BrowserItem parent = service.findFolderById(folderId).getParent();
//
//		try
//		{
//
//			service.deleteFolder(folderId);
//			showEditFolderPanel = false;
//
//			//init();
//
//			//System.out.println("" + link + "?faces-redirect=true");
//
//		}
//		catch (Exception e)
//		{
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_WARN,
//							"Error during deleting folder by id: " + folderId, null));
//		}
//
//		System.out.println(" -------------      " + link + "?folderId=" + parent.getId() + "&faces-redirect=true");
//		folderId = parent.getId();
//
//		try
//		{
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			FacesContext.getCurrentInstance().getExternalContext().redirect(link + "?folderId=" + parent.getId());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}

	}


	@Override
	public void onNodeExpand()
	{

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Tree expanding for folder: " + folderId, null));
	}

	public String getNameNewFolder()
	{
		return nameNewFolder;
	}

	public void setNameNewFolder(String nameNewFolder)
	{
		this.nameNewFolder = nameNewFolder;
	}

	public int getOperationFlag()
	{
		return operationFlag;
	}

	public void setOperationFlag(int operationFlag)
	{
		this.operationFlag = operationFlag;
	}
}
