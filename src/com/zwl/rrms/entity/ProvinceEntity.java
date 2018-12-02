package com.zwl.rrms.entity;

public class ProvinceEntity {
    private Integer id;
    private String content;

    public ProvinceEntity() {
    }

    public ProvinceEntity(String content) {
        this.content = content;
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
}
