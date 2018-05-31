package com.eve.bookmarks.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * 见名知意
 */
public class JSONUtils {
    private static ObjectMapper objectMapperper = new ObjectMapper();
    public static String objectToJsonStr(Object o){
        try {
            return objectMapperper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Map StringToJsonObj(Object o){
        try {
            return objectMapperper.readValue(o.toString(),Map.class);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
