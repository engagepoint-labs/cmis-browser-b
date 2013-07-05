package com.engagepoint.university.ep2013b.browser.api;


import java.util.List;

// Provides general interface for working with any data source like CMIS or File System

public interface BrowserService
{
    // should return unique name of service for search
    public String getServiceName();

    public int getTotalPagesFromFolderById(String id, int rowCounts);
    public int getTotalPagesFromFolderByPath(String path, int rowCounts);

    public BrowserItem findFolderById(String id, int pagenum, int rowCounts);
    public BrowserItem findFolderByPath(String path, int pagenum, int rowCounts);



    // ... add more here ...
}
