package com.engagepoint.university.ep2013b.browser.cmis;


import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;


public class EverySearchTypeIT {

    private CMISBrowserService cmisBrowserService = new CMISBrowserService();


//
//    @Test
//    public void test_simpleSearch_findlist_size()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "-1",1,19);
//
//        assertEquals(3, item.getChildren().size());
//    }
//
//    @Test
//    public void test_simpleSearch_findlist_totalpages()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "-1",1,19);
//
//        assertEquals(1, item.getTotalPages());
//    }
//
//    @Test
//    public void test_simpleSearch_invalid_parameter_id()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("0", "-1",1,19);
//
//        assertEquals(0, item.getChildren().size());
//    }
//
//    @Test
//    public void test_simpleSearch_invalid_parameter_str()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",1,19);
//
//        assertEquals(3, item.getChildren().size());
//    }
//
//    @Test
//    public void test_simpleSearch_valid_data()
//    {
//        BrowserItem item  = cmisBrowserService.simpleSearch("101", "_",1,19);
//
//        assertEquals("My_Document-1-0", item.getChildren().get(2).getName());
//    }
//
//    @Test
//    public void test_simpleSearch_empty_page()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("0", "-1",2,19);
//
//        assertEquals(0, item.getChildren().size());
//    }
//
//
//    @Test
//    public void test_simpleSearch_valid_page()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",2,2);
//
//        assertEquals(1, item.getChildren().size());
//    }
//
//
//    @Test
//    public void test_simpleSearch_valid_last_page()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",3,1);
//
//        assertEquals("My_Document-1-0", item.getChildren().get(0).getName());
//    }
//
//
//    @Test
//    public void test_simpleSearch_invalid_totalpages()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "_",9,1);
//
//        assertNotSame(4, item.getTotalPages());
//    }
//
//
//    @Test
//    public void test_simpleSearch_invalid_totalpages2()
//    {
//        BrowserItem item = cmisBrowserService.simpleSearch("101", "older",1,4);
//
//        assertEquals(0, item.getTotalPages());
//    }
//
//
//    @Before
//    public void fill_tast_data(){
//
//    }
//

    @Test
    public void test_advancedSearch_date_range()
    {

        Calendar dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
        Calendar dd2 = new GregorianCalendar(2013, 6, 18, 3, 0, 0);

//        AdvSearchParams params =  new AdvSearchParams(
//                "cmis:document",dd, dd2, "audioFile",100, null);

        AdvSearchParams params =  new AdvSearchParams("101",
                "cmis:document", dd, dd2, "ComplexType",null, null);

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
                "cmis:document",null, dd2, null, 34010, null);

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
