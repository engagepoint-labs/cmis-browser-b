package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


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
//        assertEquals("RootFolder", item.getParent().getName());
//    }
//
//    @Test
//    public void test_findFolderByPath_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0", 2, 2);
//
//        assertEquals("My_Document-1-2", item.getChildren().get(0).getName());
//        assertEquals("My_Folder-1-0", item.getChildren().get(1).getName());
//        assertEquals("RootFolder", item.getParent().getName());
//    }
//
//    @Test
//    public void test_findFolderById_without_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderById("101", false);
//
//        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
//        assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
//        assertEquals("RootFolder", item.getParent().getName());
//    }
//
//    @Test
//    public void test_findFolderByPath_without_paging()
//    {
//        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0", false);
//
//        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
//        assertEquals("My_Document-1-1", item.getChildren().get(1).getName());
//        assertEquals("RootFolder", item.getParent().getName());
//    }

    @Test
    public void test_findFolderByPath_fsdfsdf()
    {
        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0", true);
//        BrowserItem item = cmisBrowserService.findFolderByPath("/My_Folder-0-0/My_Folder-1-0/My_Folder-2-0", true);

        System.out.println("Children");
        for (BrowserItem i : item.getChildren())
        {
            System.out.println(i);
        }

//        System.out.println("Parent 1 level");
//        for (BrowserItem i : item.getParent().getChildren())
//        {
//            System.out.println(i);
//        }
//
//        System.out.println("Parent 2 level");
//        for (BrowserItem i : item.getParent().getParent().getChildren())
//        {
//            System.out.println(i);
//        }
//
//        System.out.println("Children 1 Parent 2 level");
//        System.out.println(item.getParent().getParent().getChildren().get(0));
//
//        System.out.println("Childrens 1 Parent 2 level");
//        for (BrowserItem i : item.getParent().getParent().getChildren().get(0).getChildren())
//        {
//            System.out.println(i);
//        }
    }
}
