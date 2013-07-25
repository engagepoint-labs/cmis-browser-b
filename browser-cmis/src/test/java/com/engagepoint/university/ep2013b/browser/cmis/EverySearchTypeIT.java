package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotSame;


public class EverySearchTypeIT
{
	private CMISBrowserService service = new CMISBrowserService();
	private Date date1 = new Date();
	private Date date2 = new Date();

	@Before
	public void setup()
	{
		// set date2 to next day
		date2.setDate(date2.getDate() + 1);

		System.out.println("date1 = " + date1.toString());
		System.out.println("date2 = " + date2.toString());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_document()
	{
		SearchParams params = new SearchParams("document", null, null, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}


//	@Ignore
	@Test
	public void test_advancedSearch_folder()
	{
		SearchParams params = new SearchParams("folder", null, null, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(2, item.getChildren().size());
		assertEquals("My_Folder-1-1", item.getChildren().get(0).getName());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_date_from()
	{
		SearchParams params = new SearchParams("document", date1, null, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}


//	@Ignore
	@Test
	public void test_advancedSearch_date_to()
	{
		SearchParams params = new SearchParams("document", null, date2, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_date_range()
	{
		SearchParams params = new SearchParams("document", date1, date2, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_date_same()
	{
		SearchParams params = new SearchParams("document", date1, date1, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_content()
	{
		SearchParams params = new SearchParams("document", null, null, "text/plain", null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_size()
	{
		SearchParams params = new SearchParams("document", null, null, null, 33299, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(1, item.getChildren().size());
		assertEquals("My_Document-1-2", item.getChildren().get(0).getName());
	}

//	@Ignore
	@Test
	public void test_advancedSearch_text()
	{
		SearchParams params = new SearchParams("document", null, null, null, null, "Lorem");

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}
}
