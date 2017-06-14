package com.balance.prj.vo;

/**
 * @autho 孔垂云
 * @date 2017/6/15.
 */
public class EntireStatVO {

    private String date;//日期
    private float data;//数量

    @Override
    public String toString() {
        return "EntireStatVO{" +
                "date='" + date + '\'' +
                ", data=" + data +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }
}
