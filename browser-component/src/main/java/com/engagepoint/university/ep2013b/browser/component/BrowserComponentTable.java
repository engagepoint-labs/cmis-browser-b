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
    private BrowserItem selectedItem = null;

    // List which should be displayed
    private List<BrowserItem> dataList;
    private String searchCriteria = "none";
    private BrowserItem currentFolder = null;

    // Maximum of rows per page
    private static final int rowCounts = 2;

    private AdvSearchParams advancedSearchParams = new AdvSearchParams();


    public BrowserComponentTable()
    {
        System.out.println("BrowserComponentTable");

        service = BrowserFactory.getInstance("CMIS");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        folderId = request.getParameter("folderId");
        searchCriteria = request.getParameter("searchCriteria");

        String paramPageNum = request.getParameter("pageNum");

        if (paramPageNum == null || "".equals(paramPageNum)) pageNum = 1;
        else pageNum = Integer.parseInt(paramPageNum);
    }


    // Method executed when dataTable renders (during loading page or ajax request)
    public List<BrowserItem> getDataList()
    {
        System.out.println("getDataList()");
        System.out.println("folderID = " + folderId);
        System.out.println("page = " + pageNum);
        System.out.println("simple search = " + searchCriteria);
        System.out.println("advanced search:");
        System.out.println("\tDocumentType = " + advancedSearchParams.getDocumentType());
        System.out.println("\tDateFrom = " + advancedSearchParams.getDateFrom());
        System.out.println("\tDateTo = " + advancedSearchParams.getDateTo());
        System.out.println("\tContentType = " + advancedSearchParams.getContentType());
        System.out.println("\tSize = " + advancedSearchParams.getSize());
        System.out.println("\tText = " + advancedSearchParams.getText());


        if((folderId == null) || ("".equals(folderId))|| ("null".equals(folderId)))
        {
            // first time at the page
            currentFolder = service.findFolderByPath("/", 1, rowCounts);
        }
        else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);

        folderId = currentFolder.getId();


        if ((searchCriteria == null) && advancedSearchParams.isEmpty())
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
                item = service.advancedSearch(folderId, advancedSearchParams, pageNum, rowCounts);
            }

            pagesCount = item.getTotalPages();
            dataList = item.getChildren();
        }

        return dataList;
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