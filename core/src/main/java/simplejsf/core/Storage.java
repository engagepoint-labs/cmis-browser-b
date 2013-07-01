package simplejsf.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage
{
    private static List<Person> persons = new ArrayList<Person>(Arrays.asList(
            new Person(1, "Alice", "Adams"),
            new Person(2, "Bob", "Brown"),
            new Person(3, "Eva", "Evans")
    ));


    public static List<Person> select()
    {
        return persons;
    }

    public static Person select(int id)
    {
        return persons.get(id-1);
    }

    public static void update(Person person)
    {
        Person updated = select(person.getId());

        updated.setLastname(person.getLastname());
        updated.setFirstname(person.getFirstname());
    }
}