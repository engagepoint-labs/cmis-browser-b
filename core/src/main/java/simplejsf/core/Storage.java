package simplejsf.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Storage
{
    private static List<Person> persons = new ArrayList<Person>(Arrays.asList(
            new Person(1, "Bilbo", "Bagins"),
            new Person(2, "Thorin", "Oakenshield"),
            new Person(3, "Gandalf", "White")
    ));


    public static List<Person> select()
    {
        Log.println("Storage.select()");

        return persons;
    }

    public static Person select(int id)
    {
        Log.println("Storage.select(" + id + ")");

        return persons.get(id-1);
    }

    public static void update(Person person)
    {
        Log.println("Storage.update(" + person.getId() + ")");

        Person updated = select(person.getId());
        updated.setLastname(person.getLastname());
        updated.setFirstname(person.getFirstname());
    }
}