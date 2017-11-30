package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "server_type", schema = "mondb", catalog = "")
public class ServerType implements Serializable {
    private Integer id;
    private String name;
    private String description;

    @JsonIgnore
    private Collection<Measurement> measurements;

    @JsonIgnore
    private Collection<ServerResource> serverResources;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Id가 autoIncrement 일 때 추가
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "server_type_measurement_map",
            joinColumns = @JoinColumn(name = "server_type_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "measurement_id", referencedColumnName="id"))
    public Collection<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Collection<Measurement> measurements) {
        this.measurements = measurements;
    }

    //common
    @OneToMany(mappedBy = "serverType")
    public Collection<ServerResource> getServerResources() { return serverResources; }

    public void setServerResources(Collection<ServerResource> serverResources) { this.serverResources = serverResources; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerType{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
