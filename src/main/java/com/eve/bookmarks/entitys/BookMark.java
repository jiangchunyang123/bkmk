package com.eve.bookmarks.entitys;

import java.io.Serializable;

/**
 * 书签实体类
 */
public class BookMark implements Serializable {
    private String id;
    private String parentId;
    private String index;
    private String url;
    private String title;
    private String dateAdded;
    private String dateGroupModified;

    private String mysqlId;
    private String mongoId;

    /**
     * 标识位 1正常 0删除 2新增
     */
    private int state = 1;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMysqlId() {
        return mysqlId;
    }

    public void setMysqlId(String mysqlId) {
        this.mysqlId = mysqlId;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateGroupModified() {
        return dateGroupModified;
    }

    public void setDateGroupModified(String dateGroupModified) {
        this.dateGroupModified = dateGroupModified;
    }
}
