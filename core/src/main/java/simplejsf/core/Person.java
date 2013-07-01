package simplejsf.core;


public class Person
{
    private final int id;
    private String lastname;
    private String firstname;


    public Person(int id, String firstname, String lastname)
    {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;

        Log.println("Constract\n"+this);
    }


    public int getId()
    {
        return id;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    @Override
    public String toString()
    {
        StringBuffer out = new StringBuffer();

        out.append("Person = " + this.hashCode());
        out.append("\n\tid        = " + id);
        out.append("\n\tlastname  = " + lastname);
        out.append("\n\tfirstname = " + firstname);

        return out.toString();
    }
}