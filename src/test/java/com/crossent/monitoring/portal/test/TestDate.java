package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.common.lib.util.DateUtil;

import java.util.Date;

import static com.crossent.monitoring.portal.common.lib.util.DateUtil.*;

public class TestDate {

    public static void main(String[] args) throws Exception {
        //                              2017-11-29T05:41:40.000Z
        Date date = utcTimestampToDate("2017-11-28T02:51:20.000Z");

        System.out.println(date);

        System.out.println(System.currentTimeMillis());

        long t = 1511798070000L;

        Date d = new Date(t);

        System.out.println(d);
    }
}
