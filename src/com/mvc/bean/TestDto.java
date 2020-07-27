package com.mvc.bean;

public class TestDto {

    private int key;
    private String feature;

    public TestDto() {
    }

    public TestDto(int key, String feature) {
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

    @Override
    public String toString() {
        return "TestDto{" +
                "key=" + key +
                ", feature='" + feature + '\'' +
                '}';
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
