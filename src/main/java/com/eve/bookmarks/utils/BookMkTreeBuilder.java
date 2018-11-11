package com.eve.bookmarks.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.po.BookMark;
import org.apache.logging.log4j.util.Strings;

import java.util.Map;

/**
 * 暂定为书签树工具类
 */
public class BookMkTreeBuilder {
    /**
     * 检测到从某层级开始创建路径
     *
     * @param deep     层级
     * @param paths    路径
     * @param bookmark 书签
     * @return 一条树路径节点列表
     */
    public static JSONObject initDeepPath(int deep, String[] paths, BookMark bookmark) {
        JSONObject parentObj = new JSONObject();
        JSONObject rootNode = parentObj;
        for (int i = deep; i < paths.length; i++) {
            if (i == paths.length - 1) {
                JSONArray arr = parentObj.getJSONArray("children");
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("title", bookmark.getTitle());
                if (Strings.isNotBlank(bookmark.getUrl())) {
                    jsonObject1.put("url", bookmark.getUrl());
                } else {
                    jsonObject1.put("children", new JSONArray());
                }
                arr.add(jsonObject1);
            } else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("title", paths[i]);

                jsonObject1.put("children", new JSONArray());
                JSONArray arr = (JSONArray) parentObj.get("children");
                arr.add(jsonObject1);
                parentObj = jsonObject1;
            }
        }
        return rootNode;
    }


    public static void travelAndAdd(BookMark bookmark, int deep, String[] paths, JSONArray parentObj) {
        if (deep > paths.length - 1) {
            return;
        }
        boolean dontHaveBkmk = true;
        for (int i = 0; i < parentObj.size(); i++) {
            JSONObject child = parentObj.getJSONObject(i);
            if (paths[deep].equals(child.get("title")) || paths[deep].equals(child.get("url"))) {
                dontHaveBkmk = false;
                if (child.containsKey("children")) {
                    JSONArray arr = child.getJSONArray("children");
                    travelAndAdd(bookmark, deep + 1, paths, arr);
                } else {
                    JSONArray arr = new JSONArray();
                    arr.add(BookMkTreeBuilder.initDeepPath(deep, paths, bookmark));
                    child.put("children", arr);
                    travelAndAdd(bookmark, deep + 1, paths, arr);
                }
            }
        }
        if (dontHaveBkmk) {
            parentObj.add(BookMkTreeBuilder.initDeepPath(deep, paths, bookmark));
        }
    }

    /**
     * 遍历树，并将节点存储到map中去
     *
     * @param jsonArray 节点树
     */
    public static void travelAndTransform(JSONArray jsonArray, int deep, Map<String, BookMark> map, String path) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject child = jsonArray.getJSONObject(i);
            if (child.containsKey("children")) {
                JSONArray arr = child.getJSONArray("children");
                String nextPath = path + "_" + (child.get("url") == null ?
                        child.get("title").toString() : child.get("url").toString());
                travelAndTransform(arr, deep + 1, map, nextPath);
            }
            BookMark book = jsonArray.toJavaObject(BookMark.class);
            map.put(path, book);
        }
    }

    /**
     * 遍历树，并将指定节点删除
     *
     * @param parentObj 父级节点
     */
    public static void travelAndDel(int deep, JSONArray parentObj, String targetKey, String parentPath) {
        for (int i = 0; i < parentObj.size(); i++) {
            JSONObject jsonObject = parentObj.getJSONObject(i);
            String nextPath = parentPath + "_" + (jsonObject.get("url") == null ?
                    jsonObject.get("title").toString() : jsonObject.get("url").toString());
            if (nextPath.equals(targetKey)) {
                parentObj.remove(i);
                return;
            }
            if (jsonObject.containsKey("children")) {
                travelAndDel(deep + 1, jsonObject.getJSONArray("children"), targetKey, nextPath);
            }
        }
    }

}
