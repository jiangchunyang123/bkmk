package com.eve.bookmarks.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private String createTimeMils;
    /**
     * 首次执行时间时间戳
     */
    @Column(length = 20)
    private String firstDeadLine;
    /**
     * 计划等级
     */
    @Column(length = 2)
    private int rate;
    /**
     * 类型，标识是哪种计划：一次性还是循环
     */
    @Column(length = 2)
    private int circleType;
    /**
     *计划类型：年月日时分秒
     */
    @Column(length = 2)
    private int scheduleType;

    @Column(length = 25)
    private String title;

    @Column(length = 100)
    private String remark;

    @Column()
    private Long userId;

    @Column(length = 10)
    private int scheduleNum;

    private LocalDateTime testDate;

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getCircleType() {
        return circleType;
    }

    public void setCircleType(int circleType) {
        this.circleType = circleType;
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

    public String getCreateTimeMils() {
        return createTimeMils;
    }

    public void setCreateTimeMils(String createTimeMils) {
        this.createTimeMils = createTimeMils;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstDeadLine() {
        return firstDeadLine;
    }

    public void setFirstDeadLine(String firstDeadLine) {
        this.firstDeadLine = firstDeadLine;
    }
}
