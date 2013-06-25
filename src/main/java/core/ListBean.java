package core;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 6/25/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name="listBean", eager=true)
@SessionScoped
public class ListBean {
    private Map<Long, User> usersMap = new HashMap<Long, User>();

    public ListBean() {
    }

    @PostConstruct
    public void init() {
        usersMap.put(1L, new User(1L, "Ivan", "Ivanov"));
        usersMap.put(2L, new User(2L, "Alex", "Petrov"));
        usersMap.put(3L, new User(3L, "Sergey", "Timkov"));
        usersMap.put(4L, new User(4L, "Oleg", "Alkov"));
    }

    public List<User> getUsers() {
        return new ArrayList<User>( usersMap.values());
    }

    public User getUserById(long id) {
        return usersMap.get(id);
    }
}
