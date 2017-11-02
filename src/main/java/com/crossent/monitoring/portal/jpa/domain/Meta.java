package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meta", schema = "mondb")
public class Meta implements Serializable {
    private Integer id;
    private String prog;
    private String logSource;
    private Integer startPoint;
    private Integer endPoint;
    private String pattern;
    private Manual manual;
    private StateCode stateCode;
    private Integer manualId;
    private String stateCodeId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "prog", nullable = false, length = 100)
    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    @Basic
    @Column(name = "log_source", nullable = false, length = 200)
    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }

    @Basic
    @Column(name = "start_point", nullable = false)
    public Integer getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Integer startPoint) {
        this.startPoint = startPoint;
    }

    @Basic
    @Column(name = "end_point", nullable = true)
    public Integer getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Integer endPoint) {
        this.endPoint = endPoint;
    }

    @Basic
    @Column(name = "pattern", nullable = false, length = 1000)
    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    @ManyToOne
    @JoinColumn(name = "manual_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Manual getManual() {
        return manual;
    }

    public void setManual(Manual manual) {
        this.manual = manual;
    }

    @ManyToOne
    @JoinColumn(name = "state_code", referencedColumnName = "code", nullable = false, insertable = false, updatable = false)
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @Column(name = "manual_id", nullable = true)
    public Integer getManualId() { return manualId; }

    public void setManualId(Integer manualId) { this.manualId = manualId; }

    @Column(name = "state_code", nullable = false, length = 10)
    public String getStateCodeId() { return stateCodeId; }

    public void setStateCodeId(String stateCodeId) { this.stateCodeId = stateCodeId; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Meta{");
        sb.append("id=").append(id);
        sb.append(", prog='").append(prog).append('\'');
        sb.append(", logSource='").append(logSource).append('\'');
        sb.append(", startPoint=").append(startPoint);
        sb.append(", endPoint=").append(endPoint);
        sb.append(", pattern='").append(pattern).append('\'');
        sb.append(", manual=").append(manual);
        sb.append('}');
        return sb.toString();
    }
}
