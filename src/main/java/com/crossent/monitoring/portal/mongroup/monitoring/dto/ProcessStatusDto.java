package com.crossent.monitoring.portal.mongroup.monitoring.dto;

public class ProcessStatusDto{
    String status;
    Long totalCnt;
    Long normalCnt;
    Long abnormalCnt;
    String content;


    public ProcessStatusDto(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Long totalCnt) {
        this.totalCnt = totalCnt;
    }

    public Long getNormalCnt() {
        return normalCnt;
    }

    public void setNormalCnt(Long normalCnt) {
        this.normalCnt = normalCnt;
    }

    public Long getAbnormalCnt() {
        return abnormalCnt;
    }

    public void setAbnormalCnt(Long abnormalCnt) {
        this.abnormalCnt = abnormalCnt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProcessStatusDto{");
        sb.append("status='").append(status).append('\'');
        sb.append(", totalCnt=").append(totalCnt);
        sb.append(", normalCnt=").append(normalCnt);
        sb.append(", abnormalCnt=").append(abnormalCnt);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
