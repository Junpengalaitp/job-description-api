package com.alaitp.jobdescriptionapi.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String EXCEPTION_JSON_PARSE = "get the JsonParseException : {}";
    private static final String EXCEPTION_JSON_MAPPING = "get the JsonMappingException : {}";
    private static final String EXCEPTION_IO = "get the IOException : {}";

    {
        OBJECT_MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer<>() {

            @Override
            public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
                                  SerializerProvider paramSerializerProvider) throws IOException {
                paramJsonGenerator.writeString("");
            }
        });
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * Convert POJO to JSON
     */
    public static <T> String toJson(T obj) {
        String json = "";
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("convert POJO to JSON failure");
        }
        return json;
    }

    /**
     * Convert JSON to POJO
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException("convert JSON to POJO failure", e);
        }
        return pojo;
    }

    /**
     * convert json-like object to POJO
     */
    public static <T> T fromObject(Object jsonObject, Class<T> type) {
        Map<String, Object> jsonMap = (Map<String, Object>) jsonObject;
        JSONObject json = new JSONObject(jsonMap);
        return fromJson(json.toString(), type);
    }

    /**
     * Convert JSON to HashMap
     */
    public static Map<String, Object> fromJson(String json) {
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
        };
        Map<String, Object> map = new HashMap<>();
        try {
            map = OBJECT_MAPPER.readValue(json, typeRef);
        } catch (JsonParseException e) {
            LOGGER.error("parse JSON failure");
        } catch (JsonMappingException e) {
            LOGGER.error("mapping JSON failure");
        } catch (IOException e) {
            LOGGER.error("convert JSON failure");
        }
        return map;
    }

    public static <T> List<T> convertJsonToList(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = new ArrayList<>();
        if ("2".equals(JsonUtil.checkJsonType(json))) {
            list = JsonUtil.jsonArrayToList(json, clazz);
        } else {
            T obj = mapper.readValue(json, clazz);
            list.add(obj);
        }
        return list;
    }

    public static String checkJsonType(String json) {
        Object jsonObject;
        try {
            jsonObject = new JSONTokener(json).nextValue();
            String type = "1";
            if (jsonObject instanceof JSONArray) {
                type = "2";
            }
            return type;
        } catch (JSONException e) {
            LOGGER.error("", e);
            return "";
        }

    }

    public static <T> List<T> jsonArrayToList(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        List<T> list = new ArrayList<>();
        try {
            list = mapper.readValue(json, javaType);
            return list;
        } catch (JsonParseException e) {
            LOGGER.error(EXCEPTION_JSON_PARSE, e);
        } catch (JsonMappingException e) {
            LOGGER.error(EXCEPTION_JSON_MAPPING, e);
        } catch (IOException e) {
            LOGGER.error(EXCEPTION_IO, e);
        }
        return list;
    }

}

