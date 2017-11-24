package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "server_resource", schema = "mondb")
public class ServerResource  implements Serializable {
    private Integer id;
    private String name;
    private String hostName;
    private String ip;
    private String description;
    private String uuid;

    private Integer serverTypeId;

    @JsonIgnore
    private ServerType serverType;

    @JsonIgnore
    private Collection<MonGroup> monGroups;

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
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "host_name", nullable = false, length = 100)
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Basic
    @Column(name = "IP", nullable = true, length = 50)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
    @Column(name = "uuid", nullable = true, length = 45)
    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    @Basic
    @Column(name = "server_type_id", nullable = false)
    public Integer getServerTypeId() { return serverTypeId; }

    public void setServerTypeId(Integer serverTypeId) { this.serverTypeId = serverTypeId; }

    @ManyToOne
    @JoinColumn(name = "server_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ServerType getServerType() { return serverType; }

    public void setServerType(ServerType serverType) { this.serverType = serverType; }

    //common
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "mg_server",
            joinColumns = @JoinColumn(name = "server_resource_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "mon_group_id", referencedColumnName="id"))
    public Collection<MonGroup> getMonGroups() {
        return monGroups;
    }

    public void setMonGroups(Collection<MonGroup> monGroups) {
        this.monGroups = monGroups;
    }




    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerResource{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", serverTypeId='").append(serverTypeId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
