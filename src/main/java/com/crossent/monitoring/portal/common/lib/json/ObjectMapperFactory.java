package com.crossent.monitoring.portal.common.lib.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ObjectMapperFactory {
    private static final Logger logger = LoggerFactory.getLogger(ObjectMapperFactory.class);
    public static ObjectMapper newInstance(){
        ObjectMapper om = new ObjectMapper();
        
        om.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        om.addHandler(new DeserializationProblemHandler() {
            public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException, JsonProcessingException {
                logger.warn("Unknown properties in JSON string are ignored.(" + propertyName + ")");
                return true;
            }
        });
        om.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
        om.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        //om.enable(DeserializationFeature.);
        om.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //deprecated
        //om.enable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        //om.enable();
        
        SimpleModule module = new SimpleModule();
        //module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        //module.addSerializer(java.sql.Date.class, new SqlDateSerializer());
        //module.addSerializer(Object.class, new NullSerializer());       
        //DateDeserializers dateDeserializers = new DateDeserializers();
        //module.addDeserializer(Timestamp.class, new TimestampDeserializer());
        om.registerModule(module);
        
//        List<Module> modules = ObjectMapper.findModules();
//        modules.add(module);
//        
//        om.registerModules(modules);
        
        //om.setDateFormat(new MultipleDateFormat());
      // om.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE); //Json키값 포멧 변경
        
        
        
        
       
        return om;
    }
}
