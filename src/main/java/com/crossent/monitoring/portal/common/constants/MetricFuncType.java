package com.crossent.monitoring.portal.common.constants;

public enum MetricFuncType {
    AVERAGE_HIGH("0000500001"), AVERAGE_LOW("0000500002"), IO_USAGE("0000500003"), VOLUME_HIGH("0000500004"), VOLUME_LOW("0000500005");

    String code;

    private  MetricFuncType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MetricFuncType forCode(String code){
        if(code == null){
            return null;
        }

        switch(code){
            case "0000500001":
                return MetricFuncType.AVERAGE_HIGH;
            case "0000500002":
                return MetricFuncType.AVERAGE_LOW;
            case "0000500003":
                return MetricFuncType.IO_USAGE;
            case "0000500004":
                return MetricFuncType.VOLUME_HIGH;
            case "0000500005":
                return MetricFuncType.VOLUME_LOW;
            default:
                return null;
        }
    }
}

