package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class CMISBrowserServiceIT {
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();



    private List<BrowserItem> makeList() {
        List<BrowserItem> list = new ArrayList<BrowserItem>();
        list.add(new BrowserItem("My_Folder-1-1"));
        list.add(new BrowserItem("My_Document-2-0"));
        list.add(new BrowserItem("My_Folder-0-0"));
        list.add(new BrowserItem("My_Document-1-0"));
        list.add(new BrowserItem("RootFolder"));
        list.add(new BrowserItem("My_Document-0-0"));
        return list;
    }


    @Test
    public void test_findFolderById() throws Exception {

      String id="108";
      assertEquals(makeList().get(0).getName(),cmisBrowserService.findFolderById(id).get(0).getName());
      assertEquals(makeList().get(1).getName(),cmisBrowserService.findFolderById(id).get(0).getChildren().get(0).getName());
      assertEquals(makeList().get(2).getName(),cmisBrowserService.findFolderById(id).get(0).getParent().getName());
      assertEquals(makeList().get(3).getName(),cmisBrowserService.findFolderById(id).get(0).getParent().getChildren().get(0).getName());
      assertEquals(makeList().get(4).getName(),cmisBrowserService.findFolderById(id).get(0).getParent().getParent().getName());
      assertEquals(makeList().get(5).getName(),cmisBrowserService.findFolderById(id).get(0).getParent().getParent().getChildren().get(0).getName());






    }

    @Test
    public void test_findFolderByPath() throws Exception{

        String path="/My_Folder-0-0/My_Folder-1-1";
        assertEquals(makeList().get(0).getName(),cmisBrowserService.findFolderByPath(path).get(0).getName());
        assertEquals(makeList().get(1).getName(),cmisBrowserService.findFolderByPath(path).get(0).getChildren().get(0).getName());
        assertEquals(makeList().get(2).getName(),cmisBrowserService.findFolderByPath(path).get(0).getParent().getName());
        assertEquals(makeList().get(3).getName(),cmisBrowserService.findFolderByPath(path).get(0).getParent().getChildren().get(0).getName());
        assertEquals(makeList().get(4).getName(),cmisBrowserService.findFolderByPath(path).get(0).getParent().getParent().getName());
        assertEquals(makeList().get(5).getName(),cmisBrowserService.findFolderByPath(path).get(0).getParent().getParent().getChildren().get(0).getName());

    }







}
