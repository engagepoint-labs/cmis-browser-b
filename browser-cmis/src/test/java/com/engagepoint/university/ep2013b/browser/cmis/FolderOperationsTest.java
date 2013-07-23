package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FolderOperationsTest {

    private CMISBrowserService cmisBrowserService = new CMISBrowserService();


    @Test
    public void test0_find_metadata_typelist()
    {

        Map<String,String> types = cmisBrowserService.getTypeList("cmis:document");
        assertEquals(26, types.size());
    }


    @Ignore
    @Test
    public void test1_create_folder_positive()
    {

        BrowserItem item = cmisBrowserService.createFolder("100","test_folder_b","cmis:folder");

        BrowserItem newItem = cmisBrowserService.findFolderById(item.getId());

        assertEquals(newItem.getName(), item.getName());

    }

    @Ignore
    @Test
    public void test2_create_folder_negative()
    {

        BrowserItem item = cmisBrowserService.createFolder("100","test_folder_b","cmis:folder");

        assertTrue(item.getId().equals(""));

    }


    @Ignore
    @Test
    public void test3_edit_folder_positive()
    {

        BrowserItem item = cmisBrowserService.editFolder("136", "test_folder_AA", "cmis:folder");

        BrowserItem newItem = cmisBrowserService.findFolderById(item.getId());

        assertEquals("test_folder_AA",newItem.getName());

    }

//
//    @Test
//    public void test4_edit_folder_negative()
//    {
//
//        BrowserItem  item = cmisBrowserService.editFolder("136", "test_folder_FF", "cmis:document");
//
//        assertTrue(item.getName().equals("test_folder_FF"));
//        assertNotSame("cmis:document", item.getType().name());
//
//
//    }
//

    @Ignore
    @Test
    public void test4_edit_folder_negative()
    {

        BrowserItem item = cmisBrowserService.editFolder("101", "test_folder_AA", "cmis:folder");
        BrowserItem newItem = cmisBrowserService.findFolderById("101");
        assertFalse(newItem.getName().equals("test_folder_AA"));

    }


    @Ignore
    @Test (expected = CmisObjectNotFoundException.class)
    public void test5_delete_folder_positive()
    {

        cmisBrowserService.deleteFolder("136");
        //assertTrue(cmisBrowserService.findFolderById("136").getId().equals(""));
        cmisBrowserService.findFolderById("136");

    }


    @Ignore
    @Test
    public void test6_delete_folder_negative()
    {

        cmisBrowserService.deleteFolder("101");
        assertTrue(cmisBrowserService.findFolderById("101").getId().equals("101"));

    }


}
