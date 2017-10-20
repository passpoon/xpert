package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "alarm_action", schema = "mondb", catalog = "")
@IdClass(AlarmActionPK.class)
public class AlarmAction implements Serializable {
    private Long alarmProcessId;
    private Integer actionSeq;
    private String contents;
    private String userId;
    private String registDttm;
    private String updateDttm;
    private String useYn;
    private StateCode stateCode;

    @Id
    @Column(name = "alarm_process_id", nullable = false)
    public Long getAlarmProcessId() {
        return alarmProcessId;
    }

    public void setAlarmProcessId(Long alarmProcessId) {
        this.alarmProcessId = alarmProcessId;
    }

    @Id
    @Column(name = "action_seq", nullable = false)
    public Integer getActionSeq() {
        return actionSeq;
    }

    public void setActionSeq(Integer actionSeq) {
        this.actionSeq = actionSeq;
    }

    @Basic
    @Column(name = "contents", nullable = true, length = 2000)
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Basic
    @Column(name = "user-id", nullable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "regist_dttm", nullable = true, length = 14)
    public String getRegistDttm() {
        return registDttm;
    }

    public void setRegistDttm(String registDttm) {
        this.registDttm = registDttm;
    }

    @Basic
    @Column(name = "update_dttm", nullable = true, length = 14)
    public String getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(String updateDttm) {
        this.updateDttm = updateDttm;
    }

    @Basic
    @Column(name = "use_yn", nullable = true, length = 1)
    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmAction that = (AlarmAction) o;

        if (alarmProcessId != null ? !alarmProcessId.equals(that.alarmProcessId) : that.alarmProcessId != null)
            return false;
        if (actionSeq != null ? !actionSeq.equals(that.actionSeq) : that.actionSeq != null) return false;
        if (contents != null ? !contents.equals(that.contents) : that.contents != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (registDttm != null ? !registDttm.equals(that.registDttm) : that.registDttm != null) return false;
        if (updateDttm != null ? !updateDttm.equals(that.updateDttm) : that.updateDttm != null) return false;
        if (useYn != null ? !useYn.equals(that.useYn) : that.useYn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = alarmProcessId != null ? alarmProcessId.hashCode() : 0;
        result = 31 * result + (actionSeq != null ? actionSeq.hashCode() : 0);
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (registDttm != null ? registDttm.hashCode() : 0);
        result = 31 * result + (updateDttm != null ? updateDttm.hashCode() : 0);
        result = 31 * result + (useYn != null ? useYn.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "state_code", referencedColumnName = "code", nullable = false)
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmAction{");
        sb.append("alarmProcessId=").append(alarmProcessId);
        sb.append(", actionSeq=").append(actionSeq);
        sb.append(", contents='").append(contents).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", registDttm='").append(registDttm).append('\'');
        sb.append(", updateDttm='").append(updateDttm).append('\'');
        sb.append(", useYn='").append(useYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
