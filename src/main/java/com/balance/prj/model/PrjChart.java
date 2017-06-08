package com.balance.prj.model;

/**
 * Created by dsy on 2017/6/7.
 * 图标Model
 */
public class PrjChart {
    private String title;
    private int countLeftDay;

    private String groups; //分组
    private Integer total;  //累积
    private Integer today;  //今日

    public PrjChart() {

    }

    public PrjChart(String title, int countLeftDay) {
        this.title = title;
        this.countLeftDay = countLeftDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountLeftDay() {
        return countLeftDay;
    }

    public void setCountLeftDay(int countLeftDay) {
        this.countLeftDay = countLeftDay;
    }


    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }
}
