package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

public class CMISBrowserServiceIT
{
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();

    @Test
    public void test_getTotalPagesFromFolderById()
    {
        int expected = 3;
        int actual = cmisBrowserService.getTotalPagesFromFolderById("101", 2);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTotalPagesFromFolderByPath()
    {
        int expected = 3;
        int actual = cmisBrowserService.getTotalPagesFromFolderByPath("/My_Folder-0-0", 2);

        assertEquals(expected, actual);
    }

    @Test
    public void test_findFolderById_paging()
    {
        BrowserItem item = cmisBrowserService.findFolderById("101", 2, 2);

        assertEquals("My_Document-1-2", item.getChildren().get(0).getName());
        assertEquals("My_Folder-1-0", item.getChildren().get(1).getName());
        assertEquals(2, item.getChildren().size());
        assertEquals("RootFolder", item.getParent().getName());
        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
        assertEquals(5, item.getParent().getChildren().size());
    }

    @Test
    public void test_findFolderByPath_paging()
    {
        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0", 2, 2);

        assertEquals("My_Document-1-2", item.getChildren().get(0).getName());
        assertEquals("My_Folder-1-0", item.getChildren().get(1).getName());
        assertEquals(2, item.getChildren().size());
        assertEquals("RootFolder", item.getParent().getName());
        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
        assertEquals(5, item.getParent().getChildren().size());
    }

    @Test
    public void test_findFolderById_without_paging()
    {
        BrowserItem item = cmisBrowserService.findFolderById("101");

        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
        assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
        assertEquals(5, item.getChildren().size());
        assertEquals("RootFolder", item.getParent().getName());
        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
        assertEquals(5, item.getParent().getChildren().size());
    }

    @Test
    public void test_findFolderByPath_without_paging()
    {
        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0");

        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
        assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
        assertEquals(5, item.getChildren().size());
        assertEquals("RootFolder", item.getParent().getName());
        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
        assertEquals(5, item.getParent().getChildren().size());
    }

    @Test
    public void test_getCurrentLocationById()
    {
        String expected = "/My_Folder-0-0";
        String actual = cmisBrowserService.getCurrentLocationById("101");

        assertEquals(expected, actual);

    }

    @Test
    public void test_simpleSearch_findlist_size()
    {
       BrowserItem item = cmisBrowserService.simpleSearch("101", "-1",1,19);

        assertEquals(3, item.getChildren().size());
    }

    @Test
    public void test_simpleSearch_findlist_totalpages()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "-1",1,19);

        assertEquals(1, item.getTotalPages());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_id()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("0", "-1",1,19);

        assertEquals(0, item.getChildren().size());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_str()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",1,19);

         assertEquals(3, item.getChildren().size());
    }

    @Test
    public void test_simpleSearch_valid_data()
    {
        BrowserItem item  = cmisBrowserService.simpleSearch("101", "_",1,19);

        assertEquals("My_Document-1-0", item.getChildren().get(2).getName());
    }

    @Test
    public void test_simpleSearch_empty_page()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("0", "-1",2,19);

        assertEquals(0, item.getChildren().size());
    }


    @Test
    public void test_simpleSearch_valid_page()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",2,2);

        assertEquals(1, item.getChildren().size());
    }


    @Test
    public void test_simpleSearch_valid_last_page()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",3,1);

        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
    }


    @Test
    public void test_simpleSearch_invalid_totalpages()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",9,1);

        assertNotSame(4, item.getTotalPages());
    }


    @Test
    public void test_simpleSearch_invalid_totalpages2()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "older",1,4);

        assertEquals(0, item.getTotalPages());
    }

}
