package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CMISBrowserServiceIT
{
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();

//	@Ignore
    @Test
    public void test_getTotalPagesFromFolderById()
    {
        BrowserItem item = cmisBrowserService.findTableById("101", 1, 2);

        int expected = 3;
        int actual = item.getTotalPages();

        assertEquals(expected, actual);
    }
//
//	@Ignore
//    @Test
//    public void test_getTotalPagesFromFolderByPath()
//    {
//        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0", 1, 2);
//
//        int expected = 3;
//        int actual = item.getTotalPages();
//
//        assertEquals(expected, actual);
//    }
//
//	@Ignore
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
//	@Ignore
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
//	@Ignore
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
//	@Ignore
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
//	@Ignore
//	@Test
//	public void test_findFolderById_sdfsd()
//	{
//		BrowserItem item = cmisBrowserService.findFolderById("101", 1, 2);
//
//		assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
//		assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
//		assertEquals(2, item.getChildren().size());
//	}
}
