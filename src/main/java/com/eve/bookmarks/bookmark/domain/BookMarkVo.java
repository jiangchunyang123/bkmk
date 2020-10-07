package com.eve.bookmarks.bookmark.domain;

import java.io.Serializable;

/**
 * 书签实体类vo
 */

public class BookMarkVo implements Serializable {


    private String parentId;

    private String idx;

    private String url;

    private String title;

    private String dateAdded;

    private String dateGroupModified;

    private String remark;

    private Long parentMysqlId;

    private String mongoId;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }



    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
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

    public Long getParentMysqlId() {
        return parentMysqlId;
    }

    public void setParentMysqlId(Long parentMysqlId) {
        this.parentMysqlId = parentMysqlId;
    }


}
