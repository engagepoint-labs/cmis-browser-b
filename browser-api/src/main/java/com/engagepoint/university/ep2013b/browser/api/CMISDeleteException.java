package com.engagepoint.university.ep2013b.browser.api;

import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;


public class CMISDeleteException extends CmisBaseException {

    public static final String EXCEPTION_NAME = "deleting";

    @Override
    public String getExceptionName() {
        return EXCEPTION_NAME;
    }




}
