package simplejsf.web;

import simplejsf.core.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "mainbean")
@RequestScoped
public class MainBean implements Serializable
{
    private List<Person> persons = new ArrayList<Person>();


    public MainBean()
    {
    }

    @PostConstruct
    public void init()
    {
        Log.println("");
        Log.println("MainBean.init() ---------------------------------");

        persons.add(new Person(persons.size(), "Bilbo", "Bagins"));
        persons.add(new Person(persons.size(), "Thorin", "Oakenshield"));
        persons.add(new Person(persons.size(), "Gandalf", "White"));
    }

    @PreDestroy
    public void destroy()
    {
        Log.println("MainBean.destroy()");
    }


    public List<Person> getPersons()
    {
        return persons;
    }


    public String update(Person person)
    {
        Log.println("MainBean.update(" + person.hashCode() + ")");

        Person updated = persons.get(person.getId());
        updated.setLastname(person.getLastname());
        updated.setFirstname(person.getFirstname());

        return "list.xhtml";
    }
}
