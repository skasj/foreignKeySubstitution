package org.example.foreignKeySubstitution.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JsonUtil {

    /**
     * json mapper(这里的 ObjectMapper 是线程安全的)
     */
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    static {
        // 未匹配的属性不解析
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 使用系统默认时区
        JSON_MAPPER.setTimeZone(TimeZone.getDefault());
    }

    /**
     * private constructor
     */
    private JsonUtil() {
    }

    /**
     * 对象装json字符串
     * <p>
     *
     * @return json string
     */
    public static String toJsonString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return JSON_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json序列化异常", e);
        }
    }

    public static JsonNode createJsonNode() {
        return JSON_MAPPER.createObjectNode();
    }

    public static String toPrettyJsonString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json序列化异常", e);
        }
    }

    /**
     * 将string 转化为 map @param <V> @param <T> @param <T> @param object @return @throws
     */
    public static <T, V> Map<T, V> stringToMap(String json, Class<T> classT, Class<V> classV) {
        try {
            return JSON_MAPPER.readValue(json, TypeFactory.defaultInstance()
                .constructMapType(HashMap.class, classT, classV));
        } catch (Exception e) {
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    /**
     * 反序列化一个json字符串
     * <p>
     *
     * @return instance of T
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return JSON_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.error("Json反序列化异常,json:" + json + ". class:" + clazz, e);
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    public static <T> T parseObj(String json, Class<T> clazz) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSON_MAPPER.readValue(json, clazz);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            JavaType javaType = JSON_MAPPER.getTypeFactory()
                .constructParametricType(List.class, clazz);
            return JSON_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            LOGGER.error("Json反序列化异常,json:" + json + ". class:" + clazz, e);
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    public static <T> List<T> string2List(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            JavaType javaType = JSON_MAPPER.getTypeFactory()
                .constructParametricType(List.class, clazz);
            return JSON_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            LOGGER.error("Json反序列化异常,json:" + json + ". class:" + clazz, e);
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    public static <T> List<T> parseObjList(String json, Class<T> clazz) throws IOException {
        if (StringUtils.isEmpty(json)) {
            throw new NullPointerException("json string is null");
        }

        JavaType javaType = JSON_MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
        return JSON_MAPPER.readValue(json, javaType);

    }

    public static <K, V> List<Map<K, V>> parseObjListMap(String json, Class<K> keyClazz,
        Class<V> valueClazz)
        throws IOException {
        if (StringUtils.isEmpty(json)) {
            throw new NullPointerException("json string is null");
        }

        JavaType javaType = JSON_MAPPER.getTypeFactory().constructParametricType(List.class,
            JSON_MAPPER.getTypeFactory().constructParametricType(Map.class, keyClazz, valueClazz));
        return JSON_MAPPER.readValue(json, javaType);

    }


    public static <T> List<T> parseObjectListIgnoreCase(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            JavaType javaType = JSON_MAPPER.getTypeFactory()
                .constructParametricType(List.class, clazz);
            return JSON_MAPPER.readValue(json.toUpperCase(), javaType);
        } catch (IOException e) {
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    /**
     * 获取 Json 树
     * <p>
     *
     * @return {@link JsonNode}
     */
    public static JsonNode readTree(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return JSON_MAPPER.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException("Json解析异常", e);
        }
    }

    /**
     * 获取字段的值（支持深度搜索）<br/> 多个相同字段的情况下，获取节点顺序的第一个
     * <p>
     *
     * @param jsonNode json tree
     * @param field 字段名
     * @return {@link JsonNode}
     */
    public static String findNodeByField(JsonNode jsonNode, String field) {
        JsonNode node = jsonNode.findValue(field);
        if (node == null) {
            return null;
        }
        return node.toString();
    }

    /**
     * 把JsonNode 转换成对象
     */
    public static <T> T parseObject(JsonNode jsonNode, Class<T> clazz) {
        if (jsonNode == null) {
            return null;
        }
        try {
            return JSON_MAPPER.treeToValue(jsonNode, clazz);
        } catch (IOException e) {
            LOGGER.error("Json反序列化异常,json:" + jsonNode.toString() + ". class:" + clazz, e);
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    /**
     * 把JsonNode 转换成对象
     */
    public static <T> List<T> parseObjectList(List<JsonNode> jsonNodeList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(jsonNodeList)) {
            return null;
        }
        try {
            return jsonNodeList.stream().map(j -> parseObject(j, clazz)).filter(Objects::nonNull)
                .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(
                "Json反序列化异常,json:" + JsonUtil.toJsonString(jsonNodeList) + ". class:" + clazz, e);
            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    /**
     * 把对象转换为 JsonNode
     */
    public static JsonNode toJsonNode(Object object) {
        return JSON_MAPPER.convertValue(object, JsonNode.class);
    }
}
