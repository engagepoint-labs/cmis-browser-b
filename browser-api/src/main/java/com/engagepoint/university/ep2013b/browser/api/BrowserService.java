package com.engagepoint.university.ep2013b.browser.api;


// Provides general interface for working with any data source like CMIS or File System

import java.util.Map;

public interface BrowserService
{
    // should return unique name of service for search
    public String getServiceName();

    public BrowserItem findFolderById(String id);
    public BrowserItem findFolderByPath(String path);

    public BrowserItem findFolderById(String id, int page, int rowCount);
    public BrowserItem findFolderByPath(String path, int page, int rowCount);

    // Possibly better create some Util class for this method ?
    public String getCurrentLocationById(String id);
    public BrowserItem simpleSearch(String id, String parameter, int page, int rowCounts);
    //public int getTotalPagesFromSimpleSearch(String id, String parameter, int rowCounts);
    public BrowserItem advancedSearch(String id, Object parameter, int pageNum, int rowCounts);
    public BrowserItem createFolder(String id, String name, String type);
    public BrowserItem editFolder(String id, String name, String type);
    public void deleteFolder(String id);
    public Map<String,String> getTypeList(String type);

    void moveFolder(BrowserItem source, BrowserItem target);

    // ... add more here ...
}
