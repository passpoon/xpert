package com.crossent.monitoring.portal.test;

import java.net.URLEncoder;

public class UrlEncode {

    public static void main(String[] args){
        try {
            String enc = URLEncoder.encode("{\"keywords\":{\"field1\":\"keyword1\",\"field2\":\"keyword2\",\"field3\":\"keyword3\"},\"rangeType\":\"manual\",\"rangeStart\":\"10\",\"rangeEnd\":\"20\"}", "euc-kr");
            System.out.println(enc);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
