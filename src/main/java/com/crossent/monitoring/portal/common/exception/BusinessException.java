package com.crossent.monitoring.portal.common.exception;

import com.crossent.monitoring.portal.common.lib.util.MessageUtil;

public class BusinessException extends BaseException {


    public BusinessException(String messageCode) {
        super(MessageUtil.getMessage(messageCode));
    }

    public BusinessException(String messageCode, String ...params) {

        super(MessageUtil.getMessage(messageCode, params));
    }


    public BusinessException(String messageCode, Throwable throwable) {
        super(MessageUtil.getMessage(messageCode), throwable);
    }

    public BusinessException(Throwable throwable, String messageCode, String ...params) {

        super(MessageUtil.getMessage(messageCode, params), throwable);
    }
}
