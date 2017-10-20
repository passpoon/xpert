package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_alarm_user", schema = "mondb")
@IdClass(MgAlarmUserPK.class)
public class MgAlarmUser implements Serializable {
    private Integer mgAlarmId;
    private String mgUserId;
    private MgAlarm mgAlarm;
    private TypeCode typeCode;

    @Id
    @Column(name = "mg_alarm_id", nullable = false, insertable = false, updatable = false)
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Id
    @Column(name = "mg_user_id", nullable = false, length = 20, insertable = false, updatable = false)
    public String getMgUserId() {
        return mgUserId;
    }

    public void setMgUserId(String mgUserId) {
        this.mgUserId = mgUserId;
    }



    @ManyToOne
    @JoinColumn(name = "mg_alarm_id", referencedColumnName = "id", nullable = false)
    public MgAlarm getMgAlarm() {
        return mgAlarm;
    }

    public void setMgAlarm(MgAlarm mgAlarm) {
        this.mgAlarm = mgAlarm;
    }

    @ManyToOne
    @JoinColumn(name = "send_type_code", referencedColumnName = "code", nullable = false)
    public TypeCode getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(TypeCode typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmUser{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", mgUserId='").append(mgUserId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
