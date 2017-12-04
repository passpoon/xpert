package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.common.lib.util.DateUtil;

public class Test {

    public static void main(String[] args){
       String a = "ABC";
       System.out.println(a.substring(a.length() -1));

       String date = "20171201123530";

       System.out.println(DateUtil.addDaysForDateHms(date, -3));
    }
}
