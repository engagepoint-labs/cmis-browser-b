package com.engagepoint.university.ep2013b.browser.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TablePage implements Serializable {

    private List<BrowserItem> items = new ArrayList<BrowserItem>();
    private  int totalPages = 0;

    public TablePage(){

    }

    public TablePage(List<BrowserItem> items, int totalPages) {
        this.items = items;
        this.totalPages = totalPages;
    }

    public List<BrowserItem> getItems() {
        return items;
    }

    public void setItems(List<BrowserItem> items) {
        this.items = items;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
