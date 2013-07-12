package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.TablePage;
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
       TablePage page = cmisBrowserService.simpleSearch("101", "-1",1,19);

        assertEquals(5, page.getItems().size());
    }

    @Test
    public void test_simpleSearch_findlist_totalpages()
    {
        TablePage page = cmisBrowserService.simpleSearch("101", "-1",1,19);

        assertEquals(0, page.getTotalPages());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_id()
    {
        TablePage page = cmisBrowserService.simpleSearch("0", "-1",1,19);

        assertEquals(0, page.getItems().size());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_str()
    {
        TablePage page = cmisBrowserService.simpleSearch("101", "_",1,19);

         assertEquals(9, page.getItems().size());
    }

    @Test
    public void test_simpleSearch_valid_data()
    {
        TablePage page  = cmisBrowserService.simpleSearch("101", "_",1,19);

        assertEquals("My_Document-1-0", page.getItems().get(8).getName());
    }

    @Test
    public void test_simpleSearch_empty_page()
    {
        TablePage page = cmisBrowserService.simpleSearch("0", "-1",2,19);

        assertEquals(0, page.getItems().size());
    }


    @Test
    public void test_simpleSearch_valid_page()
    {
        TablePage page = cmisBrowserService.simpleSearch("101", "_",2,6);

        assertEquals(3, page.getItems().size());
    }


    @Test
    public void test_simpleSearch_valid_last_page()
    {
        TablePage page = cmisBrowserService.simpleSearch("101", "_",9,1);

        assertEquals("My_Document-1-0", page.getItems().get(0).getName());
    }


    @Test
    public void test_simpleSearch_invalid_totalpages()
    {
        TablePage page = cmisBrowserService.simpleSearch("101", "_",9,1);

        assertNotSame(4, page.getTotalPages());
    }


}
