package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
public class PrjGroupSearchVO extends PageSearchVO {
    private Integer prj_base_info_id;//项目id
    private Integer section_id;//标段id

    @Override
    public String toString() {
        return "PrjGroupSearchVO{" +
                "prj_base_info_id=" + prj_base_info_id +
                ", section_id=" + section_id +
                '}';
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }
}
