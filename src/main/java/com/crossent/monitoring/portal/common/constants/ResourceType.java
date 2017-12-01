package com.crossent.monitoring.portal.common.constants;

public enum ResourceType {
    SERVER("0000400001"), SERVER_GROUP("0000400002"), APPLICATION("0000400003"), APPLICATION_GROUP("0000400004"), LOG("0000400005");

    String code;

    private ResourceType(String code){
        this.code = code;

    }

    public String getCode() {
        return code;
    }

    public static ResourceType forCode(String code){
        switch(code){
            case "0000400001":
                return ResourceType.SERVER;
            case "0000400002":
                return ResourceType.SERVER_GROUP;
            case "0000400003":
                return ResourceType.APPLICATION;
            case "0000400004":
                return ResourceType.APPLICATION_GROUP;
            case "0000400005":
                return ResourceType.LOG;
            default:
                return null;
        }
    }
}
