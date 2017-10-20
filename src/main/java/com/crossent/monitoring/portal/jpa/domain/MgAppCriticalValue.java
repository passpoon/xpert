package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_app_critical_value", schema = "mondb", catalog = "")
@IdClass(MgAppCriticalValuePK.class)
public class MgAppCriticalValue implements Serializable {
    private Integer monGroupId;
    private Integer appResourceId;
    private Integer metricId;
    private Double critical;
    private Double warnning;

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

    @Id
    @Column(name = "metric_id", nullable = false)
    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    @Basic
    @Column(name = "critical", nullable = true, precision = 0)
    public Double getCritical() {
        return critical;
    }

    public void setCritical(Double critical) {
        this.critical = critical;
    }

    @Basic
    @Column(name = "warnning", nullable = true, precision = 0)
    public Double getWarnning() {
        return warnning;
    }

    public void setWarnning(Double warnning) {
        this.warnning = warnning;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppCriticalValue{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append(", metricId=").append(metricId);
        sb.append(", critical=").append(critical);
        sb.append(", warnning=").append(warnning);
        sb.append('}');
        return sb.toString();
    }
}
