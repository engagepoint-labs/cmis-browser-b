package com.engagepoint.university.ep2013b.browser.api;


// Provides general interface for working with any data source like CMIS or File System

import java.util.List;

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

    // Possibly better create some Util class for this method ?
    public String getCurrentLocationById(String id);
    public List<BrowserItem> simpleSearch(String id, String parameter, int page, int rowCounts);
    public int getTotalPagesFromSimpleSearch(String id, String parameter, int rowCounts);


    // ... add more here ...
}
