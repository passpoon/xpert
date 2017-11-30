package com.crossent.monitoring.portal.mongroup.moniotring.dto;

import java.util.ArrayList;
import java.util.List;

public class ServerStatusesResDto {
    Integer serverResourceId;
    String serverResourceName;
    String hostName;

    //List<String> titles;

    List<MeasurementStatusDto> measurementStatuses;

    ProcessStatusDto processStatus;


    public ServerStatusesResDto(){

    }


    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    public String getServerResourceName() {
        return serverResourceName;
    }

    public void setServerResourceName(String serverResourceName) {
        this.serverResourceName = serverResourceName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


//    public List<String> getTitles() {
//        return titles;
//    }
//
//    public void setTitles(List<String> titles) {
//        this.titles = titles;
//    }
//
//    public void addTile(String title){
//        if(this.titles == null){
//            titles = new ArrayList<String>();
//
//        }
//
//        titles.add(title);
//    }

    public List<MeasurementStatusDto> getMeasurementStatuses() {
        return measurementStatuses;
    }

    public void setMeasurementStatuses(List<MeasurementStatusDto> measurementStatuses) {
        this.measurementStatuses = measurementStatuses;
    }

    public void addMeasurementStatus(MeasurementStatusDto dto){
        if(this.measurementStatuses == null){
            measurementStatuses = new ArrayList<MeasurementStatusDto>();
        }

        measurementStatuses.add(dto);

    }

    public ProcessStatusDto getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatusDto processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerStatusesResDto{");
        sb.append("serverResourceId=").append(serverResourceId);
        sb.append(", serverResourceName='").append(serverResourceName).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
//        sb.append(", titles=").append(titles);
        sb.append(", measurementStatuses=").append(measurementStatuses);
        sb.append(", processStatus=").append(processStatus);
        sb.append('}');
        return sb.toString();
    }
}
