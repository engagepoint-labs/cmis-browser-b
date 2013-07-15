package com.engagepoint.university.ep2013b.browser.cmis;

import org.apache.chemistry.opencmis.client.api.QueryStatement;

import java.io.Serializable;
import java.util.*;


public class AdvSearchParams implements Serializable {

    private Map<String, ParamPair> params = new HashMap<String, ParamPair>();

    public AdvSearchParams() {

        params.put("docType", );
        params.put("fromDate", );
        params.put("toDate", );
        params.put("contentType", );
        params.put("size", );
        params.put("text", );

    }


    public AdvSearchParams(
            String docType, String contentType, Date fromDate,
            Date toDate, int size, String text) {

        params.put("docType", docType);
        params.put("fromDate", fromDate);
        params.put("toDate", toDate);
        params.put("contentType", contentType);
        params.put("size", size);
        params.put("text", text);

    }


    public Map<String, Object> getParams() {
        return params;
    }


    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


    @Override
    public String toString() {
        return "AdvSearchParams{" +
                "params=" + params +
                '}';
    }


    public String createQueryString() {


        String preparedQueryString = "";

        String inDocums = "SELECT ?, ? FROM ?";
        String anyOptions = " WHERE ";
        String multiple = " AND ";

        String paramDocType = "";
        String paramDateFrom = "cmis:creationDate >= TIMESTAMP ?";
        String paramDateTo = "cmis:creationDate <= TIMESTAMP ?";


        String paramContType = "cmis:contentStreamMimeType = ?";
        String paramSize = "cmis:contentStreamLength = ?";
        String paramText = "CONTAINS(?)";


        preparedQueryString = inDocums;
        //// anyOptions + paramDateFrom + multiple + paramDateTo

//        Set<Map.Entry<String, Object>> entry = params.entrySet();
//        for (Map.Entry<String, Object> r : entry) {
//
//
//        }


        return preparedQueryString;

    }


    public QueryStatement setQueryParams(QueryStatement query) {

        String type = "cmis:document";   /// ???

        //// everytimes
        query.setProperty(1, type, "cmis:objectId");
        query.setProperty(2, type, "cmis:name");

        //// by user request values
        query.setProperty(3, "cmis:document", "cmis:creationDate");

        // type
        query.setType(4, "cmis:document");

        // query.setProperty(4, "cmis:document", "cmis:creationDate");
        Calendar dd = new GregorianCalendar(2013, 6, 16, 3, 0, 0);  // month and day -1
        query.setDateTime(5, dd);
        dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
        query.setDateTime(6, dd);

        //query.setId(6, id);

        System.out.println("query string = " + query.toQueryString());

        return query;

    }

}


class ParamPair implements Serializable {

    private String queryString = "";
    private Object value = "";


    ParamPair() {
    }

    ParamPair(String queryString, Object value) {
        this.queryString = queryString;
        this.value = value;
    }

    String getQueryString() {
        return queryString;
    }

    void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    Object getValue() {
        return value;
    }

    void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ParamPair{" +
                "queryString='" + queryString + '\'' +
                ", value=" + value +
                '}';
    }
}


