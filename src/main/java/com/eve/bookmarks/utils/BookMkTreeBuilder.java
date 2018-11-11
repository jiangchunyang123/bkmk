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
    public static void initDeepPath(int deep, String[] paths, BookMark bookmark, JSONArray parentObj) {
        if (deep == paths.length - 1) {
            JSONObject jsonObject1 = initLeaf(bookmark);
            parentObj.add(jsonObject1);
        } else {
            JSONObject child = initPath(paths[deep]);
            parentObj.add(child);
            initDeepPath(deep+1,paths,bookmark,child.getJSONArray("children"));
        }
    }


    public static JSONObject initPath(String path) {
        JSONObject child = new JSONObject();
        child.put("title", path);
        JSONArray childrens = new JSONArray();
        child.put("children", childrens);
        return child;
    }

    public static JSONObject initLeaf(BookMark bookmark) {
        JSONObject leaf = new JSONObject();
        leaf.put("title", bookmark.getTitle());
        if (Strings.isNotBlank(bookmark.getUrl())) {
            leaf.put("url", bookmark.getUrl());
        } else {
            leaf.put("children", new JSONArray());
        }
        return leaf;
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
                    initDeepPath(deep, paths, bookmark,arr);
                    child.put("children", arr);
                    travelAndAdd(bookmark, deep + 1, paths, arr);
                }
            }
        }
        if (dontHaveBkmk) {
            initDeepPath(deep, paths, bookmark,parentObj);
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
            BookMark book = child.toJavaObject(BookMark.class);
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
