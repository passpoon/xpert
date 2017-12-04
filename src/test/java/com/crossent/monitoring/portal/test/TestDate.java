package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.common.lib.util.DateUtil;

import java.util.Date;
import java.util.Locale;

import static com.crossent.monitoring.portal.common.lib.util.DateUtil.*;

public class TestDate {

    public static void main(String[] args) throws Exception {
        //                              2017-11-29T05:41:40.000Z
        Date date = utcTimestampToDate("2017-11-29T20:48:00.000Z");

        System.out.println(date);

        System.out.println(System.currentTimeMillis());

        long t = 1511798070000L;

        Date d = new Date(t);

        System.out.println(d);


        //public static
        //2017-10-31T00:00:00


        String nd1 = "20171202201531";

        java.util.Date  ud  =DateUtil.stringToDate(nd1, DateUtil.DATE_HMS_PATTERN);

//        String nd2 = DateUtil.dateToString(ud, TIMESTAMP_T_PATTERN,Locale.KOREA);
        String nd2 = DateUtil.dateToString(ud, TIMESTAMP_T_PATTERN);
        System.out.println(nd2);


        System.out.println(DateUtil.convertDateFormat(nd1, DATE_HMS_PATTERN, TIMESTAMP_T_PATTERN));

    }
}
