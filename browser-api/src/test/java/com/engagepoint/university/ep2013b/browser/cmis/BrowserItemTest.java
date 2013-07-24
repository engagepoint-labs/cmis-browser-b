package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BrowserItemTest
{
	BrowserItem root, current;

	@Before
	public void setup()
	{
		BrowserItem item, folder;

		root = new BrowserItem("1", "RootFolder", BrowserItem.TYPE.FOLDER);

		folder = new BrowserItem("2", "Folder-0-0", BrowserItem.TYPE.FOLDER);
		folder.setParent(root);
		root.setChild(folder);

			item = new BrowserItem("5", "Folder-1-0", BrowserItem.TYPE.FOLDER);
			item.setParent(folder);
			folder.setChild(item);

				current = new BrowserItem("8", "Folder-2-0", BrowserItem.TYPE.FOLDER);
				current.setParent(item);
				item.setChild(current);

			item = new BrowserItem("6", "Folder-1-1", BrowserItem.TYPE.FOLDER);
			item.setParent(folder);
			folder.setChild(item);

			item = new BrowserItem("7", "Document-1-0", BrowserItem.TYPE.FOLDER);
			item.setParent(folder);
			folder.setChild(item);

		folder = new BrowserItem("3", "Folder-0-1", BrowserItem.TYPE.FOLDER);
		folder.setParent(root);
		root.setChild(folder);

		item = new BrowserItem("4", "Document-0-0", BrowserItem.TYPE.FILE);
		item.setParent(root);
		root.setChild(item);
	}


    @Test
    public void test_getLocation()
    {
        String expected = "/Folder-0-0/Folder-1-0/Folder-2-0";
        String actual = current.getLocation();

        assertEquals(expected, actual);
    }
}
