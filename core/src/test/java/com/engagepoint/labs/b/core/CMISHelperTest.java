package com.engagepoint.labs.b.core;

import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/18/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class CMISHelperTest {
    SessionFactory sessionFactory;
    Map<String, String> parameter;

    @Before
    public void setUp() throws Exception {
        sessionFactory = SessionFactoryImpl.newInstance();
        parameter = new HashMap<String, String>();
    }

    @After
    public void tearDown() throws Exception {

    }

    private List<BrowserItem> makeList() {
        List<BrowserItem> list = new ArrayList<BrowserItem>();
        list.add(new BrowserItem("My_Document-0-0", "Tue Jun 18 17:09:43 EEST 2013"));
        list.add(new BrowserItem("My_Document-0-1", "Tue Jun 18 17:09:43 EEST 2013"));
        list.add(new BrowserItem("My_Document-0-2", "Tue Jun 18 17:09:43 EEST 2013"));
        list.add(new BrowserItem("My_Folder-0-0", "Tue Jun 18 17:09:41 EEST 2013"));
        list.add(new BrowserItem("My_Folder-0-1", "Tue Jun 18 17:09:42 EEST 2013"));
        return list;
    }

    @Test
    public void testConnect() throws Exception {

    }

    @Test
    public void testGetRootFolder() throws Exception {
        assertNotNull(makeList());
//        assertNotSame(makeList(), new CMISHelper().getRootFolder());
    }
}
