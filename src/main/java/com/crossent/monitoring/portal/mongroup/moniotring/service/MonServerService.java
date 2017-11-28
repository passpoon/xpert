package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.common.constants.StatusEnum;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.MeasurementRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerTitleMapRepository;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerDto;
import org.influxdb.dto.Point;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MonServerService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MonServerService.class);

    @Value("${monitoring.server.measurements}")
    public String[] measurement;

    @Value("$(funcTypeAverageHigh)")
    public String funcTypeAverageHigh;

    @Value("$(funcTypeAverageLow)")
    public String funcTypeAverageLow;

    @Value("$(funcTypeIoUsage)")
    public String funcTypeIoUsage;

    @Value("$(funcTypeVolumeHigh)")
    public String funcTypeVolumeHigh;

    @Value("$(funcTypeVolumeLow)")
    public String funcTypeVolumeLow;

    @Value("$(filter.cpu)")
    public String filter_cpu;

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgServerTitleMapRepository mgServerTitleMapRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public Collection<MgServer> getServerStatuses(Integer monitoringGroupId) {

        List<MgServer> mgServers = mgServerRepository.findByMonGroupId(monitoringGroupId);

        for(MgServer mgServer : mgServers) {

            logger.debug("mgServer {} ::", mgServers);
            Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);
            for (MgServerTitleMap map : mgServerTitleMaps) {
                Integer mId = map.getMeasurementId();
                String mName = map.getMeasurements().getName();
                Collection<MgServerCriticalValue> criticalValues = mgServerCriticalValueRepository.findAllByMonGroupIdAndServerResourceIdAndMetric_MeasurementId(monitoringGroupId, mgServer.getServerResourceId(), mId);

                StatusEnum status;

                StringBuffer query = new StringBuffer("select ");

                for(MgServerCriticalValue criticalValue : criticalValues) {
                    Metric metric = criticalValue.getMetric();
                    String funcTypeCode = metric.getFuncTypeCode();
                    logger.debug("metric FunTypeCode ::{} ", metric.getFuncTypeCode());
                    switch (funcTypeCode) {
                        case("0000500001"):
                        case("0000500002"):
                            query.append("MEAN("+metric.getName()+") as "+metric.getName()+"");
                            break;
                        case("0000500003"):
                            query.append("LAST("+metric.getName()+") - FIRST("+metric.getName()+") as "+metric.getName()+"");
                            break;
                        case("0000500004"):
                        case("0000500005"):
                            query.append("LAST("+metric.getName()+") as "+metric.getName()+"");
                            break;

                        default:
                            throw new BusinessException("정의되지 않은 코드");
                    }
                }
                query.append("from " + mName + " ");
                query.append("where time > now() - 60s");

            }
        }

        return mgServers;
    }

    /*public Collection<Measurement> getServerStatuses(Integer monitoringGroupId) {

        MgServerTitleMap byMonGroupId = mgServerTitleMapRepository.findByMonGroupId(monitoringGroupId);

        Collection<Measurement> byId = measurementRepository.findById(byMonGroupId.getMeasurementId());
        logger.debug("byId : : {}", byId);
        return byId;
    }*/

}
