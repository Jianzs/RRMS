package com.zwl.rrms.entity;

import com.zwl.rrms.constant.User;

public class UserEntity {
    private Integer id;
    private String phone;
    private String password;
    private String name;
    private Integer provinceId;
    private Integer cityId;
    private Integer countyId;
    private String address;
    private Long birthday;
    private Integer gender;
    private Integer state;

    public static class Builder {
        private Integer id;
        private String phone;
        private String password;
        private String name = "Stranger";
        private Integer provinceId;
        private Integer cityId;
        private Integer countyId;
        private String address;
        private Long birthday;
        private Integer gender = User.Gender.FEMALE;
        private Integer state = User.State.NORMAL;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

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

        public void setAddress(String address) {
            this.address = address;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public Builder setBirthday(Long birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setGender(Integer gender) {
            this.gender = gender;
            return this;
        }

        public Builder setState(Integer state) {
            this.state = state;
            return this;
        }

        public UserEntity build() {
            UserEntity user = new UserEntity();
            user.phone = phone;
            user.password = password;
            user.name = name;
            user.provinceId = provinceId;
            user.cityId = cityId;
            user.countyId = countyId;
            user.address = address;
            user.birthday = birthday;
            user.gender = gender;
            user.state = state;
            return user;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
