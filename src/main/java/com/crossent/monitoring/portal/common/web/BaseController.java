package com.crossent.monitoring.portal.common.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;

import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.exception.MessageConversionException;
import com.crossent.monitoring.portal.common.lib.json.ObjectMapperFactory;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Api(description="모니터링 API")
@RequestMapping(value = "/api/monitoring/${monitoring.api.version}")
abstract public class BaseController {

    @ModelAttribute("paging")
    public PagingReqVo getPageReqVo(@RequestParam(value = "paging", required = false) String paging) {
        PagingReqVo pagingReqVo = null;

        if (paging != null) {
            try {
                pagingReqVo = ObjectMapperFactory.newInstance().readValue(paging, PagingReqVo.class);
            } catch (IOException e) {
                throw new MessageConversionException(MessageUtil.getMessage("pagingParameterError", paging), e);
            }
        }
        return pagingReqVo;
    }


    @ModelAttribute("search")
    public SearchReqVo getSearchReqVo(@RequestParam(value = "search", required = false) String search) {
        SearchReqVo searchReqVo = null;
        if (search != null) {
            try {
                searchReqVo = ObjectMapperFactory.newInstance().readValue(search, SearchReqVo.class);
            } catch (IOException e) {
                throw new MessageConversionException(MessageUtil.getMessage("searchParameterError", search), e);
            }
        }

        return searchReqVo;
    }

}
