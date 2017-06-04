package com.balance.sys.model;

import java.util.Date;

/**
 * 系统用户
 *
 * @author 孔垂云
 */
public class SysUser {
    private int id;
    private int role_id;//所属角色
    private String role_name;//角色描述
    private String username;//登录账号
    private String password;//登录密码
    private String randomcode;//随机数
    private int status;//账号状态
    private String realname;//姓名
    private String telephone;//手机号
    private Date create_date;//创建时间
    private String create_person;//创建人
    private int type;//类型

    private int current_project_id;//当前项目id
    private String current_project_name;//当前项目名称
    private int current_land_status;//当前土地性质
    private String current_land_name;//当前土地性质名称
    private int current_building_type;//当前房屋类型
    private String current_building_name;//当前房屋类型名称

    public int getCurrent_project_id() {
        return current_project_id;
    }

    public void setCurrent_project_id(int current_project_id) {
        this.current_project_id = current_project_id;
    }

    public String getCurrent_project_name() {
        return current_project_name;
    }

    public void setCurrent_project_name(String current_project_name) {
        this.current_project_name = current_project_name;
    }

    public int getCurrent_land_status() {
        return current_land_status;
    }

    public void setCurrent_land_status(int current_land_status) {
        this.current_land_status = current_land_status;
    }

    public String getCurrent_land_name() {
        return current_land_name;
    }

    public void setCurrent_land_name(String current_land_name) {
        this.current_land_name = current_land_name;
    }

    public int getCurrent_building_type() {
        return current_building_type;
    }

    public void setCurrent_building_type(int current_building_type) {
        this.current_building_type = current_building_type;
    }

    public String getCurrent_building_name() {
        return current_building_name;
    }

    public void setCurrent_building_name(String current_building_name) {
        this.current_building_name = current_building_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomcode() {
        return randomcode;
    }

    public void setRandomcode(String randomcode) {
        this.randomcode = randomcode;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_person() {
        return create_person;
    }

    public void setCreate_person(String create_person) {
        this.create_person = create_person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", randomcode='" + randomcode + '\'' +
                ", status=" + status +
                ", realname='" + realname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", create_date=" + create_date +
                ", create_person='" + create_person + '\'' +
                ", type=" + type +
                '}';
    }
}
