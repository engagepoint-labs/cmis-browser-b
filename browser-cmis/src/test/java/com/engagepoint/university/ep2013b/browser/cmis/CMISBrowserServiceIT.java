package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class CMISBrowserServiceIT {
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();





    @Test
    public void test_findFolderById_getName() throws Exception {

      String id="108";
       BrowserItem item = cmisBrowserService.findFolderById(id,false);
        assertEquals("My_Folder-1-1", item.getName());
        assertEquals("My_Document-2-0", item.getChildren().get(0).getName());
        assertEquals("My_Folder-0-0",item.getParent().getName());
        assertEquals("My_Document-1-0",item.getParent().getChildren().get(0).getName());
        assertEquals("RootFolder",item.getParent().getParent().getName());
        assertEquals("My_Document-0-0",item.getParent().getParent().getChildren().get(0).getName());

    }

    @Test
    public void test_findFolderById_getChildren_not_empty() throws Exception {

        String id="108";
        BrowserItem item = cmisBrowserService.findFolderById(id,false);
        assertFalse(item.getChildren().isEmpty());
    }

   // }

    @Test
    public void test_findFolderByPath() throws Exception{

        String path="/My_Folder-0-0/My_Folder-1-1";
        BrowserItem item = cmisBrowserService.findFolderByPath(path,false);
        assertEquals("My_Folder-1-1", item.getName());
        assertEquals("My_Document-2-0", item.getChildren().get(0).getName());
        assertEquals("My_Folder-0-0",item.getParent().getName());
        assertEquals("My_Document-1-0",item.getParent().getChildren().get(0).getName());
        assertEquals("RootFolder",item.getParent().getParent().getName());
        assertEquals("My_Document-0-0",item.getParent().getParent().getChildren().get(0).getName());


    }



}
