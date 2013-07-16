package com.engagepoint.university.ep2013b.browser.cmis;

import org.apache.chemistry.opencmis.client.api.QueryStatement;

import java.io.Serializable;
import java.util.*;


public class AdvSearchParams implements Serializable {

    private Map<String, Object> params = new HashMap<String, Object>();

    public AdvSearchParams() {

        params.put("docType", null); //// ???
        params.put("fromDate",null);
        params.put("toDate", null);
        params.put("contentType", null);
        params.put("size", null);
        params.put("text", null);

    }


    public AdvSearchParams(
            String docType, Calendar fromDate,
            Calendar toDate, String contentType, Integer size, String text) {

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

    public String createQueryString() {

        String preparedQueryString = "";

        String inDocums = "SELECT ?, ? FROM ?";
        String anyOptions = " WHERE ";
        String multiple = " AND ";


        String paramDateFrom = "cmis:creationDate >= TIMESTAMP ?";
        String paramDateTo = "cmis:creationDate <= TIMESTAMP ?";

        String paramContType = "cmis:objectTypeId = ?"; // "cmis:contentStreamMimeType = ?";
        String paramSize = "cmis:contentStreamLength = ?";
        String paramText = "CONTAINS(?)";

        preparedQueryString = inDocums;
        //// anyOptions + paramDateFrom + multiple + paramDateTo

        boolean flagAnyOptions = false;
        boolean flagMulti = false;


        if(params.get("fromDate") != null){
            flagAnyOptions = true;
            preparedQueryString = preparedQueryString +  anyOptions +  paramDateFrom;
        }


        if(params.get("toDate") != null){
            if(!flagAnyOptions)  {
                preparedQueryString = preparedQueryString +  anyOptions;
                flagAnyOptions = true;
            }
            else {
                preparedQueryString = preparedQueryString +  multiple;
            }
            preparedQueryString = preparedQueryString + paramDateTo;
        }


        if(params.get("contentType") != null){
            if(!flagAnyOptions)  {
                preparedQueryString = preparedQueryString +  anyOptions;
                flagAnyOptions = true;
            }
            else {
                 preparedQueryString = preparedQueryString +  multiple;

            }
            preparedQueryString = preparedQueryString + paramContType;
        }


        if(params.get("size") != null){
            if(!flagAnyOptions)  {
                preparedQueryString = preparedQueryString +  anyOptions;
                flagAnyOptions = true;
            }
            else {
                preparedQueryString = preparedQueryString +  multiple;

            }
            preparedQueryString = preparedQueryString + paramSize;
        }


        if(params.get("text") != null){
            if(!flagAnyOptions)  {
                preparedQueryString = preparedQueryString +  anyOptions;
                flagAnyOptions = true;
            }
            else {
                    preparedQueryString = preparedQueryString +  multiple;


            }
            preparedQueryString = preparedQueryString + paramText;
        }

        System.out.println("preparedQueryString = " + preparedQueryString);

        return preparedQueryString;

    }


    public QueryStatement setQueryParams(QueryStatement query) {

        String type = "cmis:document";   /// ???

        //// everytimes
        query.setProperty(1, type, "cmis:objectId");
        query.setProperty(2, type, "cmis:name");

        //// by user request values
        //query.setProperty(3, type, "cmis:creationDate");

        int paramCounter = 3;  // have already 2 params

        // type
        if(params.get("docType") != null){
            query.setType(paramCounter++, params.get("docType").toString());
        }


        if(params.get("fromDate") != null){
            query.setDateTime(paramCounter++, (Calendar) params.get("fromDate"));
        }


        if(params.get("toDate") != null){
            query.setDateTime(paramCounter++, (Calendar) params.get("toDate"));
        }


        if(params.get("contentType") != null){
            query.setString(paramCounter++, params.get("contentType").toString());
        }


        if(params.get("size") != null){
            query.setNumber(paramCounter++, (Number) params.get("size"));
        }


        if(params.get("text") != null){
            query.setString(paramCounter++, params.get("text").toString());
        }

        System.out.println("QueryStatement = " + query.toQueryString());


//
//        // query.setProperty(4, "cmis:document", "cmis:creationDate");
//        Calendar dd = new GregorianCalendar(2013, 6, 16, 3, 0, 0);  // month and day -1
//        query.setDateTime(5, dd);
//        dd = new GregorianCalendar(2013, 6, 17, 3, 0, 0);
//        query.setDateTime(6, dd);
//
//        //query.setId(6, id);
//

        return query;

    }

}


class ParamPair implements Serializable {

    private String querySubString = "";
    private Object value = "";


    ParamPair() {
    }

    ParamPair(String querySubString, Object value) {
        this.querySubString = querySubString;
        this.value = value;
    }

    String getQuerySubString() {
        return querySubString;
    }

    void setQueryString(String querySubString) {
        this.querySubString = querySubString;
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
                "queryString='" + querySubString + '\'' +
                ", value=" + value +
                '}';
    }
}


