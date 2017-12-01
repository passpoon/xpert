package com.crossent.monitoring.portal.common.constants;

public enum  ServerState {
    NORMAL("0000100001"), WARNING("0000100002"), CRITICAL("0000100003");

    String code;

    private ServerState(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ServerState forCode(String code){
        switch(code){
            case "0000100001":
                return ServerState.NORMAL;
            case "0000100002":
                return ServerState.WARNING;
            case "0000100003":
                return ServerState.CRITICAL;
            default:
                return null;
        }
    }
}
