package com.engagepoint.university.ep2013b.browser.component;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;

import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private boolean showPanelButton;

    // List which should be displayed
    private List<BrowserItem> dataList;
    private String searchCriteria = "none";
    private BrowserItem currentFolder = null;

    // Maximum of rows per page
    private static final int rowCounts = 2;


    public BrowserComponentTable()
    {
        service = BrowserFactory.getInstance("CMIS");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        folderId = request.getParameter("folderId");
        String paramShowPanelButton = request.getParameter("showPanelButton");
        searchCriteria = request.getParameter("searchCriteria");

        String paramPageNum = request.getParameter("pageNum");

        if (paramPageNum == null || "".equals(paramPageNum)) pageNum = 1;
        else pageNum = Integer.parseInt(paramPageNum);

        if (paramShowPanelButton == null || "".equals(paramShowPanelButton)) showPanelButton = true;
        else showPanelButton = Boolean.parseBoolean(paramShowPanelButton);
    }


    // Method executed when dataTable renders (during loading page or ajax request)
    public List<BrowserItem> getDataList()
    {
        if((folderId == null) || ("".equals(folderId))|| ("null".equals(folderId)))
        {
            // first time at the page
            currentFolder = service.findFolderByPath("/", 1, rowCounts);
        }
        else currentFolder = service.findFolderById(folderId, pageNum, rowCounts);

        folderId = currentFolder.getId();


        if ((searchCriteria == null) || ("".equals(searchCriteria)))
        {
            // not searching
            pagesCount = service.getTotalPagesFromFolderById(folderId, rowCounts);
            dataList = currentFolder.getChildren();
        }
        else
        {
            // searching
            BrowserItem item  =  service.simpleSearch(folderId, searchCriteria, pageNum, rowCounts);
            //pagesCount = service.getTotalPagesFromSimpleSearch(folderId, searchCriteria, rowCounts);
            //dataList = service.simpleSearch(folderId, searchCriteria, pageNum, rowCounts);
            pagesCount = item.getTotalPages();
            dataList = item.getChildren();
        }

        return dataList;
    }

   // COMAND FOR RENDERED
    public boolean getRenderComponent(){
         if(isShowPanelButton() == true){
        return true;              }
        else{
            return false;
        }

    }





    public boolean isShowPanelButton() {
        return showPanelButton;
    }

    public void setShowPanelButton(boolean showPanelButton) {
        this.showPanelButton = showPanelButton;
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
        return ((searchCriteria == null) || "null".equals(searchCriteria)) ? "" : searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}