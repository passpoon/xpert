package com.crossent.monitoring.portal.common.constants;

public enum LogState {
    START("0000200001"), STOP("0000200002"), DEBUG("0000200006"), INFO("0000200005"), WARN("0000200004"), ERROR("0000200003");

    String code;
    private  LogState(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static LogState forCode(String code){
        switch(code){
            case "0000200001":
                return LogState.START;
            case "0000200002":
                return LogState.STOP;
            case "0000200006":
                return LogState.DEBUG;
            case "0000200005":
                return LogState.INFO;
            case "0000200004":
                return LogState.WARN;
            case "0000200003":
                return LogState.ERROR;
            default:
                    return null;
        }
    }

}
