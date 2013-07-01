package simplejsf.web;

import simplejsf.core.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "mainbean")
@RequestScoped
public class MainBean implements Serializable
{
    protected List data;


    public MainBean()
    {
    }

    @PostConstruct
    public void init()
    {
        Log.println("\nMainBean.init()");

        data = Storage.select();
    }

    @PreDestroy
    public void destroy()
    {
        Log.println("MainBean.destroy()");
    }


    public List getData()
    {
        return data;
    }
}