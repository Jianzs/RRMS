package com.zwl.rrms.entity;

public class CityEntity {
    private Integer id;
    private String content;
    private Integer provinceId;

    public CityEntity() {
    }

    public CityEntity(String content, Integer provinceId) {
        this.content = content;
        this.provinceId = provinceId;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
