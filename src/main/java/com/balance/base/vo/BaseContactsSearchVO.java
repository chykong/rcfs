package com.balance.base.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
public class BaseContactsSearchVO extends PageSearchVO {
    private Integer type;////类型，1管理公司人员2项目参与人员

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BaseContactsSearchVO{" +
                "type=" + type +
                '}';
    }
}
