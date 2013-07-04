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
    private Service service;

    public LazyItemModel(Service service) {
        this.service = service;
    }

    @Override
    public List<BrowserItem> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        return service.getItems(first, pageSize);
    }

    @Override
    public Object getRowKey(BrowserItem item) {
        return item.getId();
    }

    @Override
    public int getRowCount() {
        return service.getItemsCount();
    }
}
