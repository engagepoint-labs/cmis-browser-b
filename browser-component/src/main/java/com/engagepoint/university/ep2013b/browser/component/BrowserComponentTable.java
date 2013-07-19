package com.engagepoint.university.ep2013b.browser.component;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.cmis.AdvSearchParams;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FacesComponent("browserComponentTable")
public class BrowserComponentTable extends UINamingContainer
{
    private BrowserService service;
    private String folderId;
    private Integer pageNum;
    private int pagesCount;
    private boolean showPanelButton = true;
    // List which should be displayed
    private List<BrowserItem> dataList;
    private String searchCriteria = "none";
    private BrowserItem currentFolder = null;

    // Maximum of rows per page
    private static final int rowCounts = 2;

    private AdvSearchParams advancedSearchParams = new AdvSearchParams();

    private StateManager state;


    public BrowserComponentTable()
    {
        System.out.println("Table");

        state = new StateManager(FacesContext.getCurrentInstance(), getClientId());
        service = BrowserFactory.getInstance("CMIS");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        folderId = request.getParameter("folderId");
//        String paramShowPanelButton = request.getParameter("showPanelButton");

        pageNum = state.get("pageNum", 1);
        businessLogic();

//        if (paramShowPanelButton == null || "".equals(paramShowPanelButton)) showPanelButton = true;
//        else showPanelButton = Boolean.parseBoolean(paramShowPanelButton);
    }



    // TODO: Should have better name
    // Process received parameters and decided what data to show (folder items or search results)
    public void businessLogic()
    {
        searchCriteria = state.get("searchCriteria", null);
//        advancedSearchParams = state.get("advancedSearch", new AdvSearchParams());
        pageNum = state.get("pageNum", 1);


        if((folderId == null) || ("".equals(folderId))|| ("null".equals(folderId)))
        {
            // first time at the page
            currentFolder = service.findFolderByPath("/", 1, rowCounts);
        }
        else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);

        folderId = currentFolder.getId();


        System.out.println("businessLogic()");
        System.out.println("\tmode           = " + state.get("mode"));
        System.out.println("\tfolderID       = " + folderId);
        System.out.println("\tpage           = " + pageNum);
        System.out.println("\tsimple search  = " + searchCriteria);
        System.out.println("\tadvanced search = "+ advancedSearchParams);


        if ((searchCriteria == null) && (advancedSearchParams.isEmpty()))
        {
            // not searching
            pagesCount = service.getTotalPagesFromFolderById(folderId, rowCounts);
            dataList = currentFolder.getChildren();
        }
        else
        {
            BrowserItem item;

            if ((searchCriteria != null))
            {
                // simple search

                item = service.simpleSearch(folderId, searchCriteria, pageNum, rowCounts);
            }
            else
            {
                // advanced search

                advancedSearchParams.setFolderId(folderId);
                System.out.println("\t\tid           = " + advancedSearchParams.getFolderId());
                System.out.println("\t\tDocumentType = " + advancedSearchParams.getDocumentType());
                System.out.println("\t\tDateFrom     = " + advancedSearchParams.getDateFrom());
                System.out.println("\t\tDateTo       = " + advancedSearchParams.getDateTo());
                System.out.println("\t\tContentType  = " + advancedSearchParams.getContentType());
                System.out.println("\t\tSize         = " + advancedSearchParams.getSize());
                System.out.println("\t\tText         = " + advancedSearchParams.getText());

                item = service.advancedSearch(folderId, advancedSearchParams, pageNum, rowCounts);
            }

            pagesCount = item.getTotalPages();
            dataList = item.getChildren();
        }


    }

    public void simple()
    {
        System.out.println("Simple Search button is clicked!");

        state.put("searchCriteria", searchCriteria);
//        state.put("advancedSearch", advancedSearchParams);
        state.put("pageNum", 1);

        businessLogic();
    }

    public void newFolder()
    {
        System.out.println("New Folder button is clicked!");

        showPanelButton = false;
        state.put("isShowNewFolder", showPanelButton);

        businessLogic();
    }



    // COMAND FOR RENDERED
    public boolean getRenderComponent()
    {
        Boolean isShow = state.get("isShowNewFolder", true);

        showPanelButton = isShow;

        System.out.println("getRenderComponent() = " + showPanelButton);

        return showPanelButton;
    }

    public void firstPage()
    {
        System.out.println("FirstPage button is clicked!");

        pageNum = 1;
        state.put("pageNum", pageNum);

        businessLogic();
    }

    public void nextPage()
    {
        System.out.println("NextPage button is clicked!");

        pageNum++;
        state.put("pageNum", pageNum);

        businessLogic();
    }

    public void prevPage()
    {
        System.out.println("PrevPage button is clicked!");

        pageNum--;
        state.put("pageNum", pageNum);

        businessLogic();
    }

    public void lastPage()
    {
        System.out.println("LastPage button is clicked!");

        pageNum = pagesCount;
        state.put("pageNum", pagesCount);

        businessLogic();
    }

    // Method executed when dataTable renders (during loading page or ajax request)
    public List<BrowserItem> getDataList()
    {
//        System.out.println("getDataList");
        return dataList;
    }

    public String getFolderId() {
        return folderId;
    }


    public boolean isPrevAllowed() {
        return pageNum > 1;
    }

    public boolean isNextAllowed() {
        return pageNum + 1 <= pagesCount;
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