package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "alarm_process", schema = "mondb", catalog = "")
@IdClass(AlarmProcessPK.class)
public class AlarmProcess implements Serializable {
    private Long id;
    private Long eventHistoryId;
    private Integer monGroupId;
    private String compleatYn;
    private String useYn;
    private String registDttm;
    private String updateDttm;
    private String currentStateCode;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "event_history_id", nullable = true)
    public Long getEventHistoryId() {
        return eventHistoryId;
    }

    public void setEventHistoryId(Long eventHistoryId) {
        this.eventHistoryId = eventHistoryId;
    }

    @Basic
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Basic
    @Column(name = "compleat_yn", nullable = true, length = 1)
    public String getCompleatYn() {
        return compleatYn;
    }

    public void setCompleatYn(String compleatYn) {
        this.compleatYn = compleatYn;
    }

    @Basic
    @Column(name = "use_yn", nullable = true, length = 1)
    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
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

    @Id
    @Column(name = "current_state_code", nullable = false, length = 10)
    public String getCurrentStateCode() {
        return currentStateCode;
    }

    public void setCurrentStateCode(String currentStateCode) {
        this.currentStateCode = currentStateCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmProcess{");
        sb.append("id=").append(id);
        sb.append(", eventHistoryId=").append(eventHistoryId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", compleatYn='").append(compleatYn).append('\'');
        sb.append(", useYn='").append(useYn).append('\'');
        sb.append(", registDttm='").append(registDttm).append('\'');
        sb.append(", updateDttm='").append(updateDttm).append('\'');
        sb.append(", currentStateCode='").append(currentStateCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
