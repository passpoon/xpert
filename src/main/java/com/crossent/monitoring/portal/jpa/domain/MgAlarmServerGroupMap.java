package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_alarm_server_group_map", schema = "mondb", catalog = "")
@IdClass(MgAlarmServerGroupMapPK.class)
public class MgAlarmServerGroupMap implements Serializable {
    private Integer mgAlarmId;
    private Integer mgServerGroupId;

    @Id
    @Column(name = "mg_alarm_id", nullable = false)
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Id
    @Column(name = "mg_server_group_id", nullable = false)
    public Integer getMgServerGroupId() {
        return mgServerGroupId;
    }

    public void setMgServerGroupId(Integer mgServerGroupId) {
        this.mgServerGroupId = mgServerGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmServerGroupMap{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", mgServerGroupId=").append(mgServerGroupId);
        sb.append('}');
        return sb.toString();
    }
}
