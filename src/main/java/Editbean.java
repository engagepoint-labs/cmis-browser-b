import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

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

    private User user;

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


        User selected = listbean.getSelected();
        user = new User(selected.getId(), selected.getFirstname(), selected.getLastname());
        System.out.println("@PostConstruct");
        System.out.println("selected = "+selected);
        System.out.println("listbean selected   = "+listbean.getSelected());


    }


    public User getUser() {
        return user;
    }



    public String save(){


        if(user != null){

            listbean.getSelected().setFirstname(user.getFirstname());
            listbean.getSelected().setLastname(user.getLastname());
            return "list";

        }
        else {
            return null;
        }

    }

    public String cancel(){

        user = null;

        return "list";
    }



}
