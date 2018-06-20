package com.eve.bookmarks.entitys;

import javax.persistence.*;

/**
 * 由用户创建的时刻表
 */
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private String createTimeMils;
    /**
     * 首次执行时间
     */
    @Column(length = 20)
    private String firstDeadLine;
    /**
     *计划类型：年月日时分秒
     */
    @Column(length = 2)
    private int scheduleType;

    @Column(length = 25)
    private String title;

    @Column(length = 100)
    private String remark;

    @Column
    private Long userId;

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
