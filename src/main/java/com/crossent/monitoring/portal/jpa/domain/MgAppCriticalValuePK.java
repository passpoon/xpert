package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAppCriticalValuePK implements Serializable {
    private Integer monGroupId;
    private Integer appResourceId;
    private Integer metricId;

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

    @Column(name = "metric_id", nullable = false)
    @Id
    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppCriticalValuePK{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append(", metricId=").append(metricId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgAppCriticalValuePK that = (MgAppCriticalValuePK) o;

        if (monGroupId != null ? !monGroupId.equals(that.monGroupId) : that.monGroupId != null) return false;
        if (appResourceId != null ? !appResourceId.equals(that.appResourceId) : that.appResourceId != null)
            return false;
        return metricId != null ? metricId.equals(that.metricId) : that.metricId == null;
    }

    @Override
    public int hashCode() {
        int result = monGroupId != null ? monGroupId.hashCode() : 0;
        result = 31 * result + (appResourceId != null ? appResourceId.hashCode() : 0);
        result = 31 * result + (metricId != null ? metricId.hashCode() : 0);
        return result;
    }
}
