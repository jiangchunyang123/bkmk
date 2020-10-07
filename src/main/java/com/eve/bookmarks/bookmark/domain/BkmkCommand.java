package com.eve.bookmarks.bookmark.domain;

import com.eve.bookmarks.common.utils.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 
 */

public class BkmkCommand implements Serializable {

    private Long id;

    private Integer command;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long endVersion;

    private String path;

    private Long startVersion;

    private Integer state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long bookMarkMysqlId;

    private static final long serialVersionUID = 1L;

    private BookMark bookMark;

    public BookMark getBookMark() {
        return bookMark;
    }

    public void setBookMark(BookMark bookMark) {
        this.bookMark = bookMark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getEndVersion() {
        return endVersion;
    }

    public void setEndVersion(Long endVersion) {
        this.endVersion = endVersion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getStartVersion() {
        return startVersion;
    }

    public void setStartVersion(Long startVersion) {
        this.startVersion = startVersion;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getBookMarkMysqlId() {
        return bookMarkMysqlId;
    }

    public void setBookMarkMysqlId(Long bookMarkMysqlId) {
        this.bookMarkMysqlId = bookMarkMysqlId;
    }
    /**
     * 创建一个书签命令
     * @param path
     * @param bookmark
     * @param startVersion
     * @param endVersion
     * @param command
     * @return
     */
    public static BkmkCommand buildCommand(String path, BookMark bookmark, Long startVersion, Long endVersion, int command) {
        LocalDateTime now = LocalDateTime.now();
        return new BkmkCommand(path,bookmark,now,now, Constants.STATE_ENABLE,command,startVersion,endVersion);
    }


    public BkmkCommand(String path, BookMark bookMark, LocalDateTime createTime, LocalDateTime updateTime,
                       int state, int command, Long startVersion, Long endVersion) {
        this.path = path;
        this.bookMark = bookMark;
        this.createTime = createTime;
        this.updateTime = updateTime;

        this.state = state;
        this.command = command;
        this.startVersion = startVersion;
        this.endVersion = endVersion;
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
        BkmkCommand other = (BkmkCommand) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCommand() == null ? other.getCommand() == null : this.getCommand().equals(other.getCommand()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEndVersion() == null ? other.getEndVersion() == null : this.getEndVersion().equals(other.getEndVersion()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getStartVersion() == null ? other.getStartVersion() == null : this.getStartVersion().equals(other.getStartVersion()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getBookMarkMysqlId() == null ? other.getBookMarkMysqlId() == null : this.getBookMarkMysqlId().equals(other.getBookMarkMysqlId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCommand() == null) ? 0 : getCommand().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEndVersion() == null) ? 0 : getEndVersion().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getStartVersion() == null) ? 0 : getStartVersion().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getBookMarkMysqlId() == null) ? 0 : getBookMarkMysqlId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", command=").append(command);
        sb.append(", createTime=").append(createTime);
        sb.append(", endVersion=").append(endVersion);
        sb.append(", path=").append(path);
        sb.append(", startVersion=").append(startVersion);
        sb.append(", state=").append(state);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", bookMarkMysqlId=").append(bookMarkMysqlId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}