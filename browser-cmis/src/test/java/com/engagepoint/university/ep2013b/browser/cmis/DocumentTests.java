package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class DocumentTests
{
    private CMISBrowserService service = new CMISBrowserService();

//	@Ignore
	@Test
    public void test_createDocument()
    {
		BrowserItem item = service.createDocument("101", "TestCreateDocument");
		BrowserItem parent = service.findFolderById("101");

		System.out.println("item   = " + item);
		System.out.println("parent = " + parent);

		// parent children's should be increased
        assertEquals(6, parent.getChildren().size());

		// name should be the same
		assertEquals("TestCreateDocument", item.getName());

		// type should be file
		assertEquals(BrowserItem.TYPE.FILE, item.getType());

		// id should be set
		assertFalse("".equals(item.getId()));
    }

//	@Ignore
	@Test
	public void test_editDocument()
	{
		BrowserItem item = service.createDocument("101", "TestEditDocument");
		BrowserItem parent = service.findFolderById("101");

		System.out.println("item   = " + item);
		System.out.println("parent = " + parent);

		item = service.editDocument(item.getId(), "TestNewEditDocument");

		parent = service.findFolderById("101");
		System.out.println("item   = " + item);
		System.out.println("parent = " + parent);

		assertEquals("TestNewEditDocument", item.getName());
	}

//	@Ignore
	@Test
	public void test_deleteDocument()
	{
		BrowserItem item, parent;

		item = service.createDocument("101", "TestDeleteDocument");
		parent = service.findFolderById("101");

		System.out.println("item   = " + item);
		System.out.println("parent = " + parent);

		service.deleteDocument(item.getId());

		parent = service.findFolderById("101");
		System.out.println("parent = " + parent);
	}

	@After
	public void cleanup()
	{
		BrowserItem item = service.simpleSearch("101", "Test", 0, 0);
		for (BrowserItem i : item.getChildren())
		{
			service.deleteDocument(i.getId());
		}
	}
}
