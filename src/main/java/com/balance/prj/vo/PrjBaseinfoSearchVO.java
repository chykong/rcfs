package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
public class PrjBaseinfoSearchVO extends PageSearchVO {
    private String prj_name;//项目名称

    /**
     * 模糊查询用到
     * @return
     */
    public String getPrj_name_str() {
        return "%" + prj_name + "%";
    }

    @Override
    public String toString() {
        return "PrjBaseinfoSearchVO{" +
                "prj_name='" + prj_name + '\'' +
                '}';
    }

    public String getPrj_name() {
        return prj_name;
    }

    public void setPrj_name(String prj_name) {
        this.prj_name = prj_name;
    }
}
