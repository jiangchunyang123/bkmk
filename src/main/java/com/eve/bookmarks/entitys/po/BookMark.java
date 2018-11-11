package com.eve.bookmarks.entitys.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class BookMark implements Serializable {
    private Long mysqlId;

    private Date crateTime;

    private String dateAdded;

    private String dateGroupModified;

    private String id;

    private String idx;

    private String mongoId;

    private String parentId;

    private Long parentMysqlId;

    private String path;

    private Integer state;

    private String title;

    private String uid;

    private Date updateTime;

    private String url;

    private static final long serialVersionUID = 1L;

    public Long getMysqlId() {
        return mysqlId;
    }

    public void setMysqlId(Long mysqlId) {
        this.mysqlId = mysqlId;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
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

    public Long getParentMysqlId() {
        return parentMysqlId;
    }

    public void setParentMysqlId(Long parentMysqlId) {
        this.parentMysqlId = parentMysqlId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BookMark other = (BookMark) that;
        return (this.getMysqlId() == null ? other.getMysqlId() == null : this.getMysqlId().equals(other.getMysqlId()))
            && (this.getCrateTime() == null ? other.getCrateTime() == null : this.getCrateTime().equals(other.getCrateTime()))
            && (this.getDateAdded() == null ? other.getDateAdded() == null : this.getDateAdded().equals(other.getDateAdded()))
            && (this.getDateGroupModified() == null ? other.getDateGroupModified() == null : this.getDateGroupModified().equals(other.getDateGroupModified()))
            && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdx() == null ? other.getIdx() == null : this.getIdx().equals(other.getIdx()))
            && (this.getMongoId() == null ? other.getMongoId() == null : this.getMongoId().equals(other.getMongoId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getParentMysqlId() == null ? other.getParentMysqlId() == null : this.getParentMysqlId().equals(other.getParentMysqlId()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMysqlId() == null) ? 0 : getMysqlId().hashCode());
        result = prime * result + ((getCrateTime() == null) ? 0 : getCrateTime().hashCode());
        result = prime * result + ((getDateAdded() == null) ? 0 : getDateAdded().hashCode());
        result = prime * result + ((getDateGroupModified() == null) ? 0 : getDateGroupModified().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdx() == null) ? 0 : getIdx().hashCode());
        result = prime * result + ((getMongoId() == null) ? 0 : getMongoId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getParentMysqlId() == null) ? 0 : getParentMysqlId().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mysqlId=").append(mysqlId);
        sb.append(", crateTime=").append(crateTime);
        sb.append(", dateAdded=").append(dateAdded);
        sb.append(", dateGroupModified=").append(dateGroupModified);
        sb.append(", id=").append(id);
        sb.append(", idx=").append(idx);
        sb.append(", mongoId=").append(mongoId);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentMysqlId=").append(parentMysqlId);
        sb.append(", path=").append(path);
        sb.append(", state=").append(state);
        sb.append(", title=").append(title);
        sb.append(", uid=").append(uid);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}