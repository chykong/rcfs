package com.balance.api.dto;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class ContactsDTO {
    private String name;//组名
    private String mobile;//手机号
    private int id;//

    public ContactsDTO() {
    }

    @Override
    public String toString() {
        return "ContactsDTO{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id=" + id +
                '}';
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
