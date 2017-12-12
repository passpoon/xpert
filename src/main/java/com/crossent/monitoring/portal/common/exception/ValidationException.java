package com.crossent.monitoring.portal.common.exception;

import com.crossent.monitoring.portal.common.lib.util.MessageUtil;

public class ValidationException extends BaseException {


    public ValidationException(String messageCode) {
        super(MessageUtil.getMessage(messageCode));
    }

    public ValidationException(String messageCode, String ...params) {

        super(MessageUtil.getMessage(messageCode, params));
    }


    public ValidationException(String messageCode, Throwable throwable) {
        super(MessageUtil.getMessage(messageCode), throwable);
    }

    public ValidationException(Throwable throwable, String messageCode, String ...params) {

        super(MessageUtil.getMessage(messageCode, params), throwable);
    }
}
