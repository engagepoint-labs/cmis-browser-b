package com.engagepoint.university.ep2013b.browser.component;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@FacesComponent("browserComponentTable")
public class BrowserComponentTable extends UINamingContainer
{
    private BrowserService service;
    private String folderId;
    private Integer pageNum;
    private int pagesCount;
    private BrowserItem selectedItem = null;

    // List which should be displayed
    private List<BrowserItem> dataList;
    private String searchCriteria = "none";

    // Maximum of rows per page
    private static final int rowCounts = 2;


    public BrowserComponentTable()
    {
        System.out.println("BrowserComponentTable()");

        service = BrowserFactory.getInstance("CMIS");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        folderId = request.getParameter("folderId");
        searchCriteria = request.getParameter("searchCriteria");

        System.out.println("folder = " + folderId);
        System.out.println("pageNum = " + pageNum);
        System.out.println("searchCriteria = " + searchCriteria);

        String paramPageNum = request.getParameter("pageNum");

        if (paramPageNum == null || "".equals(paramPageNum)) pageNum = 1;
        else pageNum = Integer.parseInt(paramPageNum);


        BrowserItem currentFolder = null;
        if(folderId == null)
        {
            currentFolder = service.findFolderByPath("/", 1, rowCounts);
            folderId = currentFolder.getId();
        }
        else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);

        pagesCount = service.getTotalPagesFromFolderById(folderId, rowCounts);
        dataList = currentFolder.getChildren();
    }

    // Emulate search engine, returning fake data
    public List<BrowserItem> getSearchedList(String criteria)
    {
        List<BrowserItem> searchedList = new ArrayList<BrowserItem>();

        searchedList.add(new BrowserItem("id1", "Searched 1 for \"" + criteria + "\"", BrowserItem.TYPE.FOLDER, null, null));
        searchedList.add(new BrowserItem("id2", "Searched 2 for \"" + criteria + "\"", BrowserItem.TYPE.FOLDER, null, null));
        searchedList.add(new BrowserItem("id3", "Searched 3 for \"" + criteria + "\"", BrowserItem.TYPE.FOLDER, null, null));

        return searchedList;
    }

    // Method executed when dataTable renders (during loading page or ajax request)
    public List<BrowserItem> getDataList()
    {
        System.out.println("getDataList()");
        System.out.println("folder = " + folderId);
        System.out.println("pageNum = " + pageNum);
        System.out.println("searchCriteria = " + searchCriteria);

        if (("".equals(searchCriteria)) || (searchCriteria == null))
        {
            // non search
            return dataList;
        }

        // search
        return getSearchedList(searchCriteria);
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


    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}