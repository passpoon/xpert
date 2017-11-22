package com.crossent.monitoring.portal.mongroup.moniotring.dto;

import java.io.Serializable;
import java.util.List;

public class ServerDto implements Serializable {

    private String serverName;
    private String hostName;
    private List<MeasurementDto> measurements;


    public String getServerName() { return serverName; }

    public void setServerName(String serverName) { this.serverName = serverName; }

    public String getHostName() { return hostName; }

    public void setHostName(String hostName) { this.hostName = hostName; }

    public List<MeasurementDto> getMeasurements() { return measurements; }

    public void setMeasurements(List<MeasurementDto> measurements) { this.measurements = measurements; }
}
