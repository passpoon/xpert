package com.crossent.monitoring.portal.common.constants;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;

public enum StatusEnum {
    NA(-1), Nomal(0), Warning(1), Critical(2), Error(3), ;

    int status;
    private StatusEnum(int status){
        this.status = status;
    }

    public int getVal(){
        return status;
    }

    public StatusEnum max(StatusEnum c){
        if(this.getVal() >= c.getVal()){
            return this;
        }else{
            return c;
        }
    }

    public String getString(){
        switch (status){
            case -1:
                return MessageUtil.getMessage("statusEnumNa");
            case 0:
                return MessageUtil.getMessage("statusEnumNormal");

            case 1:
                return MessageUtil.getMessage("statusEnumWarning");

            case 2:
                return MessageUtil.getMessage("statusEnumCritical");

            case 3:
                return MessageUtil.getMessage("statusEnumError");

            default:
                throw new BusinessException("unDefCode", ""+status);
        }
    }

    @Override
    public String toString() {
        return "StatusEnum{" +
                "status=" + getString() +
                '}';
    }
}
