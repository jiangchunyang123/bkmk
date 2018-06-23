package com.eve.bookmarks.entitys;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ScheduleRecord implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private String createTimeMils;
    /**
     * 标识状态：
     */
    @Column(length = 2)
    private int state;
    /**
     * 更新时间戳
     */
    @Column(length = 20)
    private String updateTimeMils;

    @JoinColumn
    @ManyToOne
    private Schedule schedule;
    @JoinColumn
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

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

    public String getUpdateTimeMils() {
        return updateTimeMils;
    }

    public void setUpdateTimeMils(String updateTimeMils) {
        this.updateTimeMils = updateTimeMils;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
