package com.balance.api.dto;

/**
 * @autho 孔垂云
 * @date 2017/6/21.
 */
public class BasePolicyDTO {
    private int id;
    private String title;  //标题

    public BasePolicyDTO() {
    }

    public BasePolicyDTO(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "BasePolicyDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
