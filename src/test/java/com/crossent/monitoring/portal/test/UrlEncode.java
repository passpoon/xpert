package com.crossent.monitoring.portal.test;

import java.net.URLEncoder;

public class UrlEncode {

    public static void main(String[] args){
        try {
            String enc = URLEncoder.encode("{\"keywords\":{\"name\":\"measurement\"},\"rangeType\":\"1m\",\"rangeStart\":null,\"rangeEnd\":null}");
            System.out.println(enc);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
