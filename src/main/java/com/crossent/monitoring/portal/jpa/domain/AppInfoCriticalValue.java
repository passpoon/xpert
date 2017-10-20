package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_info_critical_value", schema = "mondb", catalog = "")
@IdClass(AppInfoCriticalValuePK.class)
public class AppInfoCriticalValue implements Serializable {
    private Integer appInfoId;
    private Integer measurementId;
    private Integer metricId;
    private Double critical;
    private Double warnning;

    @Id
    @Column(name = "app_info_id", nullable = false)
    public Integer getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
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
        final StringBuilder sb = new StringBuilder("AppInfoCriticalValue{");
        sb.append("appInfoId=").append(appInfoId);
        sb.append(", measurementId=").append(measurementId);
        sb.append(", metricId=").append(metricId);
        sb.append(", critical=").append(critical);
        sb.append(", warnning=").append(warnning);
        sb.append('}');
        return sb.toString();
    }
}
