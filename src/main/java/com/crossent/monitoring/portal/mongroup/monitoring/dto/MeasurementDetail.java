package com.crossent.monitoring.portal.mongroup.monitoring.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MeasurementDetail{
    Integer measurementId;
    String measurementName;
    List<String> titles;
    List<Map<String, String>> rows;

    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void addTitle(String title){
        if(titles == null){
            titles = new ArrayList<String>();
        }
        titles.add(title);
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }

    public void addRow(Map<String, String> row){
        if(rows == null){
            rows = new ArrayList<Map<String, String>>();
        }
        rows.add(row);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MeasurementDetail{");
        sb.append("measurementId=").append(measurementId);
        sb.append(", measurementName='").append(measurementName).append('\'');
        sb.append(", titles=").append(titles);
        sb.append(", rows=").append(rows);
        sb.append('}');
        return sb.toString();
    }
}
