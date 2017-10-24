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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgAppGroupAppPK that = (MgAppGroupAppPK) o;

        if (appGroupId != null ? !appGroupId.equals(that.appGroupId) : that.appGroupId != null) return false;
        if (monGroupId != null ? !monGroupId.equals(that.monGroupId) : that.monGroupId != null) return false;
        return appResourceId != null ? appResourceId.equals(that.appResourceId) : that.appResourceId == null;
    }

    @Override
    public int hashCode() {
        int result = appGroupId != null ? appGroupId.hashCode() : 0;
        result = 31 * result + (monGroupId != null ? monGroupId.hashCode() : 0);
        result = 31 * result + (appResourceId != null ? appResourceId.hashCode() : 0);
        return result;
    }
}
