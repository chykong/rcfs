package com.balance.base.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dsy on 2017/8/29.
 * 安置房详情Modal
 */
public class BasePlacementDetail {

    private int id;
    private String build_code;
    private String map_code;
    private Integer house_type; //户型
    private String house_type_name;  //户型名称
    private String house_url;  //户型图片路径
    private Float area;
    private Integer floor;//楼层
    private Integer bool_tt;//是否通透，0否1是
    private Integer bool_mc;//是否名厨
    private Integer bool_mw;//是否明卫
    private Integer is_select;//是否选房
    private String map_id;//选房编号
    private Date selected_at;//选房时间
    private BigDecimal money;  //金额
    private String note; ///备注

    private Integer prj_base_info_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuild_code() {
        return build_code;
    }

    public void setBuild_code(String build_code) {
        this.build_code = build_code;
    }

    public String getMap_code() {
        return map_code;
    }

    public void setMap_code(String map_code) {
        this.map_code = map_code;
    }

    public Integer getHouse_type() {
        return house_type;
    }

    public void setHouse_type(Integer house_type) {
        this.house_type = house_type;
    }

    public String getHouse_type_name() {
        return house_type_name;
    }

    public void setHouse_type_name(String house_type_name) {
        this.house_type_name = house_type_name;
    }

    public String getHouse_url() {
        return house_url;
    }

    public void setHouse_url(String house_url) {
        this.house_url = house_url;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBool_tt() {
        return bool_tt;
    }

    public void setBool_tt(Integer bool_tt) {
        this.bool_tt = bool_tt;
    }

    public Integer getBool_mc() {
        return bool_mc;
    }

    public void setBool_mc(Integer bool_mc) {
        this.bool_mc = bool_mc;
    }

    public Integer getBool_mw() {
        return bool_mw;
    }

    public void setBool_mw(Integer bool_mw) {
        this.bool_mw = bool_mw;
    }

    public Integer getIs_select() {
        return is_select;
    }

    public void setIs_select(Integer is_select) {
        this.is_select = is_select;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public Date getSelected_at() {
        return selected_at;
    }

    public void setSelected_at(Date selected_at) {
        this.selected_at = selected_at;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }
}
