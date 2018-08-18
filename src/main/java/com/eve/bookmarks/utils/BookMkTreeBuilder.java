package com.eve.bookmarks.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.BookMark;
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
    public static  JSONObject initDeepPath(int deep, String[] paths, BookMark bookmark) {
        JSONObject parentObj = new JSONObject();
        JSONObject rootNode = parentObj;
        for (int i = deep; i < paths.length; i++) {
            if (i == paths.length - 1) {
                JSONArray arr = (JSONArray) parentObj.get("children");
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


    public static void travelAndAdd(BookMark bookmark, int deep, String[] paths, JSONObject parentObj) {
        if (deep > paths.length - 1) {
            return;
        }

        if (parentObj.containsKey("children")) {
            JSONArray arr = (JSONArray) parentObj.get("children");
            boolean isHavePath = false;
            for (Object anArr : arr) {
                JSONObject child = (JSONObject) anArr;
                if (paths[deep].equals(child.get("title")) || paths[deep].equals(child.get("url"))) {
                    isHavePath = true;
                    travelAndAdd(bookmark, deep + 1, paths, parentObj);
                    break;
                }
            }
            if (!isHavePath) {
                arr.add(BookMkTreeBuilder.initDeepPath(deep, paths, bookmark));
            }
        } else {
            JSONArray arr = new JSONArray();
            arr.add(BookMkTreeBuilder.initDeepPath(deep, paths, bookmark));
            parentObj.put("children", arr);
        }

    }

    /**
     * 遍历树，并将节点存储到map中去
     *
     * @param jsonObject 节点树
     */
    public static void travelAndTransform(JSONObject jsonObject, int deep, Map<String, BookMark> map, String path) {
        if (jsonObject.containsKey("children")) {
            JSONArray arr = (JSONArray) jsonObject.get("children");
            for (Object anArr : arr) {
                JSONObject child = (JSONObject) anArr;
                String nextPath = path + "_" + (child.get("url") == null ? child.get("title").toString() : child.get("url").toString());
                travelAndTransform(child, deep + 1, map, nextPath);
            }
        }
        BookMark book = jsonObject.toJavaObject(BookMark.class);
        map.put(path, book);
    }

    /**
     * 遍历树，并将指定节点删除
     *
     * @param parentObj 父级节点
     */
    public static void travelAndDel(int deep, JSONObject parentObj, String targetKey, String parentPath) {
        if (parentObj.containsKey("children")) {
            JSONArray arr = (JSONArray) parentObj.get("children");
            for (int i = 0; i < arr.size(); i++) {
                JSONObject child = (JSONObject) arr.get(i);
                String nextPath = parentPath + "_" + (child.get("url") == null ? child.get("title").toString() : child.get("url").toString());
                if (nextPath.equals(targetKey)) {
                    arr.remove(child);
                    return;
                }
                travelAndDel(deep + 1, child, targetKey, nextPath);
            }
        }
    }

}
