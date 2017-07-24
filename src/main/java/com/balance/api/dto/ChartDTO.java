package com.balance.api.dto;

/**
 * @autho 孔垂云
 * @date 2017/6/24.
 * 饼图的数据
 */
public class ChartDTO {

    private int progress;//进度，当前进度1入户2签约3交房
    private float in_host_count;//入户按户数总数
    private float in_host_over;//入户按户数已完成
    private float in_host_percent;//入户按户数百分比
    private float in_landarea_count;//入户占地面积总数
    private float in_landarea_over;//入户占地面积已完成
    private float in_landarea_percent;//入户占地面积百分比
    private float in_homestead_count;//入户建筑面积总数
    private float in_homestead_over;//入户建筑面积已完成
    private float in_homestead_percent;//入户建筑面积百分比

    private float sign_host_count;//签约按户数总数
    private float sign_host_over;//签约按户数已完成
    private float sign_host_percent;//签约按户数百分比
    private float sign_landarea_count;//签约占地面积总数
    private float sign_landarea_over;//签约占地面积已完成
    private float sign_landarea_percent;//签约占地面积百分比
    private float sign_homestead_count;//签约建筑面积总数
    private float sign_homestead_over;//签约建筑面积已完成
    private float sign_homestead_percent;//签约建筑面积百分比


    private float handover_host_count;//交房按户数总数
    private float handover_host_over;//交房按户数已完成
    private float handover_host_percent;//交房按户数百分比
    private float handover_landarea_count;//交房占地面积总数
    private float handover_landarea_over;//交房占地面积已完成
    private float handover_landarea_percent;//交房占地面积百分比
    private float handover_homestead_count;//交房建筑面积总数
    private float handover_homestead_over;//交房建筑面积已完成
    private float handover_homestead_percent;//交房建筑面积百分比

    @Override
    public String toString() {
        return "ChartDTO{" +
                "progress=" + progress +
                ", in_host_count=" + in_host_count +
                ", in_host_over=" + in_host_over +
                ", in_host_percent=" + in_host_percent +
                ", in_landarea_count=" + in_landarea_count +
                ", in_landarea_over=" + in_landarea_over +
                ", in_landarea_percent=" + in_landarea_percent +
                ", in_homestead_count=" + in_homestead_count +
                ", in_homestead_over=" + in_homestead_over +
                ", in_homestead_percent=" + in_homestead_percent +
                ", sign_host_count=" + sign_host_count +
                ", sign_host_over=" + sign_host_over +
                ", sign_host_percent=" + sign_host_percent +
                ", sign_landarea_count=" + sign_landarea_count +
                ", sign_landarea_over=" + sign_landarea_over +
                ", sign_landarea_percent=" + sign_landarea_percent +
                ", sign_homestead_count=" + sign_homestead_count +
                ", sign_homestead_over=" + sign_homestead_over +
                ", sign_homestead_percent=" + sign_homestead_percent +
                ", handover_host_count=" + handover_host_count +
                ", handover_host_over=" + handover_host_over +
                ", handover_host_percent=" + handover_host_percent +
                ", handover_landarea_count=" + handover_landarea_count +
                ", handover_landarea_over=" + handover_landarea_over +
                ", handover_landarea_percent=" + handover_landarea_percent +
                ", handover_homestead_count=" + handover_homestead_count +
                ", handover_homestead_over=" + handover_homestead_over +
                ", handover_homestead_percent=" + handover_homestead_percent +
                '}';
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public float getIn_host_count() {
        return in_host_count;
    }

    public void setIn_host_count(float in_host_count) {
        this.in_host_count = in_host_count;
    }

    public float getIn_host_over() {
        return in_host_over;
    }

    public void setIn_host_over(float in_host_over) {
        this.in_host_over = in_host_over;
    }

    public float getIn_host_percent() {
        return in_host_percent;
    }

    public void setIn_host_percent(float in_host_percent) {
        this.in_host_percent = in_host_percent;
    }

    public float getIn_landarea_count() {
        return in_landarea_count;
    }

    public void setIn_landarea_count(float in_landarea_count) {
        this.in_landarea_count = in_landarea_count;
    }

    public float getIn_landarea_over() {
        return in_landarea_over;
    }

    public void setIn_landarea_over(float in_landarea_over) {
        this.in_landarea_over = in_landarea_over;
    }

    public float getIn_landarea_percent() {
        return in_landarea_percent;
    }

    public void setIn_landarea_percent(float in_landarea_percent) {
        this.in_landarea_percent = in_landarea_percent;
    }

    public float getIn_homestead_count() {
        return in_homestead_count;
    }

    public void setIn_homestead_count(float in_homestead_count) {
        this.in_homestead_count = in_homestead_count;
    }

    public float getIn_homestead_over() {
        return in_homestead_over;
    }

    public void setIn_homestead_over(float in_homestead_over) {
        this.in_homestead_over = in_homestead_over;
    }

    public float getIn_homestead_percent() {
        return in_homestead_percent;
    }

    public void setIn_homestead_percent(float in_homestead_percent) {
        this.in_homestead_percent = in_homestead_percent;
    }

    public float getSign_host_count() {
        return sign_host_count;
    }

    public void setSign_host_count(float sign_host_count) {
        this.sign_host_count = sign_host_count;
    }

    public float getSign_host_over() {
        return sign_host_over;
    }

    public void setSign_host_over(float sign_host_over) {
        this.sign_host_over = sign_host_over;
    }

    public float getSign_host_percent() {
        return sign_host_percent;
    }

    public void setSign_host_percent(float sign_host_percent) {
        this.sign_host_percent = sign_host_percent;
    }

    public float getSign_landarea_count() {
        return sign_landarea_count;
    }

    public void setSign_landarea_count(float sign_landarea_count) {
        this.sign_landarea_count = sign_landarea_count;
    }

    public float getSign_landarea_over() {
        return sign_landarea_over;
    }

    public void setSign_landarea_over(float sign_landarea_over) {
        this.sign_landarea_over = sign_landarea_over;
    }

    public float getSign_landarea_percent() {
        return sign_landarea_percent;
    }

    public void setSign_landarea_percent(float sign_landarea_percent) {
        this.sign_landarea_percent = sign_landarea_percent;
    }

    public float getSign_homestead_count() {
        return sign_homestead_count;
    }

    public void setSign_homestead_count(float sign_homestead_count) {
        this.sign_homestead_count = sign_homestead_count;
    }

    public float getSign_homestead_over() {
        return sign_homestead_over;
    }

    public void setSign_homestead_over(float sign_homestead_over) {
        this.sign_homestead_over = sign_homestead_over;
    }

    public float getSign_homestead_percent() {
        return sign_homestead_percent;
    }

    public void setSign_homestead_percent(float sign_homestead_percent) {
        this.sign_homestead_percent = sign_homestead_percent;
    }

    public float getHandover_host_count() {
        return handover_host_count;
    }

    public void setHandover_host_count(float handover_host_count) {
        this.handover_host_count = handover_host_count;
    }

    public float getHandover_host_over() {
        return handover_host_over;
    }

    public void setHandover_host_over(float handover_host_over) {
        this.handover_host_over = handover_host_over;
    }

    public float getHandover_host_percent() {
        return handover_host_percent;
    }

    public void setHandover_host_percent(float handover_host_percent) {
        this.handover_host_percent = handover_host_percent;
    }

    public float getHandover_landarea_count() {
        return handover_landarea_count;
    }

    public void setHandover_landarea_count(float handover_landarea_count) {
        this.handover_landarea_count = handover_landarea_count;
    }

    public float getHandover_landarea_over() {
        return handover_landarea_over;
    }

    public void setHandover_landarea_over(float handover_landarea_over) {
        this.handover_landarea_over = handover_landarea_over;
    }

    public float getHandover_landarea_percent() {
        return handover_landarea_percent;
    }

    public void setHandover_landarea_percent(float handover_landarea_percent) {
        this.handover_landarea_percent = handover_landarea_percent;
    }

    public float getHandover_homestead_count() {
        return handover_homestead_count;
    }

    public void setHandover_homestead_count(float handover_homestead_count) {
        this.handover_homestead_count = handover_homestead_count;
    }

    public float getHandover_homestead_over() {
        return handover_homestead_over;
    }

    public void setHandover_homestead_over(float handover_homestead_over) {
        this.handover_homestead_over = handover_homestead_over;
    }

    public float getHandover_homestead_percent() {
        return handover_homestead_percent;
    }

    public void setHandover_homestead_percent(float handover_homestead_percent) {
        this.handover_homestead_percent = handover_homestead_percent;
    }
}
