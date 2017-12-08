package com.crossent.monitoring.portal.mongroup.moniotring.dto;

import java.util.ArrayList;
import java.util.List;

public class AppStatusesResDto {
    Integer appResourceId;
    String appResourceName;
    Integer serverResourceId;
    String serverResourceName;
    String hostName;

    List<String> titles;

    List<MeasurementStatusDto> measurementStatuses;


    public AppStatusesResDto(){

    }


    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    public String getAppResourceName() {
        return appResourceName;
    }

    public void setAppResourceName(String appResourceName) {
        this.appResourceName = appResourceName;
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


    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void addTile(String title){
        if(this.titles == null){
            titles = new ArrayList<String>();

        }

        titles.add(title);
    }

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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppStatusesResDto{");
        sb.append("appResourceId=").append(appResourceId);
        sb.append(", appResourceName='").append(appResourceName).append('\'');
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", serverResourceName='").append(serverResourceName).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", measurementStatuses=").append(measurementStatuses);
        sb.append('}');
        return sb.toString();
    }
}
