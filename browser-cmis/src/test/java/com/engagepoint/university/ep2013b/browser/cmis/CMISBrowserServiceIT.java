package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CMISBrowserServiceIT {
    private CMISBrowserService cmisBrowserService = new CMISBrowserService();

    @Test
    public void test_getTotalPagesFromFolderById()
    {
        BrowserItem item = cmisBrowserService.findFolderById("101", 1, 2);

        assertNotNull(item);
    }


}
