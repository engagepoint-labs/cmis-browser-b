package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CMISBrowserServiceIT
{
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();

//    @Test
//    public void test_getTotalPagesFromFolderById()
//    {
//        int expected = 3;
//        int actual = cmisBrowserService.getTotalPagesFromFolderById("101", 2);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void test_getTotalPagesFromFolderByPath()
//    {
//        int expected = 3;
//        int actual = cmisBrowserService.getTotalPagesFromFolderByPath("/My_Folder-0-0", 2);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void test_findFolderById_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderById("101", 2, 2);
//
//        assertEquals("My_Document-1-2", item.getChildren().get(0).getName());
//        assertEquals("My_Folder-1-0", item.getChildren().get(1).getName());
//        assertEquals(2, item.getChildren().size());
//        assertEquals("RootFolder", item.getParent().getName());
//        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
//        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
//        assertEquals(5, item.getParent().getChildren().size());
//    }
//
//    @Test
//    public void test_findFolderByPath_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0", 2, 2);
//
//        assertEquals("My_Document-1-2", item.getChildren().get(0).getName());
//        assertEquals("My_Folder-1-0", item.getChildren().get(1).getName());
//        assertEquals(2, item.getChildren().size());
//        assertEquals("RootFolder", item.getParent().getName());
//        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
//        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
//        assertEquals(5, item.getParent().getChildren().size());
//    }
//
//    @Test
//    public void test_findFolderById_without_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderById("101");
//
//        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
//        assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
//        assertEquals(5, item.getChildren().size());
//        assertEquals("RootFolder", item.getParent().getName());
//        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
//        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
//        assertEquals(5, item.getParent().getChildren().size());
//    }
//
//    @Test
//    public void test_findFolderByPath_without_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0");
//
//        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
//        assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
//        assertEquals(5, item.getChildren().size());
//        assertEquals("RootFolder", item.getParent().getName());
//        assertEquals("My_Document-0-0", item.getParent().getChildren().get(0).getName());
//        assertEquals("My_Document-0-1", item.getParent().getChildren().get(1).getName());
//        assertEquals(5, item.getParent().getChildren().size());
//    }
//
//    @Test
//    public void test_getCurrentLocationById()
//    {
//        String expected = "/My_Folder-0-0";
//        String actual = cmisBrowserService.getCurrentLocationById("101");
//
//        assertEquals(expected, actual);
//
//    }

    @Test
    public void test_simpleSearch_findlist_size()
    {
        List<BrowserItem> list = cmisBrowserService.simpleSearch("101", "-1");

        assertEquals(9, list.size());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_id()
    {
        List<BrowserItem> list = cmisBrowserService.simpleSearch("0", "-1");

        assertEquals(0, list.size());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_str()
    {
        List<BrowserItem> list = cmisBrowserService.simpleSearch("101", "_");

         assertEquals(15, list.size());
    }

    @Test
    public void test_simpleSearch_valid_data()
    {
        List<BrowserItem> list = cmisBrowserService.simpleSearch("101", "_");

        assertEquals("My_Folder-2-0", list.get(11).getName());
    }

}
