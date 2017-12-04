package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.common.lib.util.DateUtil;

import java.util.Date;

public class UtcDateTest {

    public static void main(String[] args) throws Exception {
//        String d = "2017-11-30T08:50:00Z";
        String d = "2017-11-30T08:50:00.000Z";


        System.out.println(d.length());

        String date = DateUtil.utcTimestampToString(d, DateUtil.DATE_TIME_PATTERN);
        System.out.println(date);
    }
}
