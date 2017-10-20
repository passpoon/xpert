package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AppInfoMeasurementMapPK implements Serializable {
    private Integer appInfoId;
    private Integer measurementId;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppInfoMeasurementMapPK{");
        sb.append("appInfoId=").append(appInfoId);
        sb.append(", measurementId=").append(measurementId);
        sb.append('}');
        return sb.toString();
    }
}
