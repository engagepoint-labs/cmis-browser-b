package showcase;

import com.engagepoint.university.ep2013b.browser.component.BrowserController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class ShowcaseBrowser extends BrowserController
{
    public ShowcaseBrowser()
	{
		System.out.println("ShowcaseBrowser()");
	}

    @PostConstruct
    public void init()
	{
        super.init();
    }
}
