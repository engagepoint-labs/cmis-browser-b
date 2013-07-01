package simplejsf.web;

import simplejsf.core.*;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(name = "editbean")
@ViewScoped
public class EditBean implements Serializable
{
    private Person editable;


    public EditBean()
    {
    }

    public void init(ComponentSystemEvent event)
    {
        Log.println("\nEditBean.init()");

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        String value = params.get("id");

        if (value != null)
        {
            int id = Integer.parseInt(value);
            editable = Storage.select(id);

            Log.println("EditBean.editable = " + editable.hashCode());
        }
    }

    @PreDestroy
    public void destroy()
    {
        Log.println("EditBean.destroy()");
    }


    public Person getEditable()
    {
        Log.println("EditBean.getEditable() = " + editable.hashCode());

        return editable;
    }

    public String save()
    {
        Log.println("EditBean.save()");

        Storage.update(editable);

        return "list.xhtml";
    }
}