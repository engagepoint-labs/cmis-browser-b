package com.engagepoint.university.ep2013b.browser.spi;

import com.engagepoint.university.ep2013b.browser.api.CMISHelper;

// Search necessary data service provider
public class DataFactory
{
    // add more data sources below ...
    public static enum SOURCES {CMIS}


    // --- Creating new data service provider for specific source
    public static DataService getInstance(SOURCES source)
    {
        DataService spi = null;

        switch (source)
        {
            case CMIS: spi = new CMISHelper(); break;
            // ... add other DataService implementations here ...
        }

        return spi;
    }
}
