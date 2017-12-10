package com.crossent.monitoring.portal.mongroup.monitoring.dto;

import java.util.ArrayList;
import java.util.List;

public class ServerGroupStatusesResDto {

    Integer serverGroupId;

    String serverGroupName;

    String description;

    List<MeasurementStatusDto> measurementStatuses;

    ProcessStatusDto processStatus;

    public ServerGroupStatusesResDto(){

    }

    public Integer getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Integer serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public String getServerGroupName() {
        return serverGroupName;
    }

    public void setServerGroupName(String serverGroupName) {
        this.serverGroupName = serverGroupName;
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


    public void addMeasurementStatus(MeasurementStatusDto measurementStatusDto){
        if(measurementStatuses == null){
            measurementStatuses = new ArrayList<MeasurementStatusDto>();
        }
        measurementStatuses.add(measurementStatusDto);
    }


    public void setMeasurementStatuses(List<MeasurementStatusDto> measurementStatuses) {
        this.measurementStatuses = measurementStatuses;
    }

    public ProcessStatusDto getProcessStatus() {
        return processStatus;
    }



    public void setProcessStatus(ProcessStatusDto processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerGroupStatusesResDto{");
        sb.append("serverGroupId=").append(serverGroupId);
        sb.append(", serverGroupName='").append(serverGroupName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", measurementStatuses=").append(measurementStatuses);
        sb.append(", processStatus=").append(processStatus);
        sb.append('}');
        return sb.toString();
    }
}
