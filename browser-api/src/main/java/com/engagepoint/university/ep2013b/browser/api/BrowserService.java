package com.engagepoint.university.ep2013b.browser.api;


import java.util.List;

// Provides general interface for working with any data source like CMIS or File System

public interface BrowserService
{
    // should return unique name of service for search
    public String getServiceName();

    public List<BrowserItem> getRootFolder();
//    public List<BrowserItem> findFolderById(String id, boolean includeOnlyFolders);
//    public List<BrowserItem> findFolderByPath(String path, boolean includeOnlyFolders);

    // ... add more here ...
}
