package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgServerTitleMapPK implements Serializable {
    private Integer monGroupId;
    private Integer measurementId;


    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() { return monGroupId; }

    public void setMonGroupId(Integer monGroupId) { this.monGroupId = monGroupId; }

    @Id
    @Column(name = "measurement_id", nullable = false)
    public Integer getMeasurementId() { return measurementId; }

    public void setMeasurementId(Integer measurementId) { this.measurementId = measurementId; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetaManualMapPK{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", measurementId=").append(measurementId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgServerTitleMapPK that = (MgServerTitleMapPK) o;

        if (monGroupId != null ? !monGroupId.equals(that.monGroupId) : that.monGroupId != null) return false;
        return measurementId != null ? measurementId.equals(that.measurementId) : that.measurementId == null;
    }

    @Override
    public int hashCode() {
        int result = monGroupId != null ? monGroupId.hashCode() : 0;
        result = 31 * result + (measurementId != null ? measurementId.hashCode() : 0);
        return result;
    }
}
