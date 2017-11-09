package com.crossent.monitoring.portal.mongroup.mng.dto;



import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.MgAppPK;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;

import javax.persistence.*;
import java.io.Serializable;

public class MgAppDto implements Serializable{
    private Integer monGroupId;
    private Integer appResourceId;
    private String dashboardYn;
    private String monitoringYn;
    private String applicationName;

    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    public Integer getAppResourceId() { return appResourceId; }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
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

    public String getApplicationName() { return applicationName; }

    public void setApplicationName(String applicationName) { this.applicationName = applicationName; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgApp{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append(", applicationName='").append(applicationName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
