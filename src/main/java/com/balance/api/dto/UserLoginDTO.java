package com.balance.api.dto;

/**
 * @autho 孔垂云
 * @date 2017/6/24.
 * 用户登录DTO
 */
public class UserLoginDTO {
    private String access_token;//token
    private String realname;//姓名

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "access_token='" + access_token + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
