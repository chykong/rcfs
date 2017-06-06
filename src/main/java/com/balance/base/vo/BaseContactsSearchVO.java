package com.balance.base.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
public class BaseContactsSearchVO extends PageSearchVO {
    private Integer type;////类型，1管理公司人员2项目参与人员
    private Integer prj_base_info_id;//项目
    private String name;//姓名
    private String mobile;//手机号

    @Override
    public String toString() {
        return "BaseContactsSearchVO{" +
                "type=" + type +
                ", prj_base_info_id=" + prj_base_info_id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
