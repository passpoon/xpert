package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ServerTypeCriticalValuePK implements Serializable {
    private Integer serverTypeId;
    private Integer measurementId;
    private Integer metricId;

    @Column(name = "server_type_id", nullable = false)
    @Id
    public Integer getServerTypeId() {
        return serverTypeId;
    }

    public void setServerTypeId(Integer serverTypeId) {
        this.serverTypeId = serverTypeId;
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
        final StringBuilder sb = new StringBuilder("ServerTypeCriticalValuePK{");
        sb.append("serverTypeId=").append(serverTypeId);
        sb.append(", measurementId=").append(measurementId);
        sb.append(", metricId=").append(metricId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerTypeCriticalValuePK that = (ServerTypeCriticalValuePK) o;

        if (serverTypeId != null ? !serverTypeId.equals(that.serverTypeId) : that.serverTypeId != null) return false;
        if (measurementId != null ? !measurementId.equals(that.measurementId) : that.measurementId != null)
            return false;
        return metricId != null ? metricId.equals(that.metricId) : that.metricId == null;
    }

    @Override
    public int hashCode() {
        int result = serverTypeId != null ? serverTypeId.hashCode() : 0;
        result = 31 * result + (measurementId != null ? measurementId.hashCode() : 0);
        result = 31 * result + (metricId != null ? metricId.hashCode() : 0);
        return result;
    }
}
