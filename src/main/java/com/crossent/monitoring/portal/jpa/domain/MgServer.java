package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "mg_server", schema = "mondb")
@IdClass(MgServerPK.class)
public class MgServer implements Serializable {
    private Integer monGroupId;
    private Integer serverResourceId;
    private String dashboardYn;
    private String monitoringYn;
    private MonGroup monGroup;
    private ServerResource serverResource;
    @JsonIgnore
    private Collection<MgLogEvent> mgLogEvents;

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "server_resource_id", nullable = false)
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
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
    @JoinColumn(name = "server_resource_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ServerResource getServerResource() {
        return serverResource;
    }

    public void setServerResource(ServerResource serverResource) {
        this.serverResource = serverResource;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "mg_log_event_server_map",
            joinColumns = {@JoinColumn(name = "mon_group_id", referencedColumnName = "mon_group_id"), @JoinColumn(name = "server_resource_id", referencedColumnName = "server_resource_id")},
            inverseJoinColumns = @JoinColumn(name = "mg_log_event_id", referencedColumnName = "id"))
    public Collection<MgLogEvent> getMgLogEvents() {
        return mgLogEvents;
    }

    public void setMgLogEvents(Collection<MgLogEvent> mgLogEvents) {
        this.mgLogEvents = mgLogEvents;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServer{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
