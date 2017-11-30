package com.crossent.monitoring.portal.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class ApplicationProperties {


    public static Map<String,Map<String,String>> influxQueryFilters;


    public static String influxQeryGroupTerm;

    public static String influxMeasurementProcess;




    public Map<String, Map<String, String>> getInfluxQueryFilters() {
        return influxQueryFilters;
    }

    @Value("#{${influx.query.filter}}")
    public void setInfluxQueryFilters(Map<String, Map<String, String>> influxQueryFilters) {
        this.influxQueryFilters = influxQueryFilters;
    }


    public String getInfluxQeryGroupTerm() {
        return influxQeryGroupTerm;
    }

    @Value("${influx.query.group.term}")
    public void setInfluxQeryGroupTerm(String influxQeryGroupTerm) {
        this.influxQeryGroupTerm = influxQeryGroupTerm;
    }


    public String getInfluxMeasurementProcess() {
        return influxMeasurementProcess;
    }

    @Value("${influx.measurement.process}")
    public void setInfluxMeasurementProcess(String influxMeasurementProcess) {
        ApplicationProperties.influxMeasurementProcess = influxMeasurementProcess;
    }


}
