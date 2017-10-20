package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAlarmAppGroupMapPK implements Serializable {
    private Integer mgAlarmId;
    private Integer mgAppGroupId;

    @Column(name = "mg_alarm_id", nullable = false)
    @Id
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Column(name = "mg_app_group_id", nullable = false)
    @Id
    public Integer getMgAppGroupId() {
        return mgAppGroupId;
    }

    public void setMgAppGroupId(Integer mgAppGroupId) {
        this.mgAppGroupId = mgAppGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmAppGroupMapPK{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", mgAppGroupId=").append(mgAppGroupId);
        sb.append('}');
        return sb.toString();
    }
}
