package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Metric implements Serializable {
    private Integer id;
//    private Integer measurementId;
    private String name;
//    private String metricTypeCode;
    private String description;


    private Measurement measurement;
    private TypeCode typeCode;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "measurement_id", nullable = false, insertable = false, updatable = false)
//    public Integer getMeasurementId() {
//        return measurementId;
//    }
//
//    public void setMeasurementId(Integer measurementId) {
//        this.measurementId = measurementId;
//    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Basic
//    @Column(name = "metric_type_code", nullable = false, length = 10, insertable = false, updatable = false)
//    public String getMetricTypeCode() {
//        return metricTypeCode;
//    }
//
//    public void setMetricTypeCode(String metricTypeCode) {
//        this.metricTypeCode = metricTypeCode;
//    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @ManyToOne
    @JoinColumn(name = "measurement_id", referencedColumnName = "id", nullable = false)
    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    @ManyToOne
    @JoinColumn(name = "metric_type_code", referencedColumnName = "code", nullable = false)
    public TypeCode getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(TypeCode typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Metric{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
