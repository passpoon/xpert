package com.crossent.monitoring.portal.common.context;

import java.util.HashMap;
import java.util.Map;

public class DefaultServiceContext {

    Map<String,Object> attribute = new HashMap<String,Object>();



    public void addAttribute(String key, Object value){
        if(attribute == null){
            attribute = new HashMap<String,Object>();
        }

        attribute.put(key, value);
    }


    public Object getAttribute(String key){
        if( attribute == null){
            return null;
        }
        return attribute.get(key);
    }

    public <T> T getAttribute(String key, Class<T> valuType){
        if( attribute == null){
            return null;
        }
        return (T) attribute.get(key);
    }
}
