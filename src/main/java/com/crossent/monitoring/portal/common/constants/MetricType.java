package com.crossent.monitoring.portal.common.constants;

public enum MetricType {
    STRING("0000100001"),  INT("0000100002"), DOUBLE("0000100003");

    String code;

    private  MetricType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MetricType forCode(String code){
        switch(code){
            case "0000100001":
                return MetricType.STRING;
            case "0000100002":
                return MetricType.INT;
            case "0000100003":
                return MetricType.DOUBLE;

            default:
                return null;
        }
    }
}
