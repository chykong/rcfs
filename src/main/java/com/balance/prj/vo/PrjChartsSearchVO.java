package com.balance.prj.vo;

/**
 * Created by dsy on 2017/6/7.
 * 图表查询VO
 */
public class PrjChartsSearchVO {
    private Integer prj_base_info_id;//项目id
    private String town;  //镇
    private String village;  //村
    private String date; //日期

    private Integer search_type; // 查询类型 1-入户 2-签约 3-交房  4-放款

    private String search_date_type;  //查询类型
    private Integer type;//1按户数2按占地面积3按建筑面积
    private String current_land_name;//当前土地性质

    @Override
    public String toString() {
        return "PrjChartsSearchVO{" +
                "prj_base_info_id=" + prj_base_info_id +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", date='" + date + '\'' +
                ", search_type=" + search_type +
                ", search_date_type='" + search_date_type + '\'' +
                ", type=" + type +
                ", current_land_name='" + current_land_name + '\'' +
                '}';
    }

    public String getCurrent_land_name() {
        return current_land_name;
    }

    public void setCurrent_land_name(String current_land_name) {
        this.current_land_name = current_land_name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearch_date_type() {
        return search_date_type;
    }

    public void setSearch_date_type(String search_date_type) {
        this.search_date_type = search_date_type;
    }

    public Integer getSearch_type() {
        return search_type;
    }

    public void setSearch_type(Integer search_type) {
        this.search_type = search_type;
    }
}
