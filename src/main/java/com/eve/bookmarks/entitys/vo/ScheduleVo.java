package com.eve.bookmarks.entitys.vo;

/**
 * 用来与计划表交互
 */
public class ScheduleVo {

    private Long schId;
    private Long recordId;
    private Integer state;
    private String scheduleTitle;

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public Long getSchId() {
        return schId;
    }

    public void setSchId(Long schId) {
        this.schId = schId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
