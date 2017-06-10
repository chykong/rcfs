package com.balance.api.dto;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class ContactsGroupsDTO {
    private String name;//组名
    private int total;//总记录数

    @Override
    public String toString() {
        return "ContactsGroupsDTO{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
