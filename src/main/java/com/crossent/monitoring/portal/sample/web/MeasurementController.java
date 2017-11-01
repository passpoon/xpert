package com.crossent.monitoring.portal.sample.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.sample.dto.MeasurementDto;
import com.crossent.monitoring.portal.sample.service.MeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
public class MeasurementController extends BaseController {

    Logger logger = LoggerFactory.getLogger(MeasurementController.class);

    @Autowired
    MeasurementService measurementService;

    @Value("${spring.datasource.url}")
    String aa;

    @RequestMapping(value="/sample/measurements", method = RequestMethod.GET)
    public List<MeasurementDto> listMeasurement() {
        logger.info("start listMeasurement");
        List<MeasurementDto> measulementList = measurementService.listMeasurement();

        logger.info("end listMeasurement");
        return measulementList;
    }

    @Transactional
    @RequestMapping(value="/sample/measurements", method = RequestMethod.POST)
    public void insertMeasurement(@RequestBody MeasurementDto dto){

        logger.info("start insertMeasurement");
        logger.debug("req : {}", dto);
         measurementService.insertMeasurement(dto);

        //int a = 1/0;
        logger.info("req insertMeasurement");

    }


    @RequestMapping(value="/sample/measurements/{measurementId}", method = RequestMethod.GET)
    public MeasurementDto selectMeasurement(@PathVariable int measurementId) {
        logger.info("start selectMeasurement");
        MeasurementDto outDto = measurementService.selectMeasurement(measurementId);
        logger.info("end selectMeasurement");
        return outDto;
    }


    @RequestMapping(value="/sample/measurements/paging", method = RequestMethod.GET)
    public PagingResVo  pagingMessurement(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search")SearchReqVo search){
        logger.debug("start pagingMessurement");
        logger.debug("paging : {}", paging);
        logger.debug("search : {}", search);


//        String m1 = messageSource.getMessage("monitoring.portal.common.error.test", new String[]{"하하", "호호"}, null);
        String m1 = MessageUtil.getMessage("monitoring.portal.common.error.test", "하하", "호호");
        String m2 =MessageUtil.getMessage("monitoring.portal.common.error.pagingParameterError");
        logger.debug("m1 : {}", m1);
        logger.debug("m2 : {}", m2);
        logger.debug("aa : {}", aa);
        //



        PagingResVo<Measurement> resPage = measurementService.pagingMeasurement(paging, search);

        logger.debug("resPage : {}", resPage);
        logger.debug("end pagingMessurement");



        return resPage;
    }






}
