package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "app_resource", schema = "mondb")
public class AppResource implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private AppInfo appInfo;
    private ServerResource serverResource;
    @JsonIgnore
    private Collection<MgApp> mgApps;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppResource that = (AppResource) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "app_info_id", referencedColumnName = "id", nullable = false)
    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    @ManyToOne
    @JoinColumn(name = "server_resource_id", referencedColumnName = "id")
    public ServerResource getServerResource() {
        return serverResource;
    }

    public void setServerResource(ServerResource serverResource) {
        this.serverResource = serverResource;
    }

    @OneToMany(mappedBy = "appResource")
    public Collection<MgApp> getMgApps() {
        return mgApps;
    }

    public void setMgApps(Collection<MgApp> mgApps) {
        this.mgApps = mgApps;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppResource{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
