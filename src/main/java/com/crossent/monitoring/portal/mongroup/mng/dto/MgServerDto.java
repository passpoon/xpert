package com.crossent.monitoring.portal.mongroup.mng.dto;

import com.crossent.monitoring.portal.jpa.domain.MgLogEvent;
import com.crossent.monitoring.portal.jpa.domain.MgServerPK;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

public class MgServerDto implements Serializable {
    private Integer monGroupId;
    private Integer serverResourceId;
    private String dashboardYn;
    private String monitoringYn;
    private String serverName;
    private String hostName;

    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    public String getDashboardYn() {
        return dashboardYn;
    }

    public void setDashboardYn(String dashboardYn) {
        this.dashboardYn = dashboardYn;
    }

    public String getMonitoringYn() {
        return monitoringYn;
    }

    public void setMonitoringYn(String monitoringYn) { this.monitoringYn = monitoringYn; }

    public String getServerName() { return serverName; }

    public void setServerName(String serverName) { this.serverName = serverName; }

    public String getHostName() { return hostName; }

    public void setHostName(String hostName) { this.hostName = hostName; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServer{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
