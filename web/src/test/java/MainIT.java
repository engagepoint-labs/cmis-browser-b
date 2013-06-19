
import com.engagepoint.labs.b.core.BrowserItem;
import com.engagepoint.labs.b.core.CMISHelper;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MainIT {

    private CMISHelper cmisHelper = new CMISHelper();


    @Test
    public void listTest() throws Exception{
       List<BrowserItem> arrayList = new ArrayList<BrowserItem>();
        for (int i = 0; i <arrayList.size() ; i++) {
            arrayList.add(cmisHelper.getRootFolder().get(i));
        }
        assertNotNull(arrayList);
    }

    @Test
    public void cmisTest() throws Exception{
        cmisHelper.connect();
        String version = "CMIS_1_0";
        assertEquals(cmisHelper.getVersion(),version);
    }

}
