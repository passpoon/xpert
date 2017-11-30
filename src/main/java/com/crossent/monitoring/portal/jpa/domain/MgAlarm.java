package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "mg_alarm", schema = "mondb")
public class MgAlarm implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private MonGroup monGroup;

    @JsonIgnore
    private Collection<MgAppGroup> mgAppGroups;
    @JsonIgnore
    private Collection<MgApp> mgApps;
    @JsonIgnore
    private Collection<MgLogEvent> mgLogEvents;
    @JsonIgnore
    private Collection<MgServerGroup> mgServerGroups;
    @JsonIgnore
    private Collection<MgServer> mgServers;


    @Id
    @Column(name = "id", nullable = false)
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
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @ManyToOne
    @JoinColumn(name = "mon_group_id", referencedColumnName = "id", nullable = false)
    public MonGroup getMonGroup() {
        return monGroup;
    }

    public void setMonGroup(MonGroup monGroup) {
        this.monGroup = monGroup;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mg_alarm_app_group_map",
            joinColumns = @JoinColumn(name = "mg_alarm_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "mg_app_group_id", referencedColumnName = "id"))
    public Collection<MgAppGroup> getMgAppGroups() {
        return mgAppGroups;
    }

    public void setMgAppGroups(Collection<MgAppGroup> mgAppGroups) {
        this.mgAppGroups = mgAppGroups;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mg_alarm_app_map",
            joinColumns = @JoinColumn(name = "mg_alarm_id", referencedColumnName = "id"),
            inverseJoinColumns = {@JoinColumn(name = "app_resource_id", referencedColumnName = "app_resource_id"),
                    @JoinColumn(name = "mon_group_id", referencedColumnName = "mon_group_id")})
    public Collection<MgApp> getMgApps() {
        return mgApps;
    }

    public void setMgApps(Collection<MgApp> mgApps) {
        this.mgApps = mgApps;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mg_alarm_log_event_map",
            joinColumns = @JoinColumn(name = "mg_alarm_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "mg_log_event_id", referencedColumnName = "id"))
    public Collection<MgLogEvent> getMgLogEvents() {
        return mgLogEvents;
    }

    public void setMgLogEvents(Collection<MgLogEvent> mgLogEvents) {
        this.mgLogEvents = mgLogEvents;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mg_alarm_server_group_map",
            joinColumns = @JoinColumn(name = "mg_alarm_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "mg_server_group_id", referencedColumnName = "id"))
    public Collection<MgServerGroup> getMgServerGroups() {
        return mgServerGroups;
    }

    public void setMgServerGroups(Collection<MgServerGroup> mgServerGroups) {
        this.mgServerGroups = mgServerGroups;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mg_alarm_server_map",
            joinColumns = @JoinColumn(name = "mg_alarm_id", referencedColumnName = "id"),
            inverseJoinColumns = {@JoinColumn(name = "server_resource_id", referencedColumnName = "server_resource_id"),
                    @JoinColumn(name = "mon_group_id", referencedColumnName = "mon_group_id")})
    public Collection<MgServer> getMgServers() {
        return mgServers;
    }

    public void setMgServers(Collection<MgServer> mgServers) {
        this.mgServers = mgServers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarm{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
