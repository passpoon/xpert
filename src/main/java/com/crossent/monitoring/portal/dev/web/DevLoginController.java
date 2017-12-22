package com.crossent.monitoring.portal.dev.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.system.mng.dto.AppInfoDto;
import com.crossent.monitoring.portal.system.mng.service.AppInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Api(description="로그인")
@RestController
//@RequestMapping(value = "/api/monitoring/${monitoring.api.version}")
public class DevLoginController{

    private static Logger logger = LoggerFactory.getLogger(DevLoginController.class);

    @RequestMapping(value = {"/comm-api/api/portal/v1/login", "/api/portal/v1/login"}, method = RequestMethod.POST)
    public String login(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password){
        logger.debug("request loging");
        logger.debug("email : {}", email);
        logger.debug("password : {}", password);
        return "{\"serviceApiCall\":true,\"company_id\":\"monitComp\",\"manager\":true,\"uaaUpdate\":false,\"orgManager\":false,\"user_name\":\"모니터링관리자\",\"isPaaS\":false,\"token\":\"982d89875be14a5e97e52376c0762e85\",\"cellphone_number3\":\"\",\"isDevOps\":false,\"isIaaS\":false,\"cellphone_number2\":\"\",\"cellphone_number1\":\"\",\"password\":\"\",\"user_type\":\"company\",\"company_status_code\":\"done\",\"uaaAdmin\":false,\"user_id\":\"32322a45-529d-4917-8a4b-6078754c5a56\",\"company_name\":\"모니티링기업\",\"company\":{\"companyName\":\"모니티링기업\",\"phoneNumber1\":\"\",\"createdId\":\"\",\"phoneNumber2\":\"\",\"registrationFilePath\":\"\",\"updatedDate\":{\"date\":30,\"hours\":17,\"seconds\":38,\"month\":10,\"nanos\":0,\"timezoneOffset\":-540,\"year\":117,\"minutes\":41,\"time\":1512031298000,\"day\":4},\"managerName\":\"기업관리자\",\"phoneNumber3\":\"\",\"registrationFileName\":\"\",\"managerEmail\":\"monitManager@devkpaasta.com\",\"ciFileName\":\"\",\"returnReason\":\"\",\"ciFile\":null,\"useYn\":\"Y\",\"ok\":false,\"thirdPartyYn\":\"\",\"businessType1\":\"\",\"address\":\"서울\",\"updatedId\":\"admin@admin.com\",\"managerUpdate\":true,\"registrationNumber3\":\"\",\"registrationNumber2\":\"\",\"businessType2\":\"\",\"ciUseYn\":\"N\",\"companyId\":\"monitComp\",\"createdDate\":null,\"managerPhoneNumber1\":\"\",\"registrationNumber1\":\"\",\"registrationFile\":null,\"createdDivision\":\"\",\"managerPhoneNumber3\":\"\",\"managerPhoneNumber2\":\"\",\"orgLimit\":10,\"representativeName\":\"\",\"attachedFile\":null,\"ciFilePath\":\"\",\"statusCode\":\"done\"},\"companyAdmin\":false,\"email\":\"monitManager@devkpaasta.com\"}";
    }



    @RequestMapping(value = {"/comm-api/api/portal/v1/token/check", "/api/portal/v1/token/check"}, method = RequestMethod.POST)
    public String tocken(@RequestParam(value = "access_token", required = false) String access_token){
        logger.debug("request toclem");
        logger.debug("access_token : {}", access_token);
        return "{\"tokenInfo\":{\"user_id\":\"32322a45-529d-4917-8a4b-6078754c5a56\",\"user_name\":\"모니터링관리자\",\"email\":\"monitManager@devkpaasta.com\",\"client_id\":\"uaaXpert\",\"exp\":1513949759,\"scope\":[\"scim.userids\",\"openid\",\"company.manager\",\"password.write\"],\"jti\":\"982d89875be14a5e97e52376c0762e85\",\"aud\":[\"scim\",\"uaaXpert\",\"password\",\"openid\",\"company\"],\"sub\":\"32322a45-529d-4917-8a4b-6078754c5a56\",\"iss\":\"http://localhost:8080/uaa/oauth/token\",\"iat\":1513906559,\"cid\":\"uaaXpert\",\"grant_type\":\"password\",\"azp\":\"uaaXpert\",\"auth_time\":1513906559,\"zid\":\"uaa\",\"rev_sig\":\"3783bdb8\",\"origin\":\"uaa\",\"revocable\":true,\"isIaaS\":false,\"isPaaS\":false,\"isDevOps\":false,\"isOrgManager\":false},\"resultCode\":\"0\",\"resultMsg\":\"NORMAL_CODE\"}";
    }


}

