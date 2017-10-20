package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAppPK implements Serializable{
    private Integer monGroupId;
    private Integer appResourceId;

    @Id
    @Column(name = "mon_group_id", nullable = false, insertable = false, updatable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "app_resource_id", nullable = false, insertable = false, updatable = false)
    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppPK{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append('}');
        return sb.toString();
    }
}
