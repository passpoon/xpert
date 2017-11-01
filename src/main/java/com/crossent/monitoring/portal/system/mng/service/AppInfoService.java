package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.AppInfoMeasurementMap;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.repository.AppInfoMeasurementMapRepository;
import com.crossent.monitoring.portal.jpa.repository.AppInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class AppInfoService {

    @Autowired
    AppInfoRepository appInfoRepository;

    @Autowired
    AppInfoMeasurementMapRepository appInfoMeasurementMapRepository;

    public PagingResVo<AppInfo> pagingUserGroup(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if(keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<AppInfo> appInfos = null;
        if(key == null){
            //TODO 전체조회
            appInfos = appInfoRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "name":
                {
                    appInfos = appInfoRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    appInfos = appInfoRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<AppInfo> resPage = new PagingResVo<AppInfo>(appInfos, true);

        return resPage;
    }


    public void insertAppInfo(AppInfo appInfo) {

        AppInfo inAppInfo = new AppInfo();
        inAppInfo.setName(appInfo.getName());
        inAppInfo.setDescription(appInfo.getDescription());

        AppInfo result = appInfoRepository.save(inAppInfo);
    }


    public void deleteAppInfos(Integer[] appInfoIds) {

        appInfoRepository.deleteByIdIn(appInfoIds);
    }

    public AppInfo getAppInfo(Integer appInfoId) {

        AppInfo appInfo = appInfoRepository.findOne(appInfoId);

        AppInfo out = new AppInfo();
        out.setId(appInfo.getId());
        out.setName(appInfo.getName());
        out.setDescription(appInfo.getDescription());

        return out;
    }

    public AppInfo updateAppInfo(Integer appInfoId, AppInfo appInfo){

        AppInfo getData = appInfoRepository.findOne(appInfoId);

        if(getData == null) {
            return null;
        }
        getData.setName(appInfo.getName());
        getData.setDescription(appInfo.getDescription());

        AppInfo updateData = appInfoRepository.save(getData);

        return updateData;
    }

    public void deleteAppInfo(Integer appInfoId) {

        appInfoRepository.delete(appInfoId);
    }

    public Collection<Measurement> getAppInfoMeasurements(Integer appInfoId){

        AppInfo appInfo = appInfoRepository.findById(appInfoId);
        Collection<Measurement> measurements = appInfo.getMeasurements();

        return measurements;
    }

    public void insertAppInfoMeasurement(Integer appInfoId, Measurement measurement){

        AppInfoMeasurementMap appInfoMeasurementMap = new AppInfoMeasurementMap();
        appInfoMeasurementMap.setAppInfoId(appInfoId);
        appInfoMeasurementMap.setMeasurementId(measurement.getId());

        AppInfoMeasurementMap map = appInfoMeasurementMapRepository.save(appInfoMeasurementMap);
    }

    public void deleteAppInfoMeasurements(Integer appInfoId, Integer[] measurementIds) {

        appInfoMeasurementMapRepository.deleteByAppInfoIdAndMeasurementIdIn(appInfoId, measurementIds);
    }

    public void deleteAppInfoMeasurement(Integer appInfoId, Integer measurementId) {

        appInfoMeasurementMapRepository.deleteByAppInfoIdAndMeasurementId(appInfoId, measurementId);
    }

}
