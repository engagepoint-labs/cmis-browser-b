package simplejsf.web;

import simplejsf.core.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "mainbean")
@RequestScoped
public class MainBean implements Serializable
{
    private Person editable = new Person();

    public MainBean()
    {
    }

    @PostConstruct
    public void init()
    {
        Log.println("");
        Log.println("MainBean.init() ---------------------------------");
    }

    @PreDestroy
    public void destroy()
    {
        Log.println("MainBean.destroy()");
    }

    public Person getEditable()
    {
        Log.println("MainBean.getEditable()");

        return editable;
    }

    public List<Person> getData()
    {
        Log.println("MainBean.getData()");

        return Storage.select();
    }

    public String edit(Person selected)
    {
        Log.println("MainBean.edit(" + selected.getId() + ")");
        this.editable = selected;

        return "edit.xhtml";
    }

    public String update()
    {
        Log.println("MainBean.update(" + editable.getId() + ")");

        Storage.update(editable);

        return "list.xhtml";
    }
}
