package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAlarmLogEventMapPK implements Serializable {
    private Integer mgLogEventId;
    private Integer mgAlarmId;

    @Column(name = "mg_log_event_id", nullable = false)
    @Id
    public Integer getMgLogEventId() {
        return mgLogEventId;
    }

    public void setMgLogEventId(Integer mgLogEventId) {
        this.mgLogEventId = mgLogEventId;
    }

    @Column(name = "mg_alarm_id", nullable = false)
    @Id
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmLogEventMapPK{");
        sb.append("mgLogEventId=").append(mgLogEventId);
        sb.append(", mgAlarmId=").append(mgAlarmId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgAlarmLogEventMapPK that = (MgAlarmLogEventMapPK) o;

        if (mgLogEventId != null ? !mgLogEventId.equals(that.mgLogEventId) : that.mgLogEventId != null) return false;
        return mgAlarmId != null ? mgAlarmId.equals(that.mgAlarmId) : that.mgAlarmId == null;
    }

    @Override
    public int hashCode() {
        int result = mgLogEventId != null ? mgLogEventId.hashCode() : 0;
        result = 31 * result + (mgAlarmId != null ? mgAlarmId.hashCode() : 0);
        return result;
    }
}
