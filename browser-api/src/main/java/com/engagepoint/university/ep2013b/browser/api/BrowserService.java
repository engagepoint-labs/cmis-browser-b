package com.engagepoint.university.ep2013b.browser.api;


// Provides general interface for working with any data source like CMIS or File System

import java.util.Map;

public interface BrowserService
{
    // should return unique name of service for search
    public String getServiceName();

    public BrowserFolder findFolderById(String id);
    public BrowserFolder findFolderByPath(String path);

    public BrowserFolder findFolderById(String id, int page, int rowCount);
    public BrowserFolder findFolderByPath(String path, int page, int rowCount);

    // Possibly better create some Util class for this method ?
    public String getCurrentLocationById(String id);
    public BrowserFolder simpleSearch(String id, String parameter, int page, int rowCounts);
    //public int getTotalPagesFromSimpleSearch(String id, String parameter, int rowCounts);
    public BrowserFolder advancedSearch(String id, Object parameter, int pageNum, int rowCounts);

    public BrowserFolder createFolder(String id, String name, String type);
    public BrowserFolder editFolder(String id, String name, String type);
    public void deleteFolder(String id);
    public Map<String,String> getTypeList(String type);

   // public BrowserItem createDocument(String id, String name, String type);

    // ... add more here ...
}
