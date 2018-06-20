package com.eve.bookmarks.entitys;

import javax.persistence.*;

@Entity
public class ScheduleRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private String createTimeMils;

    @JoinColumn
    @ManyToOne(fetch=FetchType.LAZY)
    private Schedule schedule;

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
