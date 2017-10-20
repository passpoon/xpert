package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_server_critical_value", schema = "mondb", catalog = "")
@IdClass(MgServerCriticalValuePK.class)
public class MgServerCriticalValue implements Serializable {
    private Integer monGroupId;
    private Integer serverResourceId;
    private Integer metricId;
    private Double critical;
    private Double warring;

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "server_resource_id", nullable = false)
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
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
    @Column(name = "warring", nullable = true, precision = 0)
    public Double getWarring() {
        return warring;
    }

    public void setWarring(Double warring) {
        this.warring = warring;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerCriticalValue{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", metricId=").append(metricId);
        sb.append(", critical=").append(critical);
        sb.append(", warring=").append(warring);
        sb.append('}');
        return sb.toString();
    }
}
