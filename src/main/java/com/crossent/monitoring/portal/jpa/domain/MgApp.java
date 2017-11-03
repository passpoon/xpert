package com.crossent.monitoring.portal.jpa.domain;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_app", schema = "mondb")
@IdClass(MgAppPK.class)
public class MgApp implements Serializable{
    private Integer monGroupId;
    private Integer appResourceId;
    private AppResource appResource;
    private String dashboardYn;
    private String monitoringYn;
    private MonGroup monGroup;

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "app_resource_id", nullable = false)
    public Integer getAppResourceId() { return appResourceId; }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "app_resource_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public AppResource getAppResource() {
        return appResource;
    }

    public void setAppResource(AppResource appResource) {
        this.appResource = appResource;
    }



    @Basic
    @Column(name = "dashboard_yn", nullable = true, length = 1)
    public String getDashboardYn() {
        return dashboardYn;
    }

    public void setDashboardYn(String dashboardYn) {
        this.dashboardYn = dashboardYn;
    }

    @Basic
    @Column(name = "monitoring_yn", nullable = true, length = 1)
    public String getMonitoringYn() {
        return monitoringYn;
    }

    public void setMonitoringYn(String monitoringYn) {
        this.monitoringYn = monitoringYn;
    }


    @ManyToOne
    @JoinColumn(name = "mon_group_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public MonGroup getMonGroup() {
        return monGroup;
    }

    public void setMonGroup(MonGroup monGroup) {
        this.monGroup = monGroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgApp{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
