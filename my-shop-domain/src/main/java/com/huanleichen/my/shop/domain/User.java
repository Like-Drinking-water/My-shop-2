package com.huanleichen.my.shop.domain;

public class User {
    private String email;
    private String password;
    private String userName;
    private boolean remember;

    public User() {
    }

    public User(String email, String password, String userName, boolean remember) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.remember = remember;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean getRemember() {
        return remember;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
