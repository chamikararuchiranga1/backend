package com.mvc.bean;

public class FeatureBean {
    private int key;
    private String feature;

    public FeatureBean() {
    }

    public FeatureBean(int key, String feature) {
        this.key = key;
        this.feature = feature;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "FeatureBean{" +
                "key=" + key +
                ", feature='" + feature + '\'' +
                '}';
    }
}
