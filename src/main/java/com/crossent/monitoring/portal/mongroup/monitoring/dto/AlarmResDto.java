package com.crossent.monitoring.portal.mongroup.monitoring.dto;

public class AlarmResDto {

    Long alarmId;
    Long eventHistoryId;
    Integer monGroupId;
    String compleateYn;
    String registDttm;
    String updateDttm;
    String state;
    String content;
    String channel;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Long getEventHistoryId() {
        return eventHistoryId;
    }

    public void setEventHistoryId(Long eventHistoryId) {
        this.eventHistoryId = eventHistoryId;
    }

    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    public String getCompleateYn() {
        return compleateYn;
    }

    public void setCompleateYn(String compleateYn) {
        this.compleateYn = compleateYn;
    }

    public String getRegistDttm() {
        return registDttm;
    }

    public void setRegistDttm(String registDttm) {
        this.registDttm = registDttm;
    }

    public String getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(String updateDttm) {
        this.updateDttm = updateDttm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmResDto{");
        sb.append("alarmId=").append(alarmId);
        sb.append(", eventHistoryId=").append(eventHistoryId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", compleateYn='").append(compleateYn).append('\'');
        sb.append(", registDttm='").append(registDttm).append('\'');
        sb.append(", updateDttm='").append(updateDttm).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", channel='").append(channel).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
