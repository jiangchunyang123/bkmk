package com.eve.bookmarks.schedule.domain.po;

import com.eve.bookmarks.common.utils.Constants;
import com.eve.bookmarks.common.utils.DateUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 
 */
public class Schedule implements Serializable {
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime firstDeadLine;

    private Boolean isCircle;

    private Integer level;

    private String remark;

    private Integer scheduleNum;

    private Integer scheduleType;

    private String title;

    private String userName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getIsCircle() {
        return isCircle;
    }

    public void setIsCircle(Boolean isCircle) {
        this.isCircle = isCircle;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(Integer scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public Integer getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static Schedule newDefault(){
        Schedule schedule = new Schedule();
        schedule.setLevel(1);
        schedule.setIsCircle(true);
        schedule.setScheduleType(1);
        schedule.setScheduleNum(1);
        return schedule;
    }
    /**
     * 构建下一个计划节点
     *
     * @param record
     * @return
     */
    public ScheduleRecord builderNextRecord(ScheduleRecord record) {
        ScheduleRecord newRecord ;
        if (record == null) {
            newRecord = new ScheduleRecord(firstDeadLine, createTime, this.getId(), userName);
        } else {
            newRecord = new ScheduleRecord();
            BeanUtils.copyProperties(record, newRecord);
            newRecord.setDeadLine(DateUtils.addMils(newRecord.getDeadLine(), scheduleType, scheduleNum));
            newRecord.setState(Constants.TODO);
            newRecord.setId(null);
        }
        return newRecord;
    }
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Schedule other = (Schedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getFirstDeadLine() == null ? other.getFirstDeadLine() == null : this.getFirstDeadLine().equals(other.getFirstDeadLine()))
            && (this.getIsCircle() == null ? other.getIsCircle() == null : this.getIsCircle().equals(other.getIsCircle()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getScheduleNum() == null ? other.getScheduleNum() == null : this.getScheduleNum().equals(other.getScheduleNum()))
            && (this.getScheduleType() == null ? other.getScheduleType() == null : this.getScheduleType().equals(other.getScheduleType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getFirstDeadLine() == null) ? 0 : getFirstDeadLine().hashCode());
        result = prime * result + ((getIsCircle() == null) ? 0 : getIsCircle().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getScheduleNum() == null) ? 0 : getScheduleNum().hashCode());
        result = prime * result + ((getScheduleType() == null) ? 0 : getScheduleType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", firstDeadLine=").append(firstDeadLine);
        sb.append(", isCircle=").append(isCircle);
        sb.append(", level=").append(level);
        sb.append(", remark=").append(remark);
        sb.append(", scheduleNum=").append(scheduleNum);
        sb.append(", scheduleType=").append(scheduleType);
        sb.append(", title=").append(title);
        sb.append(", userName=").append(userName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}