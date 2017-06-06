package com.balance.base.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
public class BasePolicySearchVO extends PageSearchVO {
    private String title;//标题模糊查询

    private Integer prj_base_info_id;//项目id

    public String getTitle_str() {
        return "%" + title + "%";
    }

    @Override
    public String toString() {
        return "BasePolicySearchVO{" +
                "title='" + title + '\'' +
                ", prj_base_info_id=" + prj_base_info_id +
                '}';
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}