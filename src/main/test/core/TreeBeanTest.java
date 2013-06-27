package core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/27/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeBeanTest {
    TreeBean tb;

    @Before
    public void init() {
        tb = new TreeBean();
    }

    @Test
    public void listNodeSizeTest() {
        Assert.assertNotNull(tb.getListNodes());
    }
}
