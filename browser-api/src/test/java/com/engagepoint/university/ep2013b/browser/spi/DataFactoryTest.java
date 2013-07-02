package com.engagepoint.university.ep2013b.browser.spi;

import com.engagepoint.university.ep2013b.browser.api.CMISHelper;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class DataFactoryTest
{
    // Test if returns specified class
    @Test
    public void test_getInstance_return_correct() throws Exception
    {
        DataService actual = DataFactory.getInstance(DataFactory.SOURCES.CMIS);

        assertTrue(actual.getClass().isAssignableFrom(CMISHelper.class));
    }
}
