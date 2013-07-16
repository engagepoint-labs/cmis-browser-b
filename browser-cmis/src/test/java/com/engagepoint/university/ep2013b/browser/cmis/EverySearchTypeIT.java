package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class EverySearchTypeIT {

    private CMISBrowserService cmisBrowserService = new CMISBrowserService();



    @Test
    public void test_simpleSearch_findlist_size()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "-1",1,19);

        assertEquals(3, item.getChildren().size());
    }

    @Test
    public void test_simpleSearch_findlist_totalpages()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "-1",1,19);

        assertEquals(1, item.getTotalPages());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_id()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("0", "-1",1,19);

        assertEquals(0, item.getChildren().size());
    }

    @Test
    public void test_simpleSearch_invalid_parameter_str()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",1,19);

        assertEquals(3, item.getChildren().size());
    }

    @Test
    public void test_simpleSearch_valid_data()
    {
        BrowserItem item  = cmisBrowserService.simpleSearch("101", "_",1,19);

        assertEquals("My_Document-1-0", item.getChildren().get(2).getName());
    }

    @Test
    public void test_simpleSearch_empty_page()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("0", "-1",2,19);

        assertEquals(0, item.getChildren().size());
    }


    @Test
    public void test_simpleSearch_valid_page()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",2,2);

        assertEquals(1, item.getChildren().size());
    }


    @Test
    public void test_simpleSearch_valid_last_page()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",3,1);

        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
    }


    @Test
    public void test_simpleSearch_invalid_totalpages()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",9,1);

        assertNotSame(4, item.getTotalPages());
    }


    @Test
    public void test_simpleSearch_invalid_totalpages2()
    {
        BrowserItem item = cmisBrowserService.simpleSearch("101", "older",1,4);

        assertEquals(0, item.getTotalPages());
    }


    @Before
    public void fill_tast_data(){

    }


    @Test
    public void test_advancedSearch_positive()
    {


        //new GregorianCalendar(2013, 6, 15, 3, 0, 0)
        Calendar dd = new GregorianCalendar(2013, 6, 16, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 17, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "audioFile",100, null);

        AdvSearchParams params =  new AdvSearchParams(
                "cmis:document", dd.getTime(), dd2.getTime(), "ComplexType",null, null);

        BrowserItem item = cmisBrowserService.advancedSearch("101", params,1,30);

        //assertEquals(1, item.getChildren().size());
        assertEquals(21, item.getChildren().size());

    }


    @Test
    public void test_advancedSearch_positive2()
    {


        //new GregorianCalendar(2013, 6, 15, 3, 0, 0)
        Calendar dd = new GregorianCalendar(2013, 6, 16, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 17, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "plain/text",100, null);

        AdvSearchParams params =  new AdvSearchParams(
                "cmis:document",null, dd2.getTime(), null, 34036, null);

        BrowserItem item = cmisBrowserService.advancedSearch("101", params,1,30);

        //assertEquals(21, item.getChildren().size());
        assertEquals(1, item.getChildren().size());

    }


}
