package com.eve.bookmarks.entitys;

import com.eve.bookmarks.utils.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 命令
 */
@Entity
public class BkmkCommand {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id ;
    @Column
    private String path;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private BookMark bookMark;

    /**
     * 更新时间戳
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间戳
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;




    @Column(length = 1)
    private int state;
    /**
     * 增加1 删除-1
     */
    @Column(length = 1)
    private int command;

    @Column
    private Long startVersion;

    @Column
    private Long endVersion;

    /**
     * 创建一个书签命令
     * @param path
     * @param bookmark
     * @param startVersion
     * @param endVersion
     * @param command
     * @return
     */
    public static BkmkCommand buildCommand(String path, BookMark bookmark,Long startVersion,Long endVersion,int command) {
        LocalDateTime now = LocalDateTime.now();
        return new BkmkCommand(path,bookmark,now,now,Constants.STATE_ENABLE,command,startVersion,endVersion);
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

    public Long getStartVersion() {
        return startVersion;
    }

    public void setStartVersion(Long startVersion) {
        this.startVersion = startVersion;
    }

    public Long getEndVersion() {
        return endVersion;
    }

    public void setEndVersion(Long endVersion) {
        this.endVersion = endVersion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BookMark getBookMark() {
        return bookMark;
    }

    public void setBookMark(BookMark bookMark) {
        this.bookMark = bookMark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
