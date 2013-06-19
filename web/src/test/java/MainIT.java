import com.engagepoint.labs.b.core.BrowserItem;
import com.engagepoint.labs.b.core.CMISHelper;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class MainIT {

    private List<BrowserItem> list = new ArrayList<BrowserItem>();


    @Test
    public void getFolderTest(){

      list = new CMISHelper().getRootFolder();

    }
}
