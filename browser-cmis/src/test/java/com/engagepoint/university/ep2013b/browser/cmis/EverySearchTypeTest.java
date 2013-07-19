package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class EverySearchTypeTest {

    private CMISBrowserService cmisBrowserService = new CMISBrowserService();



    @Test
    public void test_advancedSearch_date_range()
    {

        Calendar dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 18, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "audioFile",100, null);

        AdvSearchParams params =  new AdvSearchParams("101",
                "cmis:document", dd.getTime(), dd2.getTime(), "ComplexType",null, null);

        BrowserItem item = cmisBrowserService.advancedSearch("101", params,1,30);

        //assertEquals(1, item.getChildren().size());
        assertEquals(3, item.getChildren().size());

    }


    @Test
    public void test_advancedSearch_to_date()
    {

        Calendar dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 18, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "plain/text",34036, null);

        AdvSearchParams params =  new AdvSearchParams("100",
                "cmis:document",null, dd2.getTime(), null, 34010, null);

        BrowserItem item = cmisBrowserService.advancedSearch("101", params,1,10);

        //assertEquals(21, item.getChildren().size());
        assertEquals(1, item.getChildren().size());

    }


    @Test
    public void test_advancedSearch_all_docs_in_folder()
    {

        Calendar dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 18, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "plain/text",34036, null);

        AdvSearchParams params =  new AdvSearchParams("100",
                "cmis:document",null, null, null, null, null);

        BrowserItem item = cmisBrowserService.advancedSearch("101", params,1,100);

        //assertEquals(21, item.getChildren().size());
        assertEquals(3, item.getChildren().size());

    }


    @Test
    public void test_advancedSearch_all_folders_in_folder()
    {

        Calendar dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 18, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "plain/text",34036, null);

        AdvSearchParams params =  new AdvSearchParams("100",
                "cmis:folder",null, null, null, null, null);

        BrowserItem item = cmisBrowserService.advancedSearch("101", params,1,100);

        //assertEquals(21, item.getChildren().size());
        assertEquals(2, item.getChildren().size());

    }


}
