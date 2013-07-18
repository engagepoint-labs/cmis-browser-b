package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class FolderOperationsIT {

    private CMISBrowserService cmisBrowserService = new CMISBrowserService();


    @Test
    public void test_find_metadata_typelist()
    {

        Map<String,String> types = cmisBrowserService.getTypeList("cmis:document");
        assertEquals(types.isEmpty(),false);
    }


    @Test
    public void test_create_folder_positive()
    {

        BrowserItem  item = cmisBrowserService.createFolder("100","test_folder_b","cmis:folder");

        BrowserItem newItem = cmisBrowserService.findFolderById(item.getId());

        assertEquals(newItem.getName(), item.getName());

    }


    @Test
    public void test_create_folder_negative()
    {

        BrowserItem  item = cmisBrowserService.createFolder("100","test_folder_b","cmis:folder");

        assertTrue(item.getId().equals(""));

    }


    @Ignore
    @Test
    public void test_get_types_list_positive()
    {

//        Map<String,String> types = cmisBrowserService.getTypeList("cmis:document");
//        assertEquals(types.isEmpty(),false);

    }



}
