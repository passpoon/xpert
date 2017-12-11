package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_app_group_app", schema = "mondb", catalog = "")
@IdClass(MgAppGroupAppPK.class)
public class MgAppGroupApp implements Serializable {
    private Integer appGroupId;
    private Integer monGroupId;
    private Integer appResourceId;
    private AppResource appResource;

    @Id
    @Column(name = "app_group_id", nullable = false)
    public Integer getAppGroupId() {
        return appGroupId;
    }

    public void setAppGroupId(Integer appGroupId) {
        this.appGroupId = appGroupId;
    }

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
    public Integer getAppResourceId() {
        return appResourceId;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppGroupApp{");
        sb.append("appGroupId=").append(appGroupId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append('}');
        return sb.toString();
    }
}
