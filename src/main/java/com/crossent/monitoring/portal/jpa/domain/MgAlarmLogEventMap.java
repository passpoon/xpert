package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_alarm_log_event_map", schema = "mondb", catalog = "")
@IdClass(MgAlarmLogEventMapPK.class)
public class MgAlarmLogEventMap implements Serializable {
    private Integer mgLogEventId;
    private Integer mgAlarmId;

    @Id
    @Column(name = "mg_log_event_id", nullable = false)
    public Integer getMgLogEventId() {
        return mgLogEventId;
    }

    public void setMgLogEventId(Integer mgLogEventId) {
        this.mgLogEventId = mgLogEventId;
    }

    @Id
    @Column(name = "mg_alarm_id", nullable = false)
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmLogEventMap{");
        sb.append("mgLogEventId=").append(mgLogEventId);
        sb.append(", mgAlarmId=").append(mgAlarmId);
        sb.append('}');
        return sb.toString();
    }
}
