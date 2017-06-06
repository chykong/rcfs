package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

public class PrjLogSearchVO extends PageSearchVO {
    private Integer progress;//进度
    private Integer prj_base_info_id;//

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    @Override
    public String toString() {
        return "PrjLogSearchVO{" +
                "progress=" + progress +
                ", prj_base_info_id=" + prj_base_info_id +
                '}';
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
