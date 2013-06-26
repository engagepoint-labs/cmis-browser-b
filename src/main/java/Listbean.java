import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/20/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class Listbean implements Serializable {

  private ArrayList<User> list = new ArrayList<User>();
  private User selected = new User();

    /*
    @ManagedProperty(value="#{users}")
    private UserDB users;
   */


    public Listbean() {

    }

    @PostConstruct
    public void init(){

        int id = 0;

        User us = new User(++id, "Ivan", "Petrov");
        list.add(us);
        us = new User(++id, "Dmitro","Ivanov");
        list.add(us);
        us = new User(++id, "Danila","Severny");
        list.add(us);
        us = new User(++id, "Pavlo","Demidov");
        list.add(us);
        us = new User(++id, "Sergio","Suvorov");
        list.add(us);
        us = new User(++id, "Fedir","Olenev");
        list.add(us);
        us = new User(++id, "Stas","Kamarin");
        list.add(us);

    }



    public ArrayList<User> getList() {
        return list;
    }



    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }


    public String edit(User us){

        selected = new User();
        selected.setId(us.getId());
        selected.setFirstname(us.getFirstname());
        selected.setLastname(us.getLastname());

        return "edit";
    }

    public String save(){

        if(selected != null){

            //ArrayList<User> list = users.getDb();
            int index = list.indexOf(selected);
            if (index >= 0)
            {
                list.set(index,selected);
                //users.setDb(list);
                selected = new User();
                return "list";
            }

            return "edit";

        }
        else {
            return "edit";
        }
    }

    public String cancel(){

        System.out.println("cancel editing: "+selected);

        return "list";
    }


}
