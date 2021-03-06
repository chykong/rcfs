package com.balance.prj.model;

import java.util.Date;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
public class PrjGroup {

    private int id;//
    private int prj_base_info_id;//项目
    private String prj_name;//项目名称
    private String section_id;//标段
    private String section_name;//标段名
    private String name;//
    private int total_homes;// 标段总户数
    private Date last_modified_at;  //最后更改时间
    private String last_modified_by;  //最后更改人
    private Date created_at;  //创建时间
    private String created_by;  //创建人

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getPrj_name() {
        return prj_name;
    }

    public void setPrj_name(String prj_name) {
        this.prj_name = prj_name;
    }

    @Override
    public String toString() {
        return "PrjSection{" +
                "id=" + id +
                ", prj_base_info_id=" + prj_base_info_id +
                ", name='" + name + '\'' +
                ", total_homes=" + total_homes +
                ", last_modified_at=" + last_modified_at +
                ", last_modified_by='" + last_modified_by + '\'' +
                ", created_at=" + created_at +
                ", created_by='" + created_by + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(int prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_homes() {
        return total_homes;
    }

    public void setTotal_homes(int total_homes) {
        this.total_homes = total_homes;
    }

    public Date getLast_modified_at() {
        return last_modified_at;
    }

    public void setLast_modified_at(Date last_modified_at) {
        this.last_modified_at = last_modified_at;
    }

    public String getLast_modified_by() {
        return last_modified_by;
    }

    public void setLast_modified_by(String last_modified_by) {
        this.last_modified_by = last_modified_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
