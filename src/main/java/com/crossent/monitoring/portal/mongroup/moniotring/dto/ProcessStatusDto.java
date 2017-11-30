package com.crossent.monitoring.portal.mongroup.moniotring.dto;

import com.crossent.monitoring.portal.common.constants.StatusEnum;

public class ProcessStatusDto{
    String status;
    Long totalCnt;
    Long nomalCnt;
    Long abnomalCnt;
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

    public Long getNomalCnt() {
        return nomalCnt;
    }

    public void setNomalCnt(Long nomalCnt) {
        this.nomalCnt = nomalCnt;
    }

    public Long getAbnomalCnt() {
        return abnomalCnt;
    }

    public void setAbnomalCnt(Long abnomalCnt) {
        this.abnomalCnt = abnomalCnt;
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
        sb.append(", nomalCnt=").append(nomalCnt);
        sb.append(", abnomalCnt=").append(abnomalCnt);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
