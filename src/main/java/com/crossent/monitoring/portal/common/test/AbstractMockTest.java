package com.crossent.monitoring.portal.common.test;


import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
abstract public class AbstractMockTest {
    protected static Logger logger = LoggerFactory.getLogger(AbstractMockTest.class);

    @Value("/api/monitoring/${monitoring.api.version}")
    protected String baseUrl;
//    String url = "/api/monitoring/v1.0/test";


    protected String ip;

    protected String port;



    @Autowired
    protected MockMvc mvc;


    public String get(String url) throws Exception {
        return get(url, new LinkedMultiValueMap<String, String>());
    }

    public String get(String url, MultiValueMap<String, String> params) throws Exception {
        logger.info("request method : GET");
        logger.info("request url : {}", baseUrl + url);
        logger.info("request params : {}", params);

        ResultActions resultActions = null;

        resultActions = mvc.perform(MockMvcRequestBuilders.get(baseUrl + url).accept(MediaType.APPLICATION_JSON).params(params));

        resultActions.andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();

        String responsString =result.getResponse().getContentAsString();

        logger.info("response string : {}", responsString);
        return responsString;
    }


    public String post(String url, String bodyJson) throws Exception {
        logger.info("request method : POST");
        logger.info("request url : {}", baseUrl + url);
        logger.info("request bodyJson : {}", bodyJson);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(baseUrl + url).contentType(MediaType.APPLICATION_JSON).content(bodyJson));
        resultActions.andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String responsString =result.getResponse().getContentAsString();
        logger.info("response string : {}", responsString);
        return responsString;
    }


    public String post(String url, Object body) throws Exception {
        String bodyJson = JsonUtil.ObjectToJson(body);
        return post(url, bodyJson);
    }



    public String delete(String url) throws Exception {
        return delete(url, new LinkedMultiValueMap<String, String>());
    }

    public String delete(String url, MultiValueMap<String, String> params) throws Exception {
        logger.info("request method : DELETE");
        logger.info("request url : {}", baseUrl + url);
        logger.info("request params : {}", params);

        ResultActions resultActions = null;

        resultActions = mvc.perform(MockMvcRequestBuilders.delete(baseUrl + url).accept(MediaType.APPLICATION_JSON).params(params));

        resultActions.andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();

        String responsString =result.getResponse().getContentAsString();

        logger.info("response string : {}", responsString);
        return responsString;
    }



    public String put(String url, String bodyJson) throws Exception {
        logger.info("request method : PUT");
        logger.info("request url : {}", baseUrl + url);
        logger.info("request bodyJson : {}", bodyJson);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.put(baseUrl + url).contentType(MediaType.APPLICATION_JSON).content(bodyJson));
        resultActions.andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String responsString =result.getResponse().getContentAsString();
        logger.info("response string : {}", responsString);
        return responsString;
    }


    public String put(String url, Object body) throws Exception {
        String bodyJson = JsonUtil.ObjectToJson(body);
        return put(url, bodyJson);
    }





}
