package com.zwl.rrms.common;

import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

public class Session {
    private UserEntity user;
    private HouseEntity house;
    private ViewRecordEntity viewRecord;

    private static Session ourInstance = new Session();

    public static Session getInstance() {
        return ourInstance;
    }

    public ViewRecordEntity getViewRecord() {
        return viewRecord;
    }

    public void setViewRecord(ViewRecordEntity viewRecord) {
        this.viewRecord = viewRecord;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
