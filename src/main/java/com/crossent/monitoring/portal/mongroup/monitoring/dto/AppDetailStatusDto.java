package com.crossent.monitoring.portal.mongroup.monitoring.dto;

import java.util.ArrayList;
import java.util.List;

public class AppDetailStatusDto{
    Integer appResourceId;
    String appName;
    Integer serverResourceId;
    String serverName;
    String hostName;
    String ip;

    List<MeasurementDetail> measurements;


    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<MeasurementDetail> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDetail> measurements) {
        this.measurements = measurements;
    }

    public void addMeasurement(MeasurementDetail measurementDetail){
        if(measurements == null){
            measurements = new ArrayList<MeasurementDetail>();
        }
        measurements.add(measurementDetail);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppDetailStatusDto{");
        sb.append("appResourceId=").append(appResourceId);
        sb.append(", appName='").append(appName).append('\'');
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", measurements=").append(measurements);
        sb.append('}');
        return sb.toString();
    }
}
