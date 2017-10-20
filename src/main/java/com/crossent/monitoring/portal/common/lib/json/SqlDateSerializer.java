package com.crossent.monitoring.portal.common.lib.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;

@JacksonStdImpl
public class SqlDateSerializer extends StdScalarSerializer<Date> {
    //public static String SQL_DATE_FORMAT="yyyyMMdd";
    
    
    public SqlDateSerializer() {
        super(Date.class);
    }

    public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException, JsonGenerationException {
        SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");
        paramJsonGenerator.writeString(FORMAT.format(paramDate));
    }

    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
        return createSchemaNode("string", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType) throws JsonMappingException {
        JsonStringFormatVisitor localJsonStringFormatVisitor = paramJsonFormatVisitorWrapper == null ? null : paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
        if (localJsonStringFormatVisitor != null) {
            localJsonStringFormatVisitor.format(JsonValueFormat.DATE_TIME);
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        System.out.println(f.format(new Date(System.currentTimeMillis())));

    }
}
