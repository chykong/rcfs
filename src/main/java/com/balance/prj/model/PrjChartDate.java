package com.balance.prj.model;

/**
 * Created by dsy on 2017/6/9.
 * 接收图表最大最小时间类
 */
public class PrjChartDate {
    private String min_in_host_date;  //最小入户日期
    private String max_in_host_date;  //   最大入户日期
    private String min_sign_date;   //  最小签约日期
    private String max_sign_date;   //  最大签约日期
    private String min_handover_date; //  最小交房日期
    private String max_handover_date; //  最大交房日期

    public String getMin_in_host_date() {
        return min_in_host_date;
    }

    public void setMin_in_host_date(String min_in_host_date) {
        this.min_in_host_date = min_in_host_date;
    }

    public String getMax_in_host_date() {
        return max_in_host_date;
    }

    public void setMax_in_host_date(String max_in_host_date) {
        this.max_in_host_date = max_in_host_date;
    }

    public String getMin_sign_date() {
        return min_sign_date;
    }

    public void setMin_sign_date(String min_sign_date) {
        this.min_sign_date = min_sign_date;
    }

    public String getMax_sign_date() {
        return max_sign_date;
    }

    public void setMax_sign_date(String max_sign_date) {
        this.max_sign_date = max_sign_date;
    }

    public String getMin_handover_date() {
        return min_handover_date;
    }

    public void setMin_handover_date(String min_handover_date) {
        this.min_handover_date = min_handover_date;
    }

    public String getMax_handover_date() {
        return max_handover_date;
    }

    public void setMax_handover_date(String max_handover_date) {
        this.max_handover_date = max_handover_date;
    }
}
