package com.zwl.rrms.entity;

import com.zwl.rrms.constant.Contact;

public class ContactEntity {
    private Integer id;
    private Long startTime;
    private Long endTime;
    private Integer state;
    private Integer roomId;
    private Integer renterId;
    private Double rent;
    private String leaseTerm;
    private String remark;

    public static class Builder {
        private Integer id;
        private Long startTime;
        private Long endTime;
        private Integer state = Contact.State.NORMAL;
        private Integer roomId;
        private Integer renterId;
        private Double rent = 0d;
        private String leaseTerm = "null";
        private String remark = "null";

        public Builder setStartTime(Long time) {
            this.startTime = time;
            return this;
        }

        public Builder setState(Integer state) {
            this.state = state;
            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setRoomId(Integer roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder setRenterId(Integer renterId) {
            this.renterId = renterId;
            return this;
        }

        public Builder setRent(Double rent) {
            this.rent = rent;
            return this;
        }

        public Builder setLeaseTerm(String leaseTerm) {
            this.leaseTerm = leaseTerm;
            return this;
        }

        public Builder setRemark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder setEndTime(Long endTime) {
            this.endTime = endTime;
            return this;
        }

        public ContactEntity build() {
            ContactEntity entity = new ContactEntity();
            entity.id = id;
            entity.startTime = startTime;
            entity.endTime = endTime;
            entity.state = state;
            entity.roomId = roomId;
            entity.renterId = renterId;
            entity.rent = rent;
            entity.leaseTerm = leaseTerm;
            entity.remark = remark;

            return entity;
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
