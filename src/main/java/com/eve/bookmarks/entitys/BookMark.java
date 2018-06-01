package com.eve.bookmarks.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 书签实体类
 */
@Entity
public class BookMark implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long mysqlId;

    @Column(length = 10)
    private String id;
    @Column(length = 10)
    private String parentId;
    @Column(length = 10)
    private String idx;
    @Column(length = 155)
    private String url;
    private String title;
    @Column(length = 20)
    private String dateAdded;
    @Column(length = 20)
    private String dateGroupModified;
    @Transient
    private String remark;

    @Column(length = 10)
    private Long parentMysqlId;

    @Column(length = 20)
    private String mongoId;

    /**
     * 标识位 1正常 0删除 2新增
     */
    @Column(length = 2)
    private int state = 1;
    private Long crateTime;
    private Long updateTime;
    @Column(length = 40)
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Long crateTime) {
        this.crateTime = crateTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getMysqlId() {
        return mysqlId;
    }

    public void setMysqlId(Long mysqlId) {
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
