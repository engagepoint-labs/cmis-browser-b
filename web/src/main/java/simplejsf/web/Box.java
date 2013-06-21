package simplejsf.web;

import simplejsf.core.Log;
import simplejsf.core.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "boxbean")
@RequestScoped
public class Box implements Serializable
{
    private Person editable;

    public Box()
    {
    }

    @PostConstruct
    public void init()
    {
        Log.println("");
        Log.println("Box.init() ---------------------------");

        editable = new Person(-1, "none", "none");
    }

    @PreDestroy
    public void destroy()
    {
        Log.println("Box.destroy()");
    }

    public Person getEditable()
    {
        Log.println("Box.getEditable() = " + editable.hashCode());

        return editable;
    }

    public String edit(Person editable)
    {
        Log.println("Box.edit("+ editable.hashCode() + ")");

        this.editable = editable;

        return "edit.xhtml";
    }
}
