package com.crossent.monitoring.portal.common.exception;

import com.crossent.monitoring.portal.common.lib.util.MessageUtil;

public class BusinessException extends BaseException {


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String messageCode, String ...params) {

        super(MessageUtil.getMessage(messageCode, params));
    }


    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
