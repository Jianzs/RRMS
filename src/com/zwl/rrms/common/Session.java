package com.zwl.rrms.common;

import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

public class Session {
    private UserEntity user;
    private HouseEntity house;
    private ViewRecordEntity viewRecord;

    private boolean houseMarketUpSort;

    private HouseEntity backHouseDetail;
    private UserEntity backUserDetail;
    private ContactEntity backContactDetail;
    private ViewRecordEntity backViewRecordDetail;

    private UserEntity selectedUser;

    private boolean isModify = false;
    private HouseEntity modifiedHouse;
    private UserEntity modifiedUser;
    private ContactEntity modifiedContact;
    private ViewRecordEntity modifiedView;

    private boolean isContact = false;
    private Integer role;
    private UserEntity addContactRoomer;
    private UserEntity addContactRenter;

    private static Session ourInstance = new Session();

    public boolean getHouseMarketUpSort() {
        return houseMarketUpSort;
    }

    public void setHouseMarketUpSort(boolean houseMarketUpSort) {
        this.houseMarketUpSort = houseMarketUpSort;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserEntity getAddContactRoomer() {
        return addContactRoomer;
    }

    public void setAddContactRoomer(UserEntity addContactRoomer) {
        this.addContactRoomer = addContactRoomer;
    }

    public UserEntity getAddContactRenter() {
        return addContactRenter;
    }

    public void setAddContactRenter(UserEntity addContactRenter) {
        this.addContactRenter = addContactRenter;
    }

    public HouseEntity getModifiedHouse() {
        return modifiedHouse;
    }

    public void setModifiedHouse(HouseEntity modifiedHouse) {
        this.modifiedHouse = modifiedHouse;
    }

    public UserEntity getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(UserEntity modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public ContactEntity getModifiedContact() {
        return modifiedContact;
    }

    public void setModifiedContact(ContactEntity modifiedContact) {
        this.modifiedContact = modifiedContact;
    }

    public ViewRecordEntity getModifiedView() {
        return modifiedView;
    }

    public void setModifiedView(ViewRecordEntity modifiedView) {
        this.modifiedView = modifiedView;
    }

    public boolean isModify() {
        return isModify;
    }

    public void setModify(boolean modify) {
        isModify = modify;
    }

    public static Session getInstance() {
        return ourInstance;
    }

    public UserEntity getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserEntity selectedUser) {
        this.selectedUser = selectedUser;
    }

    public ViewRecordEntity getBackViewRecordDetail() {
        return backViewRecordDetail;
    }

    public void setBackViewRecordDetail(ViewRecordEntity backViewRecordDetail) {
        this.backViewRecordDetail = backViewRecordDetail;
    }

    public ContactEntity getBackContactDetail() {
        return backContactDetail;
    }

    public void setBackContactDetail(ContactEntity backContactDetail) {
        this.backContactDetail = backContactDetail;
    }

    public UserEntity getBackUserDetail() {
        return backUserDetail;
    }

    public void setBackUserDetail(UserEntity backUserDetail) {
        this.backUserDetail = backUserDetail;
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

    public HouseEntity getBackHouseDetail() {
        return backHouseDetail;
    }

    public void setBackHouseDetail(HouseEntity backHouseDetail) {
        this.backHouseDetail = backHouseDetail;
    }
}
