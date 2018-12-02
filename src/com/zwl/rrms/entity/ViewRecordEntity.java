package com.zwl.rrms.entity;

import com.zwl.rrms.constant.ViewRecord;

public class ViewRecordEntity {
    private Integer id;
    private Long planTime;
    private Integer renterId;
    private Integer roomId;
    private String description;
    private Integer roomerAck;
    private Integer adminAck;
    private Integer state;


    public static class Builder {
        private Integer id;
        private Long plan_time;
        private Integer renter_id;
        private Integer room_id;
        private String description = "No Description";
        private Integer roomer_ack = ViewRecord.ACK.NO_RESPONSE;
        private Integer admin_ack = ViewRecord.ACK.NO_RESPONSE;
        private Integer state = ViewRecord.State.NEW;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setPlan_time(Long plan_time) {
            this.plan_time = plan_time;
            return this;
        }

        public Builder setRenter_id(Integer renter_id) {
            this.renter_id = renter_id;
            return this;
        }

        public Builder setRoom_id(Integer room_id) {
            this.room_id = room_id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setRoomer_ack(Integer roomer_ack) {
            this.roomer_ack = roomer_ack;
            return this;
        }

        public Builder setAdmin_ack(Integer admin_ack) {
            this.admin_ack = admin_ack;
            return this;
        }

        public Builder setState(Integer state) {
            this.state = state;
            return this;
        }

        public ViewRecordEntity build() {
            ViewRecordEntity entity = new ViewRecordEntity();
            entity.id = id;
            entity.planTime = plan_time;
            entity.renterId = renter_id;
            entity.roomId = room_id;
            entity.description = description;
            entity.roomerAck = roomer_ack;
            entity.adminAck = admin_ack;
            entity.state = state;
            return entity;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Long planTime) {
        this.planTime = planTime;
    }

    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoomerAck() {
        return roomerAck;
    }

    public void setRoomerAck(Integer roomerAck) {
        this.roomerAck = roomerAck;
    }

    public Integer getAdminAck() {
        return adminAck;
    }

    public void setAdminAck(Integer adminAck) {
        this.adminAck = adminAck;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
