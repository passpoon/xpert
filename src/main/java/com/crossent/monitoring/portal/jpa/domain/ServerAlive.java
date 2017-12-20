package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "server_alive", schema = "mondb")
public class ServerAlive {
    private Integer serverResourceId;
    private String checkDttm;
    private String aliveYn;

    @JsonIgnore
    private ServerResource serverResource;

    @Id
    @Column(name = "server_resource_id", nullable = false)
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
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

        ServerAlive that = (ServerAlive) o;

        if (serverResourceId != null ? !serverResourceId.equals(that.serverResourceId) : that.serverResourceId != null)
            return false;
        if (checkDttm != null ? !checkDttm.equals(that.checkDttm) : that.checkDttm != null) return false;
        if (aliveYn != null ? !aliveYn.equals(that.aliveYn) : that.aliveYn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverResourceId != null ? serverResourceId.hashCode() : 0;
        result = 31 * result + (checkDttm != null ? checkDttm.hashCode() : 0);
        result = 31 * result + (aliveYn != null ? aliveYn.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "server_resource_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ServerResource getServerResource() {
        return serverResource;
    }

    public void setServerResource(ServerResource serverResource) {
        this.serverResource = serverResource;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerAlive{");
        sb.append("serverResourceId=").append(serverResourceId);
        sb.append(", checkDttm='").append(checkDttm).append('\'');
        sb.append(", aliveYn='").append(aliveYn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
