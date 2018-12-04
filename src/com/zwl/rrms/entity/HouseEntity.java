package com.zwl.rrms.entity;


import com.zwl.rrms.constant.House;

public class HouseEntity {
    private Integer id;
    private Integer provinceId;
    private Integer cityId;
    private Integer countyId;
    private String address;
    private Integer type;
    private Integer maxCustomer;
    private Double rent;
    private Integer state;
    private Double serviceCharge;
    private Integer roomerId;
    private String description;
    private Integer freetime;
    private String picture;
    private String neighborhood;

    public static class Builder {
        private Integer id = null;
        private Integer provinceId;
        private Integer cityId;
        private Integer countyId;
        private String address;
        private Integer type = House.Type.BUNGALOW;
        private Integer maxCustomer = 1;
        private Double rent = 2000d;
        private Integer state = House.State.UNPAID;
        private Double serviceCharge = null;
        private Integer roomerId;
        private String description;
        private Integer freetime = House.FreeTime.ANY_TIME;
        private String picture = "null";
        private String neighborhood = "未命名";

        public Builder setProvinceId(Integer provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public Builder setCityId(Integer cityId) {
            this.cityId = cityId;
            return this;
        }

        public Builder setCountyId(Integer countyId) {
            this.countyId = countyId;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setType(Integer type) {
            this.type = type;
            return this;
        }

        public Builder setMaxCustomer(Integer maxCustomer) {
            this.maxCustomer = maxCustomer;
            return this;
        }

        public Builder setRent(Double rent) {
            this.rent = rent;
            if (serviceCharge == null)
                serviceCharge = rent * 0.05;
            return this;
        }

        public Builder setState(Integer state) {
            this.state = state;
            return this;
        }

        public Builder setServiceCharge(Double serviceCharge) {
            this.serviceCharge = serviceCharge;
            return this;
        }

        public Builder setRoomerId(Integer roomerId) {
            this.roomerId = roomerId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setFreetime(Integer freetime) {
            this.freetime = freetime;
            return this;
        }

        public Builder setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public HouseEntity build() {
            HouseEntity en = new HouseEntity();
            en.id = this.id;
            en.provinceId = provinceId;
            en.cityId = cityId;
            en.countyId = countyId;
            en.address = address;
            en.type = this.type;
            en.maxCustomer = this.maxCustomer;
            en.rent = this.rent;
            en.state = this.state;
            en.serviceCharge = this.serviceCharge;
            en.roomerId = this.roomerId;
            en.description = this.description;
            en.freetime = this.freetime;
            en.picture = this.picture;
            en.neighborhood = this.neighborhood;
            return en;
        }
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMaxCustomer() {
        return maxCustomer;
    }

    public void setMaxCustomer(Integer maxCustomer) {
        this.maxCustomer = maxCustomer;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Integer getRoomerId() {
        return roomerId;
    }

    public void setRoomerId(Integer roomerId) {
        this.roomerId = roomerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFreetime() {
        return freetime;
    }

    public void setFreetime(Integer freetime) {
        this.freetime = freetime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
