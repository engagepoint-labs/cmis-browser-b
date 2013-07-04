package com.engagepoint.university.ep2013b.browser.component;

import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import com.engagepoint.university.ep2013b.browser.cmis.CMISBrowserService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class BrowserFactoryTest
{
    // Test if returns specified class
    @Test
    public void test_getInstance_source_correct_positivetest()
    {
        BrowserService actual = BrowserFactory.getInstance("CMIS");

        assertTrue(actual.getClass().isAssignableFrom(CMISBrowserService.class));
    }


    @Test(expected = NullPointerException.class)
    public void test_getInstance_source_correct_negativetest()
    {
        BrowserFactory.getInstance("wrong_service_name");
    }
}
