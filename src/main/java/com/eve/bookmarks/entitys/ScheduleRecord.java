package com.eve.bookmarks.entitys;

import com.eve.bookmarks.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ScheduleRecord extends PageFilter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 标识状态：默认0
     */
    @Column(length = 2)
    private int state = Constants.TODO;
    /**
     * 更新时间戳
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 更新时间戳
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadLine;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String remark;

    public ScheduleRecord(LocalDateTime deadLine, LocalDateTime createTime, Schedule schedule, User user) {
        this.createTime = createTime;
        this.schedule = schedule;
        this.user = user;
        this.deadLine = deadLine;
    }

    public ScheduleRecord() {

    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
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

}
