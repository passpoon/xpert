package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_alarm_app_group_map", schema = "mondb", catalog = "")
@IdClass(MgAlarmAppGroupMapPK.class)
public class MgAlarmAppGroupMap implements Serializable {
    private Integer mgAlarmId;
    private Integer mgAppGroupId;

    @Id
    @Column(name = "mg_alarm_id", nullable = false)
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Id
    @Column(name = "mg_app_group_id", nullable = false)
    public Integer getMgAppGroupId() {
        return mgAppGroupId;
    }

    public void setMgAppGroupId(Integer mgAppGroupId) {
        this.mgAppGroupId = mgAppGroupId;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmAppGroupMap{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", mgAppGroupId=").append(mgAppGroupId);
        sb.append('}');
        return sb.toString();
    }
}
