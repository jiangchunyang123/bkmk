package com.eve.bookmarks.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 见名知意
 */
public class JSONUtils {
    private static ObjectMapper objectMapperper = new ObjectMapper();

    public static String objectToJsonStr(Object o) {
        try {
            return objectMapperper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map objectToMap(Object o) {
        try {

            return objectMapperper.readValue(o.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode objectToNode(Object o) {
        try {
            return objectMapperper.readTree(o.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * jsonNode数组转对象list
     *
     * @param nodes
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> nodeToListModel(JsonNode nodes, Class<T> tClass,List<T> list) {
        try {
            if (nodes.isArray()) {
                for (JsonNode node : nodes) {
                    nodeToListModel(node,tClass,list);
                }
            }else{
                list.add(objectMapperper.readValue(nodes.toString(), tClass));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
