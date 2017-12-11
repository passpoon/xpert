package com.crossent.monitoring.portal.mongroup.monitoring.dto;

public class StatisticsResDto {

    Integer allCnt;
    Integer runCnt;
    Integer stopCnt;
    Integer errorCnt;


    public Integer getAllCnt() {
        return allCnt;
    }

    public void setAllCnt(Integer allCnt) {
        this.allCnt = allCnt;
    }

    public Integer getRunCnt() {
        return runCnt;
    }

    public void setRunCnt(Integer runCnt) {
        this.runCnt = runCnt;
    }

    public Integer getStopCnt() {
        return stopCnt;
    }

    public void setStopCnt(Integer stopCnt) {
        this.stopCnt = stopCnt;
    }

    public Integer getErrorCnt() {
        return errorCnt;
    }

    public void setErrorCnt(Integer errorCnt) {
        this.errorCnt = errorCnt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatisticsResDto{");
        sb.append("allCnt=").append(allCnt);
        sb.append(", runCnt=").append(runCnt);
        sb.append(", stopCnt=").append(stopCnt);
        sb.append(", errorCnt=").append(errorCnt);
        sb.append('}');
        return sb.toString();
    }
}
