package com.zwl.rrms.entity;

public class CountyEntity {
    private Integer id;
    private String content;
    private Integer cityId;

    public CountyEntity() {
    }

    public CountyEntity(String content, Integer cityId) {
        this.content = content;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
