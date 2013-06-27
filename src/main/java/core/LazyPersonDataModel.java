package core;

import org.primefaces.model.LazyDataModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class LazyPersonDataModel extends LazyDataModel<User> {

    private List<User> datasource;

    public LazyPersonDataModel(List<User> datasource) {
        this.datasource = datasource;
    }


    public List<User> load(int first, int pageSize) {
        List<User> data = new ArrayList<User>();
        if(data.size() > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (data.size() % pageSize));
            }
        }
        else {
            return data;
        }

    }
}
