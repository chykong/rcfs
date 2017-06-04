package com.balance.base.model;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
public class BaseContacts {
    private int type;//类型，1管理公司人员2项目参与人员

    private int id;//
    private String name;//
    private String mobile;//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "BaseContacts{" +
                "type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
