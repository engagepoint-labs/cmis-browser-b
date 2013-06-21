
import com.engagepoint.labs.b.core.CMISHelper;
import static org.junit.Assert.*;

import org.junit.Test;



public class MainIT {

    private CMISHelper cmisHelper = new CMISHelper();


    @Test(expected = NullPointerException.class)
    public void test_getRootFoolder_is_empty(){

        cmisHelper.getRootFolder().get(0);

    }

    @Test
    public void test_getVersion() throws Exception{
        cmisHelper.connect();
        String version = "CMIS_1_0";
        assertEquals(version,cmisHelper.getVersion());
    }

}
