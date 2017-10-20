package com.crossent.monitoring.portal.common.lib.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {


    private static MessageSource messageSource;

    public MessageUtil(){

    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    public static String getMessage(String code){
        return messageSource.getMessage(code, null, null, null);
    }

    public static String getMessage(String code, String... params){
        return messageSource.getMessage(code, params, null, null);
    }


}
