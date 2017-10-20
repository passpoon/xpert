package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_info_measurement_map", schema = "mondb", catalog = "")
@IdClass(AppInfoMeasurementMapPK.class)
public class AppInfoMeasurementMap implements Serializable {
    private Integer appInfoId;
    private Integer measurementId;


    @Id
    @Column(name = "app_info_id", nullable = false, insertable = false, updatable = false)
    public Integer getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
    }

    @Id
    @Column(name = "measurement_id", nullable = false, insertable = false, updatable = false)
    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppInfoMeasurementMap{");
        sb.append("appInfoId=").append(appInfoId);
        sb.append(", measurementId=").append(measurementId);
        sb.append('}');
        return sb.toString();
    }
}
