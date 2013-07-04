package core;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir.ovcharov
 * Date: 7/4/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LazyItemModel extends LazyDataModel<BrowserItem> {
    private List<BrowserItem> datasource;

    public LazyItemModel(List<BrowserItem> datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<BrowserItem> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<BrowserItem> data = new ArrayList<BrowserItem>();

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

    public List<BrowserItem> getDatasource() {
        return datasource;
    }
}
