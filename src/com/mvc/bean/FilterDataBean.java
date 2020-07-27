package com.mvc.bean;

public class FilterDataBean {

    private String userId;
    private String projectId;
    private String startDate;
    private String endDate;

    public FilterDataBean() {
    }

    public FilterDataBean(String userId, String projectId, String startDate, String endDate) {
        this.userId = userId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "FilterDataBean{" +
                "userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
