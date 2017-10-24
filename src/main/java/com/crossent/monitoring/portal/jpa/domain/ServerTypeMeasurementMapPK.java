package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ServerTypeMeasurementMapPK implements Serializable {
    private Integer serverTypeId;
    private Integer measurementId;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerTypeMeasurementMapPK{");
        sb.append("serverTypeId=").append(serverTypeId);
        sb.append(", measurementId=").append(measurementId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerTypeMeasurementMapPK that = (ServerTypeMeasurementMapPK) o;

        if (serverTypeId != null ? !serverTypeId.equals(that.serverTypeId) : that.serverTypeId != null) return false;
        return measurementId != null ? measurementId.equals(that.measurementId) : that.measurementId == null;
    }

    @Override
    public int hashCode() {
        int result = serverTypeId != null ? serverTypeId.hashCode() : 0;
        result = 31 * result + (measurementId != null ? measurementId.hashCode() : 0);
        return result;
    }
}
