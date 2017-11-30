package com.crossent.monitoring.portal.common.constants;

public class Constants {

    public static final String MG_GROUP_MANAGER="0000300001"; // static Constants 재생성않고 바로 사용가능, final 상수
    public static final String MG_GROUP_OPERRATOR="0000300002";


    public static final String FUNC_TYPE_AVERAGE_HIGH="0000500001";
    public static final String FUNC_TYPE_AVERAGE_LOW="0000500002";
    public static final String FUNC_TYPE_IO_USAGE="0000500003";
    public static final String FUNC_TYPE_VOLUME_HIGH="0000500004";
    public static final String FUNC_TYPE_VOLUME_LOW="0000500005";

    public static final String METRIC_TYPE_STRING="0000100001";
    public static final String METRIC_TYPE_INT="0000100002";
    public static final String METRIC_TYPE_DOUBLE="0000100003";


    public static String PROC_STAT_KEY_NORMAL="normal";
    public static String PROC_STAT_KEY_ABNORMAL="abnormal";
    public static String PROC_STAT_KEY_TOTAL="total";
    public static String[] PROC_STAT_KEYS = {"sleeping", "running", "paging", "stopped", "blocked", "dead", "zombies", "unknown"};
}
