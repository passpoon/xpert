package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "mg_app_group", schema = "mondb", catalog = "")
public class MgAppGroup implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String dashboardYn;
    private String monitoringYn;
    private Integer monGroupId;
    private Integer appInfoId;

    @JsonIgnore
    private MonGroup monGroup;

    @JsonIgnore
    private AppInfo appInfo;

    @JsonIgnore
    private Collection<MgAppGroupCriticalValue> mgAppGroupCriticalValues;

    @JsonIgnore
    private Collection<MgApp> mgApps;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @ManyToOne
    @JoinColumn(name = "app_info_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    //추가
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() { return monGroupId; }

    public void setMonGroupId(Integer monGroupId) { this.monGroupId = monGroupId; }

    @Column(name = "app_info_id", nullable = false)
    public Integer getAppInfoId() { return appInfoId; }

    public void setAppInfoId(Integer appInfoId) { this.appInfoId = appInfoId; }

    //추가
    @OneToMany(mappedBy = "mgAppGroup")
    public Collection<MgAppGroupCriticalValue> getMgAppGroupCriticalValues() { return mgAppGroupCriticalValues; }

    public void setMgAppGroupCriticalValues(Collection<MgAppGroupCriticalValue> mgAppGroupCriticalValues) { this.mgAppGroupCriticalValues = mgAppGroupCriticalValues; }

    //추가
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mg_app_group_app",
            joinColumns = {@JoinColumn(name = "app_group_id", referencedColumnName = "id")},
            inverseJoinColumns ={ @JoinColumn(name = "mon_group_id", referencedColumnName = "mon_group_id"), @JoinColumn(name = "app_resource_id", referencedColumnName = "app_resource_id")})
    public Collection<MgApp> getMgApps() { return mgApps; }

    public void setMgApps(Collection<MgApp> mgApps) { this.mgApps = mgApps; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppGroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
