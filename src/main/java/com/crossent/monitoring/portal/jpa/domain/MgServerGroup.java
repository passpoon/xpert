package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_server_group", schema = "mondb", catalog = "")
public class MgServerGroup implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String dashboardYn;
    private String monitoringYn;
    private MonGroup monGroup;
    private ServerType serverType;

    @Id
    @Column(name = "id", nullable = false)
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
    @JoinColumn(name = "mon_group_id", referencedColumnName = "id", nullable = false)
    public MonGroup getMonGroup() {
        return monGroup;
    }

    public void setMonGroup(MonGroup monGroup) {
        this.monGroup = monGroup;
    }

    @ManyToOne
    @JoinColumn(name = "server_type_id", referencedColumnName = "id", nullable = false)
    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerGroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dashboardYn='").append(dashboardYn).append('\'');
        sb.append(", monitoringYn='").append(monitoringYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
