package com.balance.api.dto;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class LoginApiDTO {
    private String username;//
    private String password;//

    @Override
    public String toString() {
        return "LoginApiDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
