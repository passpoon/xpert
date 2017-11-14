package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.Server;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "mg_server_group", schema = "mondb", catalog = "")
public class MgServerGroup implements Serializable {
    private Integer id;
    private Integer monGroupId;
    private String name;
    private Integer serverTypeId;
    private String description;
    private String dashboardYn;
    private String monitoringYn;

    @JsonIgnore
    private MonGroup monGroup;

    @JsonIgnore
    private ServerType serverType;

    @JsonIgnore
    private Collection<MgServerGroupCriticalValue> mgServerGroupCriticalValues;

    @JsonIgnore
    private Collection<MgServer> mgServers;

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
    @JoinColumn(name = "server_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    //추가
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() { return monGroupId; }

    public void setMonGroupId(Integer monGroupId) { this.monGroupId = monGroupId; }

    @Column(name = "server_type_id", nullable = false)
    public Integer getServerTypeId() { return serverTypeId; }

    public void setServerTypeId(Integer serverTypeId) { this.serverTypeId = serverTypeId; }

    //추가
    @OneToMany(mappedBy = "mgServerGroup")
    public Collection<MgServerGroupCriticalValue> getMgServerGroupCriticalValues() { return mgServerGroupCriticalValues; }

    public void setMgServerGroupCriticalValues(Collection<MgServerGroupCriticalValue> mgServerGroupCriticalValues) { this.mgServerGroupCriticalValues = mgServerGroupCriticalValues; }

    //추가
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "mg_server_group_server",
            joinColumns = {@JoinColumn(name = "server_group_id", referencedColumnName = "id")},
            inverseJoinColumns ={ @JoinColumn(name = "mon_group_id", referencedColumnName = "mon_group_id"), @JoinColumn(name = "server_resource_id", referencedColumnName = "server_resource_id")})
    public Collection<MgServer> getMgServers() { return mgServers; }

    public void setMgServers(Collection<MgServer> mgServers) { this.mgServers = mgServers; }


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
