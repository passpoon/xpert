package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AlarmActionPK implements Serializable {
    private Long alarmProcessId;
    private Integer actionSeq;

    @Column(name = "alarm_process_id", nullable = false)
    @Id
    public Long getAlarmProcessId() {
        return alarmProcessId;
    }

    public void setAlarmProcessId(Long alarmProcessId) {
        this.alarmProcessId = alarmProcessId;
    }

    @Column(name = "action_seq", nullable = false)
    @Id
    public Integer getActionSeq() {
        return actionSeq;
    }

    public void setActionSeq(Integer actionSeq) {
        this.actionSeq = actionSeq;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmActionPK{");
        sb.append("alarmProcessId=").append(alarmProcessId);
        sb.append(", actionSeq=").append(actionSeq);
        sb.append('}');
        return sb.toString();
    }
}
