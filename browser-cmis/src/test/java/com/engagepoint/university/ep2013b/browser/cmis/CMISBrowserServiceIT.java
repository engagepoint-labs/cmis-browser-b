package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class CMISBrowserServiceIT {
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();

    @Test
    public void test_findFolderById() throws Exception {
        cmisBrowserService.connect();

        boolean includeOnlyFolders = true;
        String id="108";

        String name = "/My_Folder-0-0/My_Folder-1-1";
        String name1 = "My_Folder-2-0";
        String name2 = "My_Folder-2-1";
        assertEquals(name,cmisBrowserService.findFolderById(id,includeOnlyFolders).get(0).getName());
        assertEquals(name1,cmisBrowserService.findFolderById(id,includeOnlyFolders).get(1).getName());
        assertEquals(name2,cmisBrowserService.findFolderById(id,includeOnlyFolders).get(2).getName());


    }

    @Test
    public void test_findFolderByPath(){
        cmisBrowserService.connect();

        boolean includeOnlyFolders = false;
        String path="/My_Folder-0-0/My_Folder-1-1";

        String name = "/My_Folder-0-0/My_Folder-1-1";
        String name1 = "My_Folder-2-0";
        String name2 = "My_Folder-2-1";
        String name3 = "My_Document-2-1";
        String name4 = "My_Document-2-0";
        String name5 = "My_Document-2-2";

        assertEquals(name,cmisBrowserService.findFolderByPath(path,includeOnlyFolders).get(0).getName());
        assertEquals(name1,cmisBrowserService.findFolderByPath(path,includeOnlyFolders).get(1).getName());
        assertEquals(name2,cmisBrowserService.findFolderByPath(path,includeOnlyFolders).get(2).getName());
        assertEquals(name3,cmisBrowserService.findFolderByPath(path,includeOnlyFolders).get(3).getName());
        assertEquals(name4,cmisBrowserService.findFolderByPath(path,includeOnlyFolders).get(4).getName());
        assertEquals(name5,cmisBrowserService.findFolderByPath(path,includeOnlyFolders).get(5).getName());

    }


    private List<BrowserItem> makeList() {
        List<BrowserItem> list = new ArrayList<BrowserItem>();
        list.add(new BrowserItem("My_Document-0-0"));
        list.add(new BrowserItem("My_Document-0-1"));
        list.add(new BrowserItem("My_Document-0-2"));
        list.add(new BrowserItem("My_Folder-0-0"));
        list.add(new BrowserItem("My_Folder-0-1"));
        return list;
    }

    @Test
    public void test_getRootFolder(){

         assertEquals(makeList().get(0).getName(),cmisBrowserService.getRootFolder().get(0).getName());
         assertEquals(makeList().get(1).getName(),cmisBrowserService.getRootFolder().get(1).getName());
         assertEquals(makeList().get(2).getName(),cmisBrowserService.getRootFolder().get(2).getName());
         assertEquals(makeList().get(3).getName(),cmisBrowserService.getRootFolder().get(3).getName());
         assertEquals(makeList().get(4).getName(),cmisBrowserService.getRootFolder().get(4).getName());

    }


}
