package com.balance.prj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by dsy on 2017/12/7.
 * 产权人户籍关系
 */
public class PreallocationRela {

    private int id;
    private String map_id;// '产权人编号';
    private String name;// '姓名';
    private String id_no;// '身份证号';
    private String other_card_no;// '其他证号';
    private String birthday;
    private String telphone;// '联系方式';
    private String job_company;//  '工作单位';
    private Integer marriage_status;// '婚姻状态  1-已婚、2-未婚、3-再婚、4-离异、5-丧偶';
    private String host_relation;// '与产权人关系';
    private String householder_relation;// '与户主关系';
    private Integer cog_status; // '认定情况  1-认定 2-非认定';
    private Integer show_status; // '公示情况  1-已公示  2-未公示';
    private Double az_area;// '安置面积 0/50/100，如果认定默认50，如果不认定默认0';
    private String sex;// '性别  ';
    private Integer is_sign;// '是否在册  0-不在册  1-在册';
    private String note; // '备注';
    private String age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_at;
    private String created_by;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date last_modified_at;
    private String last_modified_by;
    private int prj_base_info_id;// '项目id';
    private Integer type;//类型   1  在册   2 非在册

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getOther_card_no() {
        return other_card_no;
    }

    public void setOther_card_no(String other_card_no) {
        this.other_card_no = other_card_no;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getJob_company() {
        return job_company;
    }

    public void setJob_company(String job_company) {
        this.job_company = job_company;
    }

    public Integer getMarriage_status() {
        return marriage_status;
    }

    public void setMarriage_status(Integer marriage_status) {
        this.marriage_status = marriage_status;
    }

    public String getHost_relation() {
        return host_relation;
    }

    public void setHost_relation(String host_relation) {
        this.host_relation = host_relation;
    }

    public String getHouseholder_relation() {
        return householder_relation;
    }

    public void setHouseholder_relation(String householder_relation) {
        this.householder_relation = householder_relation;
    }

    public Integer getCog_status() {
        return cog_status;
    }

    public void setCog_status(Integer cog_status) {
        this.cog_status = cog_status;
    }

    public Integer getShow_status() {
        return show_status;
    }

    public void setShow_status(Integer show_status) {
        this.show_status = show_status;
    }

    public Double getAz_area() {
        return az_area;
    }

    public void setAz_area(Double az_area) {
        this.az_area = az_area;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(Integer is_sign) {
        this.is_sign = is_sign;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public int getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(int prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }
}
