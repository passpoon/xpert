package com.crossent.monitoring.portal.common.lib.util;

import com.crossent.monitoring.portal.common.lib.json.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static <T> T jsonToObject(String json, Class<T> returnType) throws IOException {
        ObjectMapper om = ObjectMapperFactory.newInstance();
        T t = om.readValue(json, returnType);
        return t;
    }

    public static String ObjectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper om = ObjectMapperFactory.newInstance();
        String s = om.writer().withDefaultPrettyPrinter().writeValueAsString(obj);
        return s;
    }
}
