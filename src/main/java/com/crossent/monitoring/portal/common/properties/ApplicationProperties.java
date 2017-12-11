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
    public static String elasticsearchDateTimezone;
    public static String elasticsearchDateFormat;
    public static String elasticsearchIndexLog;
    public static String elasticsearchTypeLog;

    public static Integer dashboardListMaxCntServer;
    public static Integer dashboardListMaxCntServerGroup;
    public static Integer dashboardListMaxCntApp;
    public static Integer dashboardListMaxCntAppGroup;
    public static Integer dashboardListMaxCntEvent;
    public static Integer dashboardListMaxCntAlarm;

    //log.default.term
    public static Long logDefaultTerm;


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


    public String getElasticsearchDateTimezone() {
        return elasticsearchDateTimezone;
    }


    @Value("${elasticsearch.date.timezone}")
    public void setElasticsearchDateTimezone(String elasticsearchDateTimezone) {
        this.elasticsearchDateTimezone = elasticsearchDateTimezone;
    }

    public String getElasticsearchDateFormat() {
        return elasticsearchDateFormat;
    }

    @Value("${elasticsearch.date.format}")
    public void setElasticsearchDateFormat(String elasticsearchDateFormat) {
        this.elasticsearchDateFormat = elasticsearchDateFormat;
    }

    public String getElasticsearchIndexLog() {
        return elasticsearchIndexLog;
    }

    @Value("${elasticsearch.index.log}")
    public void setElasticsearchIndexLog(String elasticsearchIndexLog) {
        this.elasticsearchIndexLog = elasticsearchIndexLog;
    }

    public String getElasticsearchTypeLog() {
        return elasticsearchTypeLog;
    }

    @Value("${elasticsearch.type.log}")
    public void setElasticsearchTypeLog(String elasticsearchTypeLog) {
        this.elasticsearchTypeLog = elasticsearchTypeLog;
    }


    public Long getLogDefaultTerm() {
        return logDefaultTerm;
    }

    @Value("${log.default.term}")
    public void setLogDefaultTerm(Long logDefaultTerm) {
        ApplicationProperties.logDefaultTerm = logDefaultTerm;
    }

    public Integer getDashboardListMaxCntServer() {
        return dashboardListMaxCntServer;
    }

    @Value("${dashboard.list.max.cnt.server}")
    public void setDashboardListMaxCntServer(Integer dashboardListMaxCntServer) {
        ApplicationProperties.dashboardListMaxCntServer = dashboardListMaxCntServer;
    }

    public Integer getDashboardListMaxCntServerGroup() {
        return dashboardListMaxCntServerGroup;
    }

    @Value("${dashboard.list.max.cnt.server-group}")
    public void setDashboardListMaxCntServerGroup(Integer dashboardListMaxCntServerGroup) {
        ApplicationProperties.dashboardListMaxCntServerGroup = dashboardListMaxCntServerGroup;
    }

    public Integer getDashboardListMaxCntApp() {
        return dashboardListMaxCntApp;
    }

    @Value("${dashboard.list.max.cnt.app}")
    public void setDashboardListMaxCntApp(Integer dashboardListMaxCntApp) {
        ApplicationProperties.dashboardListMaxCntApp = dashboardListMaxCntApp;
    }

    public Integer getDashboardListMaxCntAppGroup() {
        return dashboardListMaxCntAppGroup;
    }

    @Value("${dashboard.list.max.cnt.app-group}")
    public void setDashboardListMaxCntAppGroup(Integer dashboardListMaxCntAppGroup) {
        ApplicationProperties.dashboardListMaxCntAppGroup = dashboardListMaxCntAppGroup;
    }

    public Integer getDashboardListMaxCntEvent() {
        return dashboardListMaxCntEvent;
    }

    @Value("${dashboard.list.max.cnt.event}")
    public void setDashboardListMaxCntEvent(Integer dashboardListMaxCntEvent) {
        ApplicationProperties.dashboardListMaxCntEvent = dashboardListMaxCntEvent;
    }

    public static Integer getDashboardListMaxCntAlarm() {
        return dashboardListMaxCntAlarm;
    }

    @Value("${dashboard.list.max.cnt.alarm}")
    public static void setDashboardListMaxCntAlarm(Integer dashboardListMaxCntAlarm) {
        ApplicationProperties.dashboardListMaxCntAlarm = dashboardListMaxCntAlarm;
    }


}
