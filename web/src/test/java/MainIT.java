import com.engagepoint.labs.b.core.BrowserItem;
import com.engagepoint.labs.b.core.CMISHelper;


import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class MainIT {

    private List<BrowserItem> list = new ArrayList<BrowserItem>();


    @Test
    public List<BrowserItem> getFolderTest() throws Exception{
      list = new CMISHelper().getRootFolder();
      return list;
    }
}
