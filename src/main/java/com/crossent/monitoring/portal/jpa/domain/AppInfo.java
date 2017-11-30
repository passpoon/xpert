package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "app_info", schema = "mondb", catalog = "")
public class AppInfo implements Serializable {
    private Integer id;
    private String name;
    private String description;

    @JsonIgnore
    private Collection<MgAppGroup> mgAppGroups;
    @JsonIgnore
    private Collection<Measurement> measurements;
    @JsonIgnore
    private Collection<AppResource> appResources;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppInfo appInfo = (AppInfo) o;

        if (id != null ? !id.equals(appInfo.id) : appInfo.id != null) return false;
        if (name != null ? !name.equals(appInfo.name) : appInfo.name != null) return false;
        if (description != null ? !description.equals(appInfo.description) : appInfo.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "appInfo")
    public Collection<MgAppGroup> getMgAppGroups() {
        return mgAppGroups;
    }

    public void setMgAppGroups(Collection<MgAppGroup> mgAppGroups) {
        this.mgAppGroups = mgAppGroups;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_info_measurement_map",
            joinColumns = @JoinColumn(name = "app_info_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "measurement_id", referencedColumnName="id"))
    public Collection<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Collection<Measurement> measurements) {
        this.measurements = measurements;
    }

    @OneToMany(mappedBy = "appInfo")
    public Collection<AppResource> getAppResources() { return appResources; }

    public void setAppResources(Collection<AppResource> appResources) { this.appResources = appResources; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppInfo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
