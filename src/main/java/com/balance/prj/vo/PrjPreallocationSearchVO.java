package com.balance.prj.vo;

import com.balance.util.page.PageSearchVO;

import java.util.List;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息查询表
 */
public class PrjPreallocationSearchVO extends PageSearchVO {
    private Integer base_info_id; // 项目id
    private String map_id; //编号
    private Integer land_status; //土地性质
    private Integer house_status; //房屋性质
    private String location;//房屋坐落

    private String host_name;
    private Integer status;  //查询状态
    private List<Integer> statusList;  //用于查询多个状态
    private String town;  //镇
    private String village;  //村
    private String section;  //标段
    private String groups;  //组别
    private Integer export_status; // 导出状态  10-未签约  20-已签约

    private Integer user_id;  //用户id
    private Integer is_section;  //是否标段过滤
    private Integer is_group;  //是否组别过滤

    private String term;//API接口里面按照户名或编号查询

    @Override
    public String toString() {
        return "PrjPreallocationSearchVO{" +
                "base_info_id=" + base_info_id +
                ", map_id='" + map_id + '\'' +
                ", land_status=" + land_status +
                ", house_status=" + house_status +
                ", location='" + location + '\'' +
                ", host_name='" + host_name + '\'' +
                ", status=" + status +
                ", statusList=" + statusList +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", section='" + section + '\'' +
                ", groups='" + groups + '\'' +
                ", export_status=" + export_status +
                ", user_id=" + user_id +
                ", is_section=" + is_section +
                ", is_group=" + is_group +
                ", term='" + term + '\'' +
                '}';
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

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

    public String getLocation_param() {
        return "%" + location + "%";
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getHost_name() {
        return host_name;
    }

    public String getHost_name_param() {
        return "%" + host_name + "%";
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getExport_status() {
        return export_status;
    }

    public void setExport_status(Integer export_status) {
        this.export_status = export_status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getIs_section() {
        return is_section;
    }

    public void setIs_section(Integer is_section) {
        this.is_section = is_section;
    }

    public Integer getIs_group() {
        return is_group;
    }

    public void setIs_group(Integer is_group) {
        this.is_group = is_group;
    }

    public Integer getHouse_status() {
        return house_status;
    }

    public void setHouse_status(Integer house_status) {
        this.house_status = house_status;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
