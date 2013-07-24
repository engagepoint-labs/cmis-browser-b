package com.engagepoint.university.ep2013b.browser.component;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vadym.karko
 * Date: 7/24/13
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BrowserController
{
    private BrowserService service;
    private String folderId = null;

    public class Page
    {
        public int max = 2;
        public int number = 1;
        public int total;

		public void first()		{ number = 1; }
        public void next()		{ number++; }
        public void previous()	{ number--; }
		public void last()		{ number = total; }
        public boolean isNextAllowed() { return number + 1 <= total; }
        public boolean isPrevAllowed() { return number > 1; }
    }

    private Page page = new Page();

    private List<BrowserItem> dataList;
    private BrowserItem current = null;


    public void init()
    {
		System.out.println("BrowserController.init()");

        service = BrowserFactory.getInstance("CMIS");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        setFolderId(request.getParameter("folderId"));

        if(getFolderId() == null)
        {
            current = service.findFolderByPath("/", 1, page.max);
        }
        else current = service.findFolderById(folderId, page.number, page.max);

        folderId = current.getId();
        page.total = current.getTotalPages();
        dataList = current.getChildren();
    }

    public List<BrowserItem> getDataList()
    {
        return dataList;
    }

    public String getFolderId()
    {
        return folderId;
    }

    public void setFolderId(String folderId)
    {
        this.folderId = ((folderId == null) || ("".equals(folderId)) || ("null".equals(folderId))) ? null : folderId;
    }

    public Page getPage() {
        return page;
    }
}
