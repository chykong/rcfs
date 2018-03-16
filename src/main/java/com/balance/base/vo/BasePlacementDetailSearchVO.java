package com.balance.base.vo;

/**
 * Created by dsy on 2017/8/29.
 * 安置房明细  查询VO
 */
public class BasePlacementDetailSearchVO {

    private Integer prj_base_info_id;
    private String build_code;
    private String map_code;

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public String getBuild_code() {
        return build_code;
    }

    public void setBuild_code(String build_code) {
        this.build_code = build_code;
    }

    public String getMap_code_param() {
        return "%" + map_code + "%";
    }

    public String getMap_code() {
        return map_code;
    }

    public void setMap_code(String map_code) {
        this.map_code = map_code;
    }
}
