package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AlarmProcessPK implements Serializable {
    private Long id;
    private String currentStateCode;

    @Column(name = "id", nullable = false)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "current_state_code", nullable = false, length = 10)
    @Id
    public String getCurrentStateCode() {
        return currentStateCode;
    }

    public void setCurrentStateCode(String currentStateCode) {
        this.currentStateCode = currentStateCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmProcessPK{");
        sb.append("id=").append(id);
        sb.append(", currentStateCode='").append(currentStateCode).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmProcessPK that = (AlarmProcessPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return currentStateCode != null ? currentStateCode.equals(that.currentStateCode) : that.currentStateCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currentStateCode != null ? currentStateCode.hashCode() : 0);
        return result;
    }
}
