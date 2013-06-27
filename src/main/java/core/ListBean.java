package core;

import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;


@ManagedBean(name = "listBean", eager = true)
@SessionScoped
public class ListBean {
    private LazyPersonDataModel lazyModel;
    private User user;

    private Map<Long, User> usersMap = new HashMap<Long, User>();

    public ListBean() {
        lazyModel = new LazyPersonDataModel(lazyModel.load(1,3));
    }

    @PostConstruct
    public void init() {
        usersMap.put(1L, new User(1L, "Ivan", "Ivanov"));
        usersMap.put(2L, new User(2L, "Alex", "Petrov"));
        usersMap.put(3L, new User(3L, "Sergey", "Timkov"));
        usersMap.put(4L, new User(4L, "Oleg", "Alkov"));
    }

    public List<User> getUsers() {
        return new ArrayList<User>(usersMap.values());
    }



    public User getUserById(long id) {
        return usersMap.get(id);
    }

    public LazyDataModel<User> getLazyModel() {
        return lazyModel;
    }

    public User getUser() {
        return user;
    }


}
