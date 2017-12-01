package com.crossent.monitoring.portal.common.constants;

public enum MgGroupUserType {
    MANAGER("0000300001"), OPERRATOR("0000300002");

    String code;

    private MgGroupUserType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MgGroupUserType forCode(String code){
        switch(code){
            case "0000300001":
                return MgGroupUserType.MANAGER;
            case "0000300002":
                return MgGroupUserType.OPERRATOR;
            default:
                return null;
        }
    }
}
