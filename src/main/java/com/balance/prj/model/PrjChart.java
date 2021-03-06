package com.balance.prj.model;

/**
 * Created by dsy on 2017/6/7.
 * 图标Model
 */
public class PrjChart {
    private String title;
    private int countLeftDay;

    private String groups; //分组
    private float total;  //累积
    private float today;  //今日改为昨日

    private String section;//标段
    private float is;//分标段已完成
    private float no;//分标段未完成

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public float getIs() {
        return is;
    }

    public void setIs(float is) {
        this.is = is;
    }

    public float getNo() {
        return no;
    }

    public void setNo(float no) {
        this.no = no;
    }

    public PrjChart() {

    }

    public PrjChart(String groups, float total, float today) {
        this.groups = groups;
        this.total = total;
        this.today = today;
    }

    public PrjChart(String title) {
        this.title = title;
    }

    public PrjChart(String title, int countLeftDay) {
        this.title = title;
        this.countLeftDay = countLeftDay;
    }

    @Override
    public String toString() {
        return "PrjChart{" +
                "title='" + title + '\'' +
                ", countLeftDay=" + countLeftDay +
                ", groups='" + groups + '\'' +
                ", total=" + total +
                ", today=" + today +
                '}';
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getToday() {
        return today;
    }

    public void setToday(float today) {
        this.today = today;
    }
}
