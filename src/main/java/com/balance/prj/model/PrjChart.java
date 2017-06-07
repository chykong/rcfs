package com.balance.prj.model;

/**
 * Created by dsy on 2017/6/7.
 * 图标Model
 */
public class PrjChart {
    private String title;
    private int countLeftDay;

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
}
