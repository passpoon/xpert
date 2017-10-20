package com.crossent.monitoring.portal.common.exception;

public class SystemException extends BaseException {


    public SystemException(String message) {
        super(message);
    }


    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
