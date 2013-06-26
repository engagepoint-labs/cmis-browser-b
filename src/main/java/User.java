import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: iryna.domachuk
 * Date: 6/20/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */

public class User implements Serializable {


    private int id;
    private String firstname;
    private  String lastname;

     public User(){

     }

    public User(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        /*
        if (!firstname.equals(user.firstname)) return false;
        if (!lastname.equals(user.lastname)) return false;
        */

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        /*
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        */
        return result;
    }
}
