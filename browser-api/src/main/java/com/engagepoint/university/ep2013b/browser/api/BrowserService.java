package com.engagepoint.university.ep2013b.browser.api;


// Provides general interface for working with any data source like CMIS or File System

public interface BrowserService
{
    // should return unique name of service for search
    public String getServiceName();

    public BrowserItem findFolderById(String id);//, boolean includeOnlyFolders);
    public BrowserItem findFolderByPath(String path);//, boolean includeOnlyFolders);

    // ... add more here ...
}
