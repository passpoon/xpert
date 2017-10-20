package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAppGroupAppPK implements Serializable {
    private Integer appGroupId;
    private Integer monGroupId;
    private Integer appResourceId;

    @Column(name = "app_group_id", nullable = false)
    @Id
    public Integer getAppGroupId() {
        return appGroupId;
    }

    public void setAppGroupId(Integer appGroupId) {
        this.appGroupId = appGroupId;
    }

    @Column(name = "mon_group_id", nullable = false)
    @Id
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Column(name = "app_resource_id", nullable = false)
    @Id
    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppGroupAppPK{");
        sb.append("appGroupId=").append(appGroupId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append('}');
        return sb.toString();
    }
}
