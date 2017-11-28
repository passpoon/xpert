package com.crossent.monitoring.portal.common.constants;

import com.crossent.monitoring.portal.common.exception.BusinessException;

public enum StatusEnum {
    Nomal(0), Warning(1), Critical(2);

    int status;
    private StatusEnum(int status){
        this.status = status;
    }

    public int getVal(){
        return status;
    }

    public StatusEnum getMax(StatusEnum c){
        if(this.getVal() >= c.getVal()){
            return this;
        }else{
            return c;
        }
    }

    public String getString(){
        switch (status){
            case 0:
                return "정상";

            case 1:
                return "경고";

            case 2:
                return "심각";

            default:
                throw new BusinessException("정의되지 않은 코드");
        }
    }

    @Override
    public String toString() {
        return "StatusEnum{" +
                "status=" + status +
                '}';
    }
}
