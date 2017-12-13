package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.jpa.domain.Meta;
import com.crossent.monitoring.portal.jpa.domain.MetaManualMap;
import com.crossent.monitoring.portal.jpa.domain.StateCode;
import com.crossent.monitoring.portal.jpa.repository.ManualRepository;
import com.crossent.monitoring.portal.jpa.repository.MetaManualMapRepository;
import com.crossent.monitoring.portal.jpa.repository.MetaRepository;
import com.crossent.monitoring.portal.jpa.repository.StateCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class MetaService {

    private static Logger logger = LoggerFactory.getLogger(MetaService.class);

    @Autowired
    MetaRepository metaRepository;

    @Autowired
    MetaManualMapRepository metaManualMapRepository;

    @Autowired
    ManualRepository manualRepository;

    @Autowired
    StateCodeRepository stateCodeRepository;

    public PagingResVo<Meta> pagingMeta(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<Meta> metas = null;
        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);

        if (key == null) {
            //TODO 전체조회
            metas = metaRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "prog": {
                    metas = metaRepository.findByProgLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "logSource": {
                    metas = metaRepository.findByLogSourceLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "pattern": {
                    metas = metaRepository.findByPatternLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "stateCode": {
                    metas = metaRepository.findByStateCodeLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);
            }
        }

        PagingResVo<Meta> resPage = new PagingResVo<Meta>(metas, true);

        return resPage;
    }

    public void insertMeta(Meta meta) {

        Meta in = new Meta();
        in.setProg(meta.getProg());
        in.setLogSource(meta.getLogSource());
        in.setStartPoint(meta.getStartPoint());
        in.setEndPoint(meta.getEndPoint());
        in.setPattern(meta.getPattern());
        in.setStateCodeId(meta.getStateCodeId());

        Meta result = metaRepository.save(in);
    }

    public void deleteMetas(Integer[] metaIds) {

        metaRepository.deleteByIdIn(metaIds);
    }

    public Meta getMeta(Integer metaId) {

        Meta meta = metaRepository.findOne(metaId);
        if(logger.isDebugEnabled()){
            logger.debug("meta : {}", meta);
        }
        if(meta == null) {
            throw new BusinessException("noFindMeta");
        }

        Meta out = new Meta();
        out.setId(meta.getId());
        out.setStateCode(meta.getStateCode());
        out.setPattern(meta.getPattern());
        out.setLogSource(meta.getLogSource());
        out.setProg(meta.getProg());
        out.setStartPoint(meta.getStartPoint());
        out.setEndPoint(meta.getEndPoint());

        return out;
    }

    public Meta updateMeta(Integer metaId, Meta meta) {

        Meta getData = metaRepository.findOne(metaId);
        if(logger.isDebugEnabled()){
            logger.debug("Meta : {}", getData);
        }
        if(getData == null) {
            throw new BusinessException("noFindMeta");
        }
        getData.setProg(meta.getProg());
        getData.setStateCodeId(meta.getStateCodeId());
        getData.setStartPoint(meta.getStartPoint());
        getData.setEndPoint(meta.getEndPoint());
        getData.setLogSource(meta.getLogSource());
        getData.setPattern(meta.getPattern());

        Meta updateData = metaRepository.save(getData);

        return updateData;
    }

    public void deleteMeta(Integer metaId) {

        metaRepository.delete(metaId);
    }

    public Collection<Manual> getMetaManuals(Integer metaId){

        Meta meta = metaRepository.findById(metaId);
        if(logger.isDebugEnabled()){
            logger.debug("meta : {}", meta);
        }
        if(meta == null) {
            throw new BusinessException("noFindMeta");
        }

        Collection<Manual> manuals = meta.getManuals();
        if(logger.isDebugEnabled()){
            logger.debug("manuals : {}", manuals);
        }

        return manuals;
    }

    public void insertMetaManuals(Integer metaId, Integer[] manualIds){

        for(Integer manualId : manualIds) {
            MetaManualMap map = new MetaManualMap();
            map.setMetaId(metaId);
            map.setManualId(manualId);

            MetaManualMap result = metaManualMapRepository.save(map);
        }
    }

    public void deleteMetaManuals(Integer metaId, Integer[] manualIds) {

        metaManualMapRepository.deleteByMetaIdAndManualIdIn(metaId, manualIds);
    }

    public void deleteMetaManual(Integer metaId, Integer manualId) {

        metaManualMapRepository.deleteByMetaIdAndManualId(metaId, manualId);
    }

    public Collection<Manual> getManuals(Integer metaId, String name) {

        Collection<Manual> manuals = manualRepository.findAllByNameLikeAndAndMetas_Id("%"+name+"%", metaId);

        return manuals;
    }

    public Collection<StateCode> getStateCodes() {

        String code = "00002%";

       return  stateCodeRepository.findAllByCodeLike(code);
    }
}