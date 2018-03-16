package com.balance.prj.model;

import java.util.Date;

/**
 * Created by dsy on 2018/3/3.
 * 选房顺序号
 */
public class PrjSelected {
    private int id;
    private String map_id;  //编号
    private int selected_code;  //选房号
    private String house_type;  //房屋类型
    private String land_type;  //土地类型
    private int project_id;  //项目id
    private int compensation_type;  //选房方式  1-面积   2-人口
    private Date selected_at;  //选房时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public int getSelected_code() {
        return selected_code;
    }

    public void setSelected_code(int selected_code) {
        this.selected_code = selected_code;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getLand_type() {
        return land_type;
    }

    public void setLand_type(String land_type) {
        this.land_type = land_type;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Date getSelected_at() {
        return selected_at;
    }

    public void setSelected_at(Date selected_at) {
        this.selected_at = selected_at;
    }

    public int getCompensation_type() {
        return compensation_type;
    }

    public void setCompensation_type(int compensation_type) {
        this.compensation_type = compensation_type;
    }
}
