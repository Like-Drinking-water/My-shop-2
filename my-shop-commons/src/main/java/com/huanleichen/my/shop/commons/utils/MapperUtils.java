package com.huanleichen.my.shop.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapperUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 单例模式返回一个 ObjectMapper 的实例
     * @return
     */
    public static ObjectMapper getInstance() {
        return mapper;
    }

    /**
     * 对象转 JSON
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String Obj2json(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * 转换为 JSON 字符串，忽略空值
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String obj2jsonIgnoreNull(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * JSON 转 Java 对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T json2pojo(String json, Class<T> clazz) throws IOException {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper.readValue(json, clazz);
    }

    public static <T> T json2pojoByTree(String json, String treeNode, Class<T> clazz) throws IOException {
        JsonNode node = mapper.readTree(json);
        JsonNode data = node.findPath(treeNode);
        return mapper.readValue(data.toString(), clazz);
    }

    /**
     * 字符串转换为 Map<String, Object>
     *
     * @param jsonString
     * @return
     * @throws Exception
     */
    public static Map<String, Object> json2map(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, Map.class);
    }

    /**
     * JSON 转换为 Map<String, T>
     */
    public static <T> Map<String, T> json2map(String jsonString, Class<T> clazz) throws Exception {
        return mapper.readValue(jsonString, new TypeReference<Map<String, T>>() {});
    }

    /**
     * 深度转换 JSON 成 Map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2mapDeeply(String json) throws Exception {
        return json2MapRecursion(json);
    }


    /**
     * 将 JSON 数组转换为集合
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static <T> List<T> json2list(String json, Class<T> clazz) throws Exception {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        return mapper.readValue(json, javaType);
    }

    /**
     * 将 Map 转换为 JavaBean
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }

    /**
     * 将 Map 转换为 JSON
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map map) throws JsonProcessingException {
        return mapper.writeValueAsString(map);
    }

    /**
     * 将 JSON 对象转换为 JavaBean
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return mapper.convertValue(obj, clazz);
    }

    /**
     * 根据给定节点 将 JSON 转换成 List 集合
     * @param json
     * @param treeNode
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> json2listByNode(String json, String treeNode, Class<T> clazz) throws Exception {
        //获取节点
        JsonNode node = mapper.readTree(json);
        //获取 treeNode 节点
        JsonNode data = node.findPath(treeNode);
        return json2list(data.toString(), clazz);
    }

    /**
     * 把 JSON 解析成 List，如果 List 内部的元素存在 jsonString，继续解析
     *
     * @param json
     * @return
     * @throws Exception
     */
    private static List<Object> json2ListRecursion(String json) throws Exception {
        if (json == null) {
            return null;
        }

        List<Object> list = mapper.readValue(json, List.class);

        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = json2ListRecursion(str);
                } else if (obj.toString().startsWith("{")) {
                    obj = json2MapRecursion(str);
                }
            }
        }

        return list;
    }

    /**
     * 把 JSON 解析成 Map，如果 Map 内部的 Value 存在 jsonString，继续解析
     *
     * @param json
     * @return
     * @throws Exception
     */
    private static Map<String, Object> json2MapRecursion(String json) throws Exception {
        if (json == null) {
            return null;
        }

        Map<String, Object> map = mapper.readValue(json, Map.class);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);

                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }

        return map;
    }
}
