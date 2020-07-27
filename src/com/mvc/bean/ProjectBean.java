package com.mvc.bean;

import java.util.List;

public class ProjectBean {
    private String projectName;
    private List<FeatureBean> features;

    public ProjectBean() {
    }

    public ProjectBean(String projectName, List<FeatureBean> features) {
        this.projectName = projectName;
        this.features = features;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<FeatureBean> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureBean> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "ProjectBean{" +
                "projectName='" + projectName + '\'' +
                ", features=" + features +
                '}';
    }
}
