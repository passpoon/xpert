package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAlarmAppMapPK implements Serializable {
    private Integer mgAlarmId;
    private Integer appResourceId;
    private Integer monGroupId;

    @Column(name = "mg_alarm_id", nullable = false)
    @Id
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Column(name = "app_resource_id", nullable = false)
    @Id
    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    @Column(name = "mon_group_id", nullable = false)
    @Id
    public Integer getMonGroupId() {
        return monGroupId;
    }


    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmAppMapPK{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgAlarmAppMapPK that = (MgAlarmAppMapPK) o;

        if (mgAlarmId != null ? !mgAlarmId.equals(that.mgAlarmId) : that.mgAlarmId != null) return false;
        if (appResourceId != null ? !appResourceId.equals(that.appResourceId) : that.appResourceId != null)
            return false;
        return monGroupId != null ? monGroupId.equals(that.monGroupId) : that.monGroupId == null;
    }

    @Override
    public int hashCode() {
        int result = mgAlarmId != null ? mgAlarmId.hashCode() : 0;
        result = 31 * result + (appResourceId != null ? appResourceId.hashCode() : 0);
        result = 31 * result + (monGroupId != null ? monGroupId.hashCode() : 0);
        return result;
    }
}
