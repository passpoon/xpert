package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AppInfoCriticalValuePK implements Serializable {
    private Integer appInfoId;
    private Integer measurementId;
    private Integer metricId;

    @Column(name = "app_info_id", nullable = false)
    @Id
    public Integer getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
    }

    @Column(name = "measurement_id", nullable = false)
    @Id
    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
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
        final StringBuilder sb = new StringBuilder("AppInfoCriticalValuePK{");
        sb.append("appInfoId=").append(appInfoId);
        sb.append(", measurementId=").append(measurementId);
        sb.append(", metricId=").append(metricId);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppInfoCriticalValuePK that = (AppInfoCriticalValuePK) o;

        if (appInfoId != null ? !appInfoId.equals(that.appInfoId) : that.appInfoId != null) return false;
        if (measurementId != null ? !measurementId.equals(that.measurementId) : that.measurementId != null)
            return false;
        return metricId != null ? metricId.equals(that.metricId) : that.metricId == null;
    }

    @Override
    public int hashCode() {
        int result = appInfoId != null ? appInfoId.hashCode() : 0;
        result = 31 * result + (measurementId != null ? measurementId.hashCode() : 0);
        result = 31 * result + (metricId != null ? metricId.hashCode() : 0);
        return result;
    }
}
