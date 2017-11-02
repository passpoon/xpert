package com.crossent.monitoring.portal.system.mng.dto;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class ServerTypeDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private List<MeasurementDto> measurements;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MeasurementDto> getMeasurements() { return measurements; }

    public void setMeasurements(List<MeasurementDto> measurements) { this.measurements = measurements; }

}
