package com.balance.base.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Created by 杨康康 on 2018/3/3.
 */
public class BaseHouseSearchVO extends PageSearchVO {
    private String  name;//户型标题
    private Integer prjBaseInfoId;//项目id
    public String getName_str() {
        return "%" + name + "%";
    }

    @Override
    public String toString() {
        return "BaseHouseSearchVO{" +
                "name='" + name + '\'' +
                '}';
    }

    public Integer getPrjBaseInfoId() {
        return prjBaseInfoId;
    }

    public void setPrjBaseInfoId(Integer prjBaseInfoId) {
        this.prjBaseInfoId = prjBaseInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
