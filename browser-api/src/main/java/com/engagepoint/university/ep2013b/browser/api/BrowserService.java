package com.engagepoint.university.ep2013b.browser.api;


// Provides general interface for working with any data source like CMIS or File System

public interface BrowserService
{
    // should return unique name of service for search
    public String getServiceName();

    public BrowserItem findFolderById(String id);
    public BrowserItem findFolderByPath(String path);

    public BrowserItem findFolderById(String id, int page, int rowCount);
    public BrowserItem findFolderByPath(String path, int page, int rowCount);

    public int getTotalPagesFromFolderById(String id, int rowCounts);
    public int getTotalPagesFromFolderByPath(String id, int rowCounts);

    // ... add more here ...
}
