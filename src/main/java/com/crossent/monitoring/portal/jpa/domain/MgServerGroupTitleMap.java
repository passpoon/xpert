package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_server_group_title_map", schema = "mondb", catalog = "")
@IdClass(MgServerGroupTitleMapPK.class)
public class MgServerGroupTitleMap implements Serializable {
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
        final StringBuilder sb = new StringBuilder("MetaManualMap{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", measurementId=").append(measurementId);
        sb.append('}');
        return sb.toString();
    }
}
