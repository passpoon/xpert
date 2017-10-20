package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_alarm_app_map", schema = "mondb", catalog = "")
@IdClass(MgAlarmAppMapPK.class)
public class MgAlarmAppMap implements Serializable {
    private Integer mgAlarmId;
    private Integer appResourceId;
    private Integer monGroupId;

    @Id
    @Column(name = "mg_alarm_id", nullable = false)
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Id
    @Column(name = "app_resource_id", nullable = false)
    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmAppMap{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", appResourceId=").append(appResourceId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append('}');
        return sb.toString();
    }
}
