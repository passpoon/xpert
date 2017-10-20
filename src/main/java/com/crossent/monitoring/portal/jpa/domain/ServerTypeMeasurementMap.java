package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "server_type_measurement_map", schema = "mondb", catalog = "")
@IdClass(ServerTypeMeasurementMapPK.class)
public class ServerTypeMeasurementMap implements Serializable {
    private Integer serverTypeId;
    private Integer measurementId;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerTypeMeasurementMap{");
        sb.append("serverTypeId=").append(serverTypeId);
        sb.append(", measurementId=").append(measurementId);
        sb.append('}');
        return sb.toString();
    }
}
