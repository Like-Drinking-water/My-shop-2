package com.huanleichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huanleichen.my.shop.commons.persistence.BaseEntity;

public class TbUser extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private String email;
    private boolean remember;

    public TbUser() {
    }

    public TbUser(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "TbUser{" +
                "id=" + super.getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", created=" + super.getCreated() +
                ", updated=" + super.getUpdated() +
                '}';
    }
}
