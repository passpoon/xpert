package com.crossent.monitoring.portal.mongroup.mng.dto;

import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;

import javax.persistence.*;
import java.io.Serializable;

public class MgAppGroupDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String dashboardYn;
    private String monitoringYn;

    private Integer monGroupId;
    private Integer appInfoId;
    private String appInfoName;


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

    public Integer getAppInfoId() { return appInfoId; }

    public void setAppInfoId(Integer appInfoId) { this.appInfoId = appInfoId; }

    public String getAppInfoName() { return appInfoName; }

    public void setAppInfoName(String appInfoName) { this.appInfoName = appInfoName; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppGroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append(", appInfoId='").append(appInfoId).append('\'');
        sb.append(", appInfoName='").append(appInfoName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
