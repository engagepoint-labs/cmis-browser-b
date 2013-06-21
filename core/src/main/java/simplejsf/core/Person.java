package simplejsf.core;


public class Person
{
//    private final int id;
    private int id;
    private String lastname;
    private String firstname;


    public Person(int id, String firstname, String lastname)
    {
        Log.println("Person(" + id + ", \"" + firstname + "\", \"" + lastname + "\") = " + this.hashCode());

        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        Log.println("Person.getId() = " + id);

        return id;
    }

    public String getLastname()
    {
        Log.println("Person.getLastname() = \"" + lastname + "\"");

        return lastname;
    }

    public void setLastname(String lastname)
    {
        Log.println("Person.setLastname(\"" + lastname + "\")");

        this.lastname = lastname;
    }

    public String getFirstname()
    {
        Log.println("Person.getFirstname() = \"" + firstname + "\"");

        return firstname;
    }

    public void setFirstname(String firstname)
    {
        Log.println("Person.setFirstname() = \"" + firstname + "\"");

        this.firstname = firstname;
    }
}
