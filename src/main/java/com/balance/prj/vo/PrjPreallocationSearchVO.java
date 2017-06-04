package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息查询表
 */
public class PrjPreallocationSearchVO extends PageSearchVO {
    private Integer base_info_id; // 项目id
    private String map_id; //编号
    private Integer land_status; //土地性质

    public Integer getBase_info_id() {
        return base_info_id;
    }

    public void setBase_info_id(Integer base_info_id) {
        this.base_info_id = base_info_id;
    }

    public String getMap_id_param() {
        return "%" + map_id + "%";
    }
    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public Integer getLand_status() {
        return land_status;
    }

    public void setLand_status(Integer land_status) {
        this.land_status = land_status;
    }
}
