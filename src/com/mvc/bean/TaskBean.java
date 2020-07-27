package com.mvc.bean;

public class TaskBean {
    private String projectId ;
    private String employeeId ;
    private String mainFeature;
    private String taskDetail ;
    private String date ;
    private String taskType ;
    private String numberOfHours ;
    private String comments ;

    public TaskBean() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(String numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMainFeature() {
        return mainFeature;
    }

    public void setMainFeature(String mainFeature) {
        this.mainFeature = mainFeature;
    }
}
