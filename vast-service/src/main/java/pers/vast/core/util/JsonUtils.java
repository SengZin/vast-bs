package pers.vast.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json 序列化工具
 * Created by sengzin on 2018/5/5.
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectMapper prettyMapper = new ObjectMapper();
    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(String data, TypeReference<T> typeRef) {
        try {
            return (T) mapper.readValue(data, typeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonString(Object obj) {
        String result;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <T> T parse(String data, Class<T> clazz) {
        try {
            return mapper.readValue(data, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toPrettyJsonString(Object obj) {
        try {
            return prettyMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将 JsonArray 转换为对应的List
     */
    public static <T> List<T> parseList(String text, Class<T> clazz) {
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return mapper.readValue(text, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转为Map
     */
    public static <K, V> Map<K, V> parseMap(String text, Class<K> keyClass, Class<V> valueClass) {
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
        try {
            return mapper.readValue(text, mapType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
