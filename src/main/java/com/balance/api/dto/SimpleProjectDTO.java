package com.balance.api.dto;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class SimpleProjectDTO {
    private int id;//项目id
    private String name;//项目名称

    public SimpleProjectDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
