package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;
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

	@Ignore
	@Test
	public void test_advancedSearch_date_from()
	{
		AdvSearchParams params = new AdvSearchParams("101", "cmis:document", date1, null, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-1-1", item.getChildren().get(0).getName());
	}

	@Ignore
	@Test
	public void test_advancedSearch_date_range()
	{
		AdvSearchParams params = new AdvSearchParams("101",
				"cmis:document", date1, date2, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
	}


	@Ignore
	@Test
	public void test_advancedSearch_to_date()
	{
		AdvSearchParams params = new AdvSearchParams("100",
				"cmis:document", null, date2, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		//assertEquals(21, item.getChildren().size());
		assertEquals(1, item.getChildren().size());

	}


//	@Ignore
	@Test
	public void test_advancedSearch_document()
	{
		AdvSearchParams params = new AdvSearchParams("100",	"cmis:document", null, null, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(3, item.getChildren().size());
		assertEquals("My_Document-0-1", item.getChildren().get(0).getName());
	}


//	@Ignore
	@Test
	public void test_advancedSearch_folder()
	{
		AdvSearchParams params = new AdvSearchParams("100",	"cmis:folder", null, null, null, null, null);

		BrowserItem item = service.advancedSearch("101", params, 0, 0);

		assertEquals(2, item.getChildren().size());
		assertEquals("My_Folder-1-1", item.getChildren().get(0).getName());
	}


}
