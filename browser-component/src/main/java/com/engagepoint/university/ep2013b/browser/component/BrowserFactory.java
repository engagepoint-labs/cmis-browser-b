package com.engagepoint.university.ep2013b.browser.component;


import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import java.util.ServiceLoader;


// Search necessary data service provider

public class BrowserFactory
{
    // Creating new data service provider for specific name
    public static BrowserService getInstance(String servicename) throws NullPointerException
    {
        ServiceLoader<BrowserService> services = ServiceLoader.load(BrowserService.class);

        for (BrowserService i : services)
        {
            if (i.getServiceName().equalsIgnoreCase(servicename)) return i;
        }

        throw new NullPointerException("Service with name \"" + servicename + "\" not found.");
    }
}
