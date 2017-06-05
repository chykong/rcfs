package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

public class PrjMeetingSearchVO extends PageSearchVO {
    private Integer progress;//进度

    @Override
    public String toString() {
        return "PrjMeetingSearchVO{" +
                "progress=" + progress +
                '}';
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}