package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

public class PrjMeetingSearchVO extends PageSearchVO {
    private Integer prj_base_info_id;//

    @Override
    public String toString() {
        return "PrjMeetingSearchVO{" +
                ", prj_base_info_id=" + prj_base_info_id +
                '}';
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

}
