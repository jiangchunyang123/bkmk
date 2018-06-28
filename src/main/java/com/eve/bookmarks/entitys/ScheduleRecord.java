package com.eve.bookmarks.entitys;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ScheduleRecord implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private Long createTimeMils;
    /**
     * 标识状态：默认0
     */
    @Column(length = 2)
    private int state =0;
    /**
     * 更新时间戳
     */
    @Column(length = 20)
    private Long updateTimeMils;
    /**
     * 更新时间戳
     */
    @Column(length = 20)
    private Long deadLineMils;

    @JoinColumn
    @ManyToOne(fetch=FetchType.LAZY)
    private Schedule schedule;
    @JoinColumn
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    public ScheduleRecord(Long deadLineMils ,Long createTimeMils, Schedule schedule, User user) {
        this.createTimeMils = createTimeMils;
        this.schedule = schedule;
        this.user = user;
        this.deadLineMils = deadLineMils;
    }

    public ScheduleRecord() {

    }

    public Long getDeadLineMils() {
        return deadLineMils;
    }

    public void setDeadLineMils(Long deadLine) {
        this.deadLineMils = deadLine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getCreateTimeMils() {
        return createTimeMils;
    }

    public void setCreateTimeMils(Long createTimeMils) {
        this.createTimeMils = createTimeMils;
    }

    public Long getUpdateTimeMils() {
        return updateTimeMils;
    }

    public void setUpdateTimeMils(Long updateTimeMils) {
        this.updateTimeMils = updateTimeMils;
    }
}
