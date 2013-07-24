package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoveFolderTest {
    private CMISBrowserService service = new CMISBrowserService();
    private BrowserItem item117;
    private BrowserItem item101;
    private BrowserItem root;
    private List<BrowserItem> actualRoot;
    private List<BrowserItem> actual101;

    private void init() {
        item117 = service.findFolderById("117");
        item101 = service.findFolderById("101");
        root = service.findFolderById("100");

        actualRoot = root.getChildren();
        actual101 = item101.getChildren();
    }

    // Find folder name in list
    private boolean findName(List<BrowserItem> itemsList, String name) {
        for (BrowserItem item : itemsList) {
            if(item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    // move 117 to 101
    @Test
    public void Test1moveTest() {

        init();

        assertEquals(5, actualRoot.size());
        assertEquals(5, actual101.size());
        assertTrue(findName(actualRoot, "My_Folder-0-1"));

        service.moveFolder(item117, item101);

        init();

        assertEquals(4, actualRoot.size());
        assertEquals(6, actual101.size());
        assertFalse(findName(actualRoot, "My_Folder-0-1"));
    }

    // return 117 to root
    @Test
    public void Test2moveTest() {
        init();

        assertEquals(4, actualRoot.size());
        assertEquals(6, actual101.size());
        assertTrue(findName(actual101, "My_Folder-0-1"));

        service.moveFolder(item117, root);

        init();

        assertEquals(5, actualRoot.size());
        assertEquals(5, actual101.size());
        assertFalse(findName(actual101, "My_Folder-0-1"));
    }
}
