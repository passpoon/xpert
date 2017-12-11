package com.crossent.monitoring.portal.mongroup.monitoring.dto;

import java.util.ArrayList;
import java.util.List;

public class AppGroupStatusesResDto {


    Integer appGroupId;
    String appGroupName;

    String description;
    List<String> titles;

    List<MeasurementStatusDto> measurementStatuses;



    public AppGroupStatusesResDto(){

    }

    public Integer getAppGroupId() {
        return appGroupId;
    }

    public void setAppGroupId(Integer appGroupId) {
        this.appGroupId = appGroupId;
    }

    public String getAppGroupName() {
        return appGroupName;
    }

    public void setAppGroupName(String appGroupName) {
        this.appGroupName = appGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MeasurementStatusDto> getMeasurementStatuses() {
        return measurementStatuses;
    }

    public void setMeasurementStatuses(List<MeasurementStatusDto> measurementStatuses) {
        this.measurementStatuses = measurementStatuses;
    }

    public void addMeasurementStatus(MeasurementStatusDto measurementStatusDto){
        if(measurementStatuses == null){
            measurementStatuses = new ArrayList<MeasurementStatusDto>();
        }

        measurementStatuses.add(measurementStatusDto);
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void addTitle(String title){
        if(this.titles == null){
            titles = new ArrayList<String>();

        }

        titles.add(title);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppGroupStatusesResDto{");
        sb.append("appGroupId=").append(appGroupId);
        sb.append(", appGroupName='").append(appGroupName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", titles=").append(titles);
        sb.append(", measurementStatuses=").append(measurementStatuses);
        sb.append('}');
        return sb.toString();
    }
}
