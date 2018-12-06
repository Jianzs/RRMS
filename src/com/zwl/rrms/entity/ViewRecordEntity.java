package com.zwl.rrms.entity;

import com.zwl.rrms.constant.ViewRecord;

public class ViewRecordEntity {
    private Integer id;
    private Integer planTime;
    private Integer renterId;
    private Integer roomId;
    private String description;
    private Integer roomerAck;
    private Integer adminAck;
    private Integer state;


    public static class Builder {
        private Integer id;
        private Integer planTime;
        private Integer renterId;
        private Integer roomId;
        private String description = "No Description";
        private Integer roomerAck = ViewRecord.ACK.NO_RESPONSE;
        private Integer adminAck = ViewRecord.ACK.NO_RESPONSE;
        private Integer state = ViewRecord.State.NEW;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setPlanTime(Integer planTime) {
            this.planTime = planTime;
            return this;
        }

        public Builder setRenterId(Integer renterId) {
            this.renterId = renterId;
            return this;
        }

        public Builder setRoomId(Integer roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setRoomerAck(Integer roomerAck) {
            this.roomerAck = roomerAck;
            return this;
        }

        public Builder setAdminAck(Integer adminAck) {
            this.adminAck = adminAck;
            return this;
        }

        public Builder setState(Integer state) {
            this.state = state;
            return this;
        }

        public ViewRecordEntity build() {
            ViewRecordEntity entity = new ViewRecordEntity();
            entity.id = id;
            entity.planTime = planTime;
            entity.renterId = renterId;
            entity.roomId = roomId;
            entity.description = description;
            entity.roomerAck = roomerAck;
            entity.adminAck = adminAck;
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

    public Integer getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Integer planTime) {
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
