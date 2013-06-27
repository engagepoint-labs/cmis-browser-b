package core;

import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "usersBean", eager = true)
@SessionScoped
public class UsersBean {

    private Map<Long, User> usersMap = new HashMap<Long, User>();

    public UsersBean() {

    }

    @PostConstruct
    public void init() {
        usersMap.put(1L, new User(1L, "Ivan", "Ivanov"));
        usersMap.put(2L, new User(2L, "Alex", "Petrov"));
        usersMap.put(3L, new User(3L, "Sergey", "Timkov"));
        usersMap.put(4L, new User(4L, "Oleg4", "Alkov"));
        usersMap.put(5L, new User(5L, "Oleg5", "Alkov"));
        usersMap.put(6L, new User(6L, "Oleg6", "Alkov"));
        usersMap.put(7L, new User(7L, "Oleg7", "Alkov"));
        usersMap.put(8L, new User(8L, "Oleg8", "Alkov"));
        usersMap.put(9L, new User(9L, "Oleg9", "Alkov"));
        usersMap.put(10L, new User(10L, "Oleg10", "Alkov"));
        usersMap.put(11L, new User(11L, "Oleg11", "Alkov"));
        usersMap.put(12L, new User(12L, "Oleg12", "Alkov"));
    }

    public List<User> getUsers(int start, int count) {
        int toIndex = start+count;
        if (toIndex>usersMap.size()){
            toIndex = usersMap.size();
        }
        return new ArrayList<User>(usersMap.values()).subList(start,toIndex);
    }

    public User getUserById(long id) {
        return usersMap.get(id);
    }

    public int  getUsersCount(){
        return usersMap.size();
    }


}
