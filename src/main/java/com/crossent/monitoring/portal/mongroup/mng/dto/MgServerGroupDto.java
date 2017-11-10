package com.crossent.monitoring.portal.mongroup.mng.dto;

import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.ServerType;

import javax.persistence.*;
import java.io.Serializable;

public class MgServerGroupDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String dashboardYn;
    private String monitoringYn;

    private Integer monGroupId;
    private Integer serverTypeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setMonitoringYn(String monitoringYn) {
        this.monitoringYn = monitoringYn;
    }


    public Integer getMonGroupId() { return monGroupId; }

    public void setMonGroupId(Integer monGroupId) { this.monGroupId = monGroupId; }

    public Integer getServerTypeId() { return serverTypeId; }

    public void setServerTypeId(Integer serverTypeId) { this.serverTypeId = serverTypeId; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerGroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append(", monGroupId='").append(monGroupId).append('\'');
        sb.append(", serverTypeId='").append(serverTypeId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
