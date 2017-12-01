package com.crossent.monitoring.portal.common.lib.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MapUtil {

    public static List<String> listKeys(java.util.Map<String,String> map){

        Iterator<String> iterator = map.keySet().iterator();

        List<String> keyList = new ArrayList<String>();

        if(iterator.hasNext()){
            String key = iterator.next();
            keyList.add(key);
        }
        return keyList;
    }
}
