package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "server_type_critical_value", schema = "mondb", catalog = "")
@IdClass(ServerTypeCriticalValuePK.class)
public class ServerTypeCriticalValue implements Serializable {
    private Integer serverTypeId;
    private Integer measurementId;
    private Integer metricId;
    private Double critical;
    private Double warnning;

    @Id
    @Column(name = "server_type_id", nullable = false)
    public Integer getServerTypeId() {
        return serverTypeId;
    }

    public void setServerTypeId(Integer serverTypeId) {
        this.serverTypeId = serverTypeId;
    }

    @Id
    @Column(name = "measurement_id", nullable = false)
    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
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
        final StringBuilder sb = new StringBuilder("ServerTypeCriticalValue{");
        sb.append("serverTypeId=").append(serverTypeId);
        sb.append(", measurementId=").append(measurementId);
        sb.append(", metricId=").append(metricId);
        sb.append(", critical=").append(critical);
        sb.append(", warnning=").append(warnning);
        sb.append('}');
        return sb.toString();
    }
}
