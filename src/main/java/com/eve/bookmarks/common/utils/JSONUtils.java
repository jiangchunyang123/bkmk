package com.eve.bookmarks.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * 见名知意
 */
public class JSONUtils {
    private static ObjectMapper objectMapperper = new ObjectMapper();

    public static String objectToJsonStr(Object o) throws JsonProcessingException {
        return objectMapperper.writeValueAsString(o);
    }

    public static Map objectToMap(Object o) throws IOException {
        return objectMapperper.readValue(o.toString(), Map.class);
    }

    public static JsonNode objectToNode(Object o) throws IOException {
        return objectMapperper.readTree(o.toString());
    }

    /**
     * jsonNode数组转对象list
     *
     * @param nodes
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> nodeToListModel(JsonNode nodes, Class<T> tClass, List<T> list) throws IOException {
        if (nodes.isArray()) {
            for (JsonNode node : nodes) {
                nodeToListModel(node, tClass, list);
            }
        } else {
            list.add(objectMapperper.readValue(nodes.toString(), tClass));
        }
        return list;
    }
    public static void printJsonStr(String filepath,String tableName) throws IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(filepath));
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stringBuilder=new StringBuilder();
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        bufferedReader.close();
        inputStreamReader.close();
        String s = stringBuilder.toString().replaceAll(" ","").replaceAll("\t","");
        String[] strs = stringBuilder.toString().split("\\{");
        for(String ss:strs){
            String[] s2 = ss.split(",");
            if(s2.length>1){
                System.out.println("alter table "+tableName+" add column "+s2[0]+ " varchar(50) comment '"+s2[1]+"';");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        printJsonStr("D:\\sch.txt","T04_BUILDING");
    }
}
