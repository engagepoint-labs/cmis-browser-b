package com.engagepoint.university.ep2013b.browser.component.controller;


import com.engagepoint.university.ep2013b.browser.api.BrowserDocumentContent;
import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.api.PreferencesHelper;
import com.engagepoint.university.ep2013b.browser.cmis.SearchParams;
import com.engagepoint.university.ep2013b.browser.component.BrowserFactory;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class AbstractBrowserController implements BrowserController
{
	private BrowserService service;
	private String folderId;

	private static enum STATE
	{
		NORMAL,
		SEARCH_SIMPLE, SEARCH_ADVANCED,
		NEW_FOLDER, EDIT_FOLDER,
		NEW_DOCUMENT, EDIT_DOCUMENT
	}
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

	private Page page;// = new Page(2);	// Maximum of rows per number
	private SearchParams searchParams = new SearchParams();
	private FolderPanel folderPanel = new FolderPanel();
	private DocumentPanel documentPanel = new DocumentPanel();

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

		state = STATE.NORMAL;
		page = new Page(2);

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
		@Override
		public void showNew()
		{
			tempstate = state;
			state = STATE.NEW_FOLDER;
			name = "";
		}

		// Show panel in "Edit" mode
		@Override
		public void showEdit()
		{
			tempstate = state;
			state = STATE.EDIT_FOLDER;
			name = tree.getName();
		}

		@Override
		public void hide()
		{
			state = tempstate;
		}

		@Override
		public boolean isShow()
		{
			return (state == STATE.NEW_FOLDER) || (state == STATE.EDIT_FOLDER);
		}

		@Override
		public boolean isShowSaveButton()
		{
			return (state == STATE.NEW_FOLDER);
		}

		@Override
		public boolean isShowEditButton()
		{
			return (state == STATE.EDIT_FOLDER);
		}

		// Folder Operations
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

		@Override
		public void moveFolder(TreeDragDropEvent event)
		{
			TreeNode dragNode = event.getDragNode();
			TreeNode dropNode = event.getDropNode();

			BrowserItem source = (BrowserItem) dragNode.getData();
			BrowserItem target = (BrowserItem) dropNode.getData();

			service.moveFolder(source, target);
			updateTree();
		}

		// Setters and Getters
		@Override
		public String getName()	{ return name; }

		@Override
		public void setName(String name) { this.name = name; }

		@Override
		public String getType() { return type; }

		@Override
		public void setType(String type) { this.type = type; }
	}

	public FolderPanel getFolderPanel()
	{
		return folderPanel;
	}

	// Helper class for holding Document Management
	public class DocumentPanel implements BrowserDocumentPanel
	{
		private String name;
		private String type;
		private String id;
		private UploadedFile file;

		// Show panel in "Creation" mode
		@Override
		public void showNew()
		{
			tempstate = state;
			state = STATE.NEW_DOCUMENT;
			name = "";

			System.out.println("showNew = " + state.name());
		}

		// Show panel in "Edit" mode
		@Override
		public void showEdit(String id)
		{
			tempstate = state;
			state = STATE.EDIT_DOCUMENT;

			// find edited item
			BrowserItem find = new BrowserItem();
			find.setId(id);
			int index = table.getChildren().indexOf(find);

			this.id = id;

			if (index != -1)
			name = table.getChildren().get(index).getName();
		}

		@Override
		public void showDelete(String id)
		{
			this.id = id;
		}

		@Override
		public void hide()
		{
			state = tempstate;
			System.out.println("hide = " + state.name());
		}

		@Override
		public boolean isShow()
		{
			return (state == STATE.NEW_DOCUMENT) || (state == STATE.EDIT_DOCUMENT);
		}

		@Override
		public boolean isShowSaveButton()
		{
			return (state == STATE.NEW_DOCUMENT);
		}

		@Override
		public boolean isShowEditButton()
		{
			return (state == STATE.EDIT_DOCUMENT);
		}

		// Document Operations
		@Override
		public String createDocument(String link)
		{
			try
			{
				BrowserDocumentContent content = null;

				if (file != null)
				{
					content = new BrowserDocumentContent();

					content.setFilename(file.getFileName());
	//				TODO: content.setType(file.getContentType()) ?
					content.setType(type);
					content.setSize(file.getSize());
					content.setStream(file.getInputstream());
				}

				BrowserItem document = service.createDocument(folderId, name, content);

				page.first();
				hide();
				updateTable();
			}
			catch (Exception e)
			{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Error during creating document by name: " + name, null));
			}

			return link + "?faces-redirect=true";
		}

		@Override
		public String editDocument(String link) throws IOException
		{
			try
			{
				BrowserDocumentContent content = null;

				if (file != null)
				{
					content = new BrowserDocumentContent();

					content.setFilename(file.getFileName());
					content.setType(type);
					content.setSize(file.getSize());
					content.setStream(file.getInputstream());
				}

				BrowserItem document = service.editDocument(id, name, content);

				hide();
				updateTable();
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
		public void deleteDocument(String link)
		{
//			try
//			{
				service.deleteDocument(id);
//			}
//			catch (Exception e)
//			{
//				FacesContext.getCurrentInstance().addMessage(null,
//						new FacesMessage(FacesMessage.SEVERITY_WARN,
//								"Error during deleting folder " + tree.getName(), null));
//			}

			updateTable();
		}

		// Setters and Getters
		@Override
		public String getName()	{ return name; }

		@Override
		public void setName(String name) { this.name = name; }

		@Override
		public String getType() { return type; }

		@Override
		public void setType(String type) { this.type = type; }

		@Override
		public UploadedFile getFile() { return file; }

		@Override
		public void setFile(UploadedFile file) { this.file = file; }
	}

	public DocumentPanel getDocumentPanel()
	{
		return documentPanel;
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


    public String getCurrentUrl()
	{
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl)
	{
        this.currentUrl = currentUrl;
    }

    public void findLink(String link)
	{
        preferencesHelper.setCmisUrl(currentUrl);
        service.connect();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().getExternalContext().redirect(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
