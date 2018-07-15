package com.eve.bookmarks.entitys;

import com.eve.bookmarks.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 由用户创建的时刻表
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 首次执行时间时间戳
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime firstDeadLine;
    /**
     * 计划等级
     */
    @Column(length = 2)
    private int level;
    /**
     * 类型，标识是哪种计划：一次性还是循环，默认循环
     */
    @Column
    private boolean isCircle = true;
    /**
     * 计划类型：年月日时,默认日
     */
    @Column(length = 2)
    private int scheduleType = 2;

    @Column(length = 25)
    private String title;

    @Column(length = 100)
    private String remark;

    @JoinColumn
    @ManyToOne
    private User user;

    @Column(length = 10)
    private int scheduleNum;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getFirstDeadLine() {
        return firstDeadLine;
    }

    public void setFirstDeadLine(LocalDateTime firstDeadLine) {
        this.firstDeadLine = firstDeadLine;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(int scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 构建下一个计划节点
     *
     * @param record
     * @return
     */
    public ScheduleRecord builderNextRecord(ScheduleRecord record) {
        if (record == null) {
            record = new ScheduleRecord(firstDeadLine, createTime, this, user);
        } else {
            ScheduleRecord newRecord = new ScheduleRecord();
            BeanUtils.copyProperties(record, newRecord);
            newRecord.setDeadLine(DateUtils.addMils(newRecord.getDeadLine(), scheduleType, scheduleNum));
        }
        return record;
    }
}
