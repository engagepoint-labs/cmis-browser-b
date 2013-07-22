package com.engagepoint.university.ep2013b.browser.component;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.cmis.AdvSearchParams;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FacesComponent("browserComponentTable")
public class BrowserComponentTable extends UINamingContainer
{
    private BrowserService service;
    private String folderId;
    private Integer pageNum;
    private int pagesCount;

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

        pageNum = state.get("pageNum", 1);
        businessLogic();
    }



    // TODO: Should have better name
    // Process received parameters and decided what data to show (folder items or search results)
    public void businessLogic()
    {
        searchCriteria = state.get("searchCriteria", null);
//        advancedSearchParams = state.get("advancedSearch", new AdvSearchParams());
        pageNum = state.get("pageNum", 1);

        System.out.println("businessLogic()");
        System.out.println("\tfolderID       = " + folderId);
        System.out.println("\tpage           = " + pageNum);
        System.out.println("\tsimple search  = " + searchCriteria);
        System.out.println("\tadvanced search = "+ advancedSearchParams);

        if ((searchCriteria == null) && (advancedSearchParams.isEmpty()))
        {
            // not searching

            if((folderId == null) || ("".equals(folderId))|| ("null".equals(folderId)))
            {
                // first time at the page
                currentFolder = service.findFolderByPath("/", 1, rowCounts);
            }
            else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);

            folderId = currentFolder.getId();

//            // old functions, new BrowserItem.totalPages introduced
//            int total = service.getTotalPagesFromFolderById(folderId, rowCounts);
//            currentFolder.setTotalPages(total);
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

        folderId = currentFolder.getId();
        pagesCount = currentFolder.getTotalPages();
        dataList = currentFolder.getChildren();
    }

    public void simple()
    {
        System.out.println("Simple Search button is clicked!");

        state.put("searchCriteria", searchCriteria);
//        state.put("advancedSearch", advancedSearchParams);
        state.put("pageNum", 1);

        businessLogic();
    }

//    public void searchAdvanced()
//    {
//        System.out.println("Advanced Search button is clicked!");
//
//        state.put("advancedSearch", advancedSearchParams);
//        state.put("pageNum", 1);
//
//        businessLogic();
//    }


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

//    public void setPageNum(Integer pageNum) {
//        this.pageNum = pageNum;
//    }
//
//    public Integer getPageNum() {
//        return pageNum;
//    }

    public boolean isPrevAllowed() {
        return pageNum > 1;
    }

    public boolean isNextAllowed() {
        return pageNum + 1 <= pagesCount;
    }

//    public int getNextPageNum() {
//        return pageNum + 1;
//    }
//
//    public int getPrevPageNum() {
//        return pageNum - 1;
//    }

//    public int getPagesCount() {
//        return pagesCount;
//    }



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