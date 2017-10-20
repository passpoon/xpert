package com.crossent.monitoring.portal.common.exception;

public class BusinessException extends BaseException {


    public BusinessException(String message) {
        super(message);
    }


    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
