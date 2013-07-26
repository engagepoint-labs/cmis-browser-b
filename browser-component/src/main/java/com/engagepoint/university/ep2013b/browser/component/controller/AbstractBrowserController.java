package com.engagepoint.university.ep2013b.browser.component.controller;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.api.PreferencesHelper;
import com.engagepoint.university.ep2013b.browser.cmis.SearchParams;
import com.engagepoint.university.ep2013b.browser.component.BrowserFactory;
import org.primefaces.event.TreeDragDropEvent;
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

	private static enum STATE { NORMAL, SEARCH_SIMPLE, SEARCH_ADVANCED, NEW_FOLDER, EDIT_FOLDER }
	private STATE state = STATE.NORMAL;
	private STATE tempstate = STATE.NORMAL;	// remember state before opening Folder Panel

	// Tree
	private TreeNode root;
	private String currentLocation;
	private TreeNode selectedNode;


	// List which should be displayed
	private List<BrowserItem> dataList;
	private String searchCriteria = "";

	private BrowserItem table, tree;

	private Page page = new Page(2);	// Maximum of rows per number
	private SearchParams searchParams = new SearchParams();
	private FolderPanel folderPanel = new FolderPanel();

    private String currentUrl = null;
    private PreferencesHelper preferencesHelper;


    public AbstractBrowserController()
	{
	}

	@PostConstruct
	public void init()
	{
		service = BrowserFactory.getInstance("CMIS");

        preferencesHelper = new PreferencesHelper();
        currentUrl = preferencesHelper.getCmisUrl("http://localhost:18080/server/services/");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		folderId = request.getParameter("folderId");

		System.out.println("init()");

		updateTable();
		updateTree();
	}

	// Updating Table according to current state
	private void updateTable()
	{
		System.out.println("updateTable()");

		switch (state)
		{
			case NORMAL:
			{
				table = (folderId == null) ?
						service.findTableByPath("/", 1, page.max) :
						service.findTableById(folderId, page.number, page.max);
			}
			break;

			case SEARCH_SIMPLE:		table = service.simpleSearch(folderId, searchCriteria, page.number, page.max);	break;
			case SEARCH_ADVANCED:	table = service.advancedSearch(folderId, searchParams, page.number, page.max);	break;
		}

		page.total = table.getTotalPages();
		dataList = table.getChildren();

		System.out.println("\tstate       = " + state.name());
		System.out.println("\tfolderId    = " + folderId);
		System.out.println("\tpage.number = " + page.number);
		System.out.println("\tpage.total  = " + page.total);
	}

	// Updating Tree according to current state
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

		System.out.println("\tstate    = " + state.name());
		System.out.println("\tfolderId = " + folderId);
		System.out.println("\tlocation = '" + currentLocation + "'");
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

	// Helper class for holding Paging Management
	public class Page implements BrowserPage
	{
		private int number = 1;		// current number
		private int total = 1;		// total pages
		private int max;			// items per number

		public Page (int max)	{ this.max = max; }

		public void first()		{ number = 1;		updateTable();}
		public void next()		{ number++; 		updateTable();}
		public void previous()	{ number--;			updateTable();}
		public void last()		{ number = total;	updateTable();}

		public boolean isPrevious()	{ return number > 1; }
		public boolean isNext()		{ return number + 1 <= total; }
	}

	@Override
	public BrowserPage getPage()
	{
		return page;
	}

	// Helper class for holding Folder Management
	public class FolderPanel implements BrowserFolderPanel
	{
		private String name;
		private String type;

		// Show panel in "Creation" mode
		public void showNewFolderPanel()
		{
			tempstate = state;
			state = STATE.NEW_FOLDER;
			name = "";
		}

		// Show panel in "Edit" mode
		public void showEditFolderPanel()
		{
			tempstate = state;
			state = STATE.EDIT_FOLDER;
			name = tree.getName();
		}

		public void hide()
		{
			state = tempstate;
		}

		public boolean isShow()
		{
			return (state == STATE.NEW_FOLDER) || (state == STATE.EDIT_FOLDER);
		}

		public boolean isShowSaveButton()
		{
			return (state == STATE.NEW_FOLDER);
		}

		public boolean isShowEditButton()
		{
			return (state == STATE.EDIT_FOLDER);
		}

		@Override
		public String createFolder(String link)
		{
			try
			{
				BrowserItem folder = service.createFolder(folderId, name, type);
				hide();
				init();
			}
			catch (Exception e)
			{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Error during creating folder by name: " + name, null));
			}

			return link + "?faces-redirect=true";
		}

		@Override
		public String editFolder(String link)
		{
			try
			{
				BrowserItem folder = service.editFolder(folderId, name, type);
				hide();
				init();
			}
			catch (Exception e)
			{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Error during editing folder by name: " + name, null));
			}
			return link + "?faces-redirect=true";

		}


		@Override
		public void deleteFolder(String link)
		{
			BrowserItem parent = tree.getParent();

			try
			{
				service.deleteFolder(folderId);
				hide();
			}
			catch (Exception e)
			{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Error during deleting folder " + tree.getName(), null));
			}

			folderId = parent.getId();

			try
			{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().redirect(link + "?folderId=" + parent.getId());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getType()
		{
			return type;
		}

		public void setType(String type)
		{
			this.type = type;
		}
	}

	public FolderPanel getFolderPanel()
	{
		return folderPanel;
	}


	// Search methods
	public void searchSimple()
	{
		System.out.println("-- searchSimple() --------------- ");
		state = (searchCriteria != null) ? STATE.SEARCH_SIMPLE : STATE.NORMAL;
		page.number = 1;
		updateTable();
	}

	public void searchAdvanced()
	{
		System.out.println("-- searchAdvanced() --------------- ");
		System.out.println("params = \n" + searchParams);
		state = (!searchParams.isEmpty()) ? STATE.SEARCH_ADVANCED : STATE.NORMAL;
		page.number = 1;
		updateTable();
	}


	// Setters and Getters
	public List<BrowserItem> getDataList()
	{
		return dataList;
	}

	public String getSearchCriteria()
	{
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria)
	{
		this.searchCriteria = (!"".equals(searchCriteria)) ? searchCriteria : null;
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
//		folderId = ((BrowserItem)selectedNode.getData()).getId();
//		System.out.println("SelectedNode = " + folderId);
	}


	public String getFolderId()
	{
		return folderId;
	}

	public SearchParams getSearchParams()
	{
		return searchParams;
	}

	public void setSearchParams(SearchParams searchParams)
	{
		this.searchParams = searchParams;
	}

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public void findLink(String link) {
        preferencesHelper.setCmisUrl(currentUrl);
        service.connect();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().getExternalContext().redirect(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveFolder(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();

        BrowserItem source = (BrowserItem) dragNode.getData();
        BrowserItem target = (BrowserItem) dropNode.getData();

        service.moveFolder(source, target);
        updateTree();
    }
}
