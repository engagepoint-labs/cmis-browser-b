package com.engagepoint.labs.b.preferences;

import java.util.prefs.Preferences;


public class PreferencesHelper
{
    private Preferences settings;

    // Constant preferences names
    private static final String FAVORITE_FOLDER = "FAVORITE_FOLDER";
    private static final String CMIS_URL = "CMIS_URL";


    public PreferencesHelper()
    {
        settings = Preferences.userNodeForPackage(this.getClass());
    }


    //--- Favorite Folder ---------------------------------------------------------------
    public void setFavoriteFolder(String url)
    {
        // check if url is correct
        String pattern = "^((/(\\w|_|-)*)+)$";
//        String pattern = "^((?:/\\w+(?:_\\w+)*(?:-\\w+)*)+)$";
        if (!url.matches(pattern)) throw new IllegalArgumentException();

        settings.put(FAVORITE_FOLDER, url);
    }

    public String getFavoriteFolder()
    {
        return settings.get(FAVORITE_FOLDER, "/");  // return root folder by default
    }


    //--- CMIS Url ----------------------------------------------------------------------
    public void setCmisUrl(String url)
    {
        // check if url is correct
        String pattern = "^(https?)://((\\w|\\.\\w)*)(:(\\d{2,5}))?((?:/\\w+(?:_\\w+)*(?:-\\w+)*)+)/$";
        if (!url.matches(pattern)) throw new IllegalArgumentException();

        settings.put(CMIS_URL, url);
    }

    public String getCmisUrl(String defaultUrl)
    {
        return settings.get(CMIS_URL, defaultUrl);
    }
}
