package com.balance.prj.vo;

import java.util.Arrays;

/**
 * Author  孔垂云
 * Date  2017/6/6.
 */
public class ChartsDataVO {

    private String barTitle1;//柱状图分类1
    private String barTitle2;//柱状图分类2
    private float[] barData1;//柱状图数据1
    private float[] barData2;//柱状图数据2
    private String[] barCategories;//柱状图分组

    private String barSectionTitle1;//标段柱状图分类1
    private String barSectionTitle2;//标段柱状图分类2
    private float[] barSectionData1;//标段柱状图数据1
    private float[] barSectionData2;//标段柱状图数据2
    private String[] barSectionCategories;//标段柱状图分组

    private String guageTitle;//仪表盘标题
    private float guageData;//仪表盘百分比
    private float guageData2;//仪表盘已完成数
    private String guageDataTitle;//仪表盘标题


    private int type;//类型
    private int search_type;  //查询类型

    public String getBarSectionTitle1() {
        return barSectionTitle1;
    }

    public void setBarSectionTitle1(String barSectionTitle1) {
        this.barSectionTitle1 = barSectionTitle1;
    }

    public String getBarSectionTitle2() {
        return barSectionTitle2;
    }

    public void setBarSectionTitle2(String barSectionTitle2) {
        this.barSectionTitle2 = barSectionTitle2;
    }

    public float[] getBarSectionData1() {
        return barSectionData1;
    }

    public void setBarSectionData1(float[] barSectionData1) {
        this.barSectionData1 = barSectionData1;
    }

    public float[] getBarSectionData2() {
        return barSectionData2;
    }

    public void setBarSectionData2(float[] barSectionData2) {
        this.barSectionData2 = barSectionData2;
    }

    public String[] getBarSectionCategories() {
        return barSectionCategories;
    }

    public void setBarSectionCategories(String[] barSectionCategories) {
        this.barSectionCategories = barSectionCategories;
    }

    public String getGuageDataTitle() {
        return guageDataTitle;
    }

    public void setGuageDataTitle(String guageDataTitle) {
        this.guageDataTitle = guageDataTitle;
    }

    @Override
    public String toString() {
        return "ChartsDataVO{" +
                "barTitle1='" + barTitle1 + '\'' +
                ", barTitle2='" + barTitle2 + '\'' +
                ", barData1=" + Arrays.toString(barData1) +
                ", barData2=" + Arrays.toString(barData2) +
                ", barCategories=" + Arrays.toString(barCategories) +
                ", guageTitle='" + guageTitle + '\'' +
                ", guageData=" + guageData +
                ", guageData2=" + guageData2 +
                ", type=" + type +
                '}';
    }

    public int getSearch_type() {
        return search_type;
    }

    public void setSearch_type(int search_type) {
        this.search_type = search_type;
    }

    public float getGuageData2() {
        return guageData2;
    }

    public void setGuageData2(float guageData2) {
        this.guageData2 = guageData2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float[] getBarData1() {
        return barData1;
    }

    public void setBarData1(float[] barData1) {
        this.barData1 = barData1;
    }

    public float[] getBarData2() {
        return barData2;
    }

    public void setBarData2(float[] barData2) {
        this.barData2 = barData2;
    }

    public String getBarTitle1() {
        return barTitle1;
    }

    public void setBarTitle1(String barTitle1) {
        this.barTitle1 = barTitle1;
    }

    public String getBarTitle2() {
        return barTitle2;
    }

    public void setBarTitle2(String barTitle2) {
        this.barTitle2 = barTitle2;
    }


    public String[] getBarCategories() {
        return barCategories;
    }

    public void setBarCategories(String[] barCategories) {
        this.barCategories = barCategories;
    }

    public String getGuageTitle() {
        return guageTitle;
    }

    public void setGuageTitle(String guageTitle) {
        this.guageTitle = guageTitle;
    }

    public float getGuageData() {
        return guageData;
    }

    public void setGuageData(float guageData) {
        this.guageData = guageData;
    }
}
