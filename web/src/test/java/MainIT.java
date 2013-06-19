
import com.engagepoint.labs.b.core.BrowserItem;
import com.engagepoint.labs.b.core.CMISHelper;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MainIT {

    private CMISHelper cmisHelper;


    @Test
    public void listTest() throws Exception{
       List<BrowserItem> arrayList = new ArrayList<BrowserItem>();
        arrayList = cmisHelper.getRootFolder();

        assertNotNull(arrayList);
    }

    @Test
    public void cmisTest() throws Exception{
        String version = "0.9.0-EP-SNAPSHOT";
        assertEquals(cmisHelper.getVersion(),version);
    }

}
