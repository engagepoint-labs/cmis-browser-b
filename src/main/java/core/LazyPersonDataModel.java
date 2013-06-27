package core;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LazyPersonDataModel extends LazyDataModel<User> {

    UsersBean users;

    public LazyPersonDataModel(UsersBean users) {
        this.users = users;
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        return users.getUsers(first, pageSize);
    }

    @Override
    public Object getRowKey(User object) {
        return object.getId();
    }




    @Override
    public int getRowCount() {
        return users.getUsersCount();
    }
}
