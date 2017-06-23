package com.balance.api.dto;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class ContactsDTO {
    private String name;//组名
    private String mobile;//手机号
    private int id;//
    private String user_duty;//职位

    @Override
    public String toString() {
        return "ContactsDTO{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id=" + id +
                ", user_duty='" + user_duty + '\'' +
                '}';
    }

    public String getUser_duty() {
        return user_duty;
    }

    public void setUser_duty(String user_duty) {
        this.user_duty = user_duty;
    }

    public ContactsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
