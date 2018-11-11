package com.eve.bookmarks.entitys.vo;


import com.eve.bookmarks.entitys.po.ScheduleRecord;

import java.util.List;

public class MailMsg {
    private List<ScheduleRecord> nearEnds;
    private List<ScheduleRecord> overTimes;

    public MailMsg(List<ScheduleRecord> nearEnds, List<ScheduleRecord> overTimes) {
        this.nearEnds = nearEnds;
        this.overTimes = overTimes;
    }

    public List<ScheduleRecord> getNearEnds() {
        return nearEnds;
    }

    public void setNearEnds(List<ScheduleRecord> nearEnds) {
        this.nearEnds = nearEnds;
    }

    public List<ScheduleRecord> getOverTimes() {
        return overTimes;
    }

    public void setOverTimes(List<ScheduleRecord> overTimes) {
        this.overTimes = overTimes;
    }
}
