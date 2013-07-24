package com.engagepoint.labs.b.preferences;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.prefs.Preferences;


public class PreferencesHelperTest
{
    PreferencesHelper helper = new PreferencesHelper();
    Preferences settings;

    // Create preferences node
    @Before
    public void setUp() throws Exception
    {
        settings = Preferences.userNodeForPackage(helper.getClass());
    }

    // Delete preferences node
    @After
    public void tearDown() throws Exception
    {
        Preferences.userNodeForPackage(helper.getClass()).removeNode();
    }

    //--- Favorite Folder ---------------------------------------------------------------
    // Tests if sets given url argument
    @Test
    public void test_setFavoriteFolder_url()
    {
        String expected = "/My/Favorite/Folder";
        helper.setFavoriteFolder("/My/Favorite/Folder");
        String actual = settings.get("FAVORITE_FOLDER", "");

        assertEquals(expected, actual);
    }

    // Tests if not sets invalid argument
    @Test(expected = IllegalArgumentException.class)
    public void test_setFavoriteFolder_url_correct_negativetest()
    {
        helper.setFavoriteFolder("/My/Favorite/Folder/file.txt");
        helper.setFavoriteFolder("/My/Favorite/Folder/");
        helper.setFavoriteFolder("/My/Favorite//");
        helper.setFavoriteFolder("MyFavorite/");
        helper.setFavoriteFolder("MyFavorite");
        helper.setFavoriteFolder("");
    }

    // Tests if sets valid argument
    @Test
    public void test_setFavoriteFolder_url_correct_positivetest()
    {
        helper.setFavoriteFolder("/My/Favorite/Folder/");
        helper.setFavoriteFolder("/MyFolder/");
        helper.setFavoriteFolder("/MyFolder5/");
        helper.setFavoriteFolder("/MyFolder/6");
        helper.setFavoriteFolder("/My-Folder/");
        helper.setFavoriteFolder("/My_Folder/");
        helper.setFavoriteFolder("/");
    }

    // Tests if gets saved argument
    @Test
    public void test_getFavoriteFolder_url()
    {
        String expected = "/My/Favorite/Folder";
        settings.put("FAVORITE_FOLDER", "/My/Favorite/Folder");
        String actual = helper.getFavoriteFolder();

        assertEquals(expected, actual);
    }

    // Tests if gets default url when no argument
    @Test
    public void test_getFavoriteFolder_url_default()
    {
        String expected = "/";
        settings.remove("FAVORITE_FOLDER");
        String actual = helper.getFavoriteFolder();

        assertEquals(expected, actual);
    }


    //--- CMIS Url ----------------------------------------------------------------------
    @Test
    public void test_setCmisUrl_url()
    {
        String expected = "http://www.host.com:1234/server/";
        helper.setCmisUrl("http://www.host.com:1234/server/");
        String actual = settings.get("CMIS_URL", "");

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setCmisUrl_url_correct_negativetest()
    {
        helper.setCmisUrl("http://www.host.com:8080/server/file.txt");
        helper.setCmisUrl("http://www.host.com:8080/server");
        helper.setCmisUrl("http://www.host.com:8080");
        helper.setCmisUrl("http://www.host.com");
        helper.setCmisUrl("http://www.host.");
        helper.setCmisUrl("http://");
        helper.setCmisUrl("http");
        helper.setCmisUrl("www.host.com:8080/server");
        helper.setCmisUrl(".host.com:8080/server");
        helper.setCmisUrl(".:8080/server");
        helper.setCmisUrl("8080");
        helper.setCmisUrl("http://www.host.com:port/server/");
        helper.setCmisUrl("http://www.host.com:00000/server/");
        helper.setCmisUrl("http://www.host.com:0/server/");
    }

    @Test
    public void test_setCmisUrl_url_correct_positivetest()
    {
        helper.setCmisUrl("http://www.host.com:8080/server/");
        helper.setCmisUrl("https://www.host.com:8080/server/");
        helper.setCmisUrl("http://localhost:8080/server/");
    }

    @Test
    public void test_getCmisUrl_url()
    {
        String expected = "http://localhost:8080/server/";
        settings.put("CMIS_URL", "http://localhost:8080/server/");
        String actual = helper.getCmisUrl("http://localhost:8080/server/services/");

        assertEquals(expected, actual);
    }

    @Test
    public void test_getCmisUrl_url_default()
    {
        String expected = "http://localhost:8080/server/services/";
        settings.remove("CMIS_URL");
        String actual = helper.getCmisUrl("http://localhost:8080/server/services/");

        assertEquals(expected, actual);
    }
}
