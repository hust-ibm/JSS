package com.hust.jss.entity;

import java.util.Date;

public class Task {
	
	//作业ID
    private Integer taskId;
    
    //作业名称 
    private String taskName;
    
    //作业路径
    private String taskPath;
    
    //作业评分规则存放路径
    private String taskRule;
    
    //作业截止日期
    private Date taskExpiry;
    
    //作业文件大小最小值
    private Integer taskMinsize;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskPath() {
        return taskPath;
    }

    public void setTaskPath(String taskPath) {
        this.taskPath = taskPath == null ? null : taskPath.trim();
    }

    public String getTaskRule() {
        return taskRule;
    }

    public void setTaskRule(String taskRule) {
        this.taskRule = taskRule == null ? null : taskRule.trim();
    }

    public Date getTaskExpiry() {
        return taskExpiry;
    }

    public void setTaskExpiry(Date taskExpiry) {
        this.taskExpiry = taskExpiry;
    }

    public Integer getTaskMinsize() {
        return taskMinsize;
    }

    public void setTaskMinsize(Integer taskMinsize) {
        this.taskMinsize = taskMinsize;
    }
}