import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/25/13
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@ViewScoped
public class Editbean  implements Serializable {

    private User selected = new User();
    //private User edited = new User();


    @ManagedProperty(value="#{listbean}")
    private Listbean listbean;


    public Listbean getListbean() {
        return listbean;
    }

    public void setListbean(Listbean listbean) {
        this.listbean = listbean;
    }




    public Editbean() {
    }

    @PostConstruct
    public void init(){


        selected = listbean.getSelected();

        System.out.println("@PostConstruct");
        System.out.println("selected = "+selected);
        System.out.println("listbean selected   = "+listbean.getSelected());
        selected = listbean.getSelected();

    }


    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public Editbean(User selected) {
        this.selected = selected;
    }

    public String edit(User us){

        System.out.println("editbean.edit(User us)");
        selected = us;

        System.out.println("selected = "+selected);

        return "edit";
    }

    public String save(){

        System.out.println("save(): selected = "+selected);

        if(selected != null){

            ArrayList<User> list = listbean.getList();
            int index = list.indexOf(selected);
            if (index >= 0)
            {
                list.set(index,selected);
                listbean.setList(list);
                return "list";
            }

            return "edit";

        }
        else {
            return "edit";
        }

    }

    public String cancel(){

        selected = new User();

        return "list";
    }



}
