package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "app_alive", schema = "mondb")
public class AppAlive {
    private Integer appResourceId;
    private String checkDttm;
    private String aliveYn;

    @JsonIgnore
    private AppResource appResource;

    @Id
    @Column(name = "app_resource_id", nullable = false)
    public Integer getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(Integer appResourceId) {
        this.appResourceId = appResourceId;
    }

    @Basic
    @Column(name = "check_dttm", nullable = true, length = 14)
    public String getCheckDttm() {
        return checkDttm;
    }

    public void setCheckDttm(String checkDttm) {
        this.checkDttm = checkDttm;
    }

    @Basic
    @Column(name = "alive_yn", nullable = true, length = 1)
    public String getAliveYn() {
        return aliveYn;
    }

    public void setAliveYn(String aliveYn) {
        this.aliveYn = aliveYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppAlive appAlive = (AppAlive) o;

        if (appResourceId != null ? !appResourceId.equals(appAlive.appResourceId) : appAlive.appResourceId != null)
            return false;
        if (checkDttm != null ? !checkDttm.equals(appAlive.checkDttm) : appAlive.checkDttm != null) return false;
        if (aliveYn != null ? !aliveYn.equals(appAlive.aliveYn) : appAlive.aliveYn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appResourceId != null ? appResourceId.hashCode() : 0;
        result = 31 * result + (checkDttm != null ? checkDttm.hashCode() : 0);
        result = 31 * result + (aliveYn != null ? aliveYn.hashCode() : 0);
        return result;
    }


    @OneToOne
    @JoinColumn(name = "app_resource_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public AppResource getAppResource() {
        return appResource;
    }

    public void setAppResource(AppResource appResource) {
        this.appResource = appResource;
    }
}
