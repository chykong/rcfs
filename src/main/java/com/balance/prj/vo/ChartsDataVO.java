package com.balance.prj.vo;

import java.util.Arrays;

/**
 * Author  孔垂云
 * Date  2017/6/6.
 */
public class ChartsDataVO {

    private String barTitle1;//柱状图分类1
    private String barTitle2;//柱状图分类2
    private int[] barData1;//柱状图数据1
    private int[] barData2;//柱状图数据2
    private String[] barCategories;//柱状图分组

    private String guageTitle;//仪表盘标题
    private float guageData;//仪表盘百分比

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
                '}';
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

    public int[] getBarData1() {
        return barData1;
    }

    public void setBarData1(int[] barData1) {
        this.barData1 = barData1;
    }

    public int[] getBarData2() {
        return barData2;
    }

    public void setBarData2(int[] barData2) {
        this.barData2 = barData2;
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
