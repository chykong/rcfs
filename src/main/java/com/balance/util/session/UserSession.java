package com.balance.util.session;

import java.io.Serializable;

/**
 * 系统用户Session信息
 *
 * @author 孔垂云
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1629527703944211785L;
    private int user_id;//用户id
    private String user_ip;//用户IP

    private String user_name;//用户名  即登录账号
    private String realname;//真实姓名
    private int role_id;//角色id
    private String role_name;//角色名称

    private int current_project_id;//当前项目id
    private int current_land_status;//当前土地性质
    private int current_building_type;//当前房屋类型

    private int type;//类型

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCurrent_project_id() {
        return current_project_id;
    }

    public void setCurrent_project_id(int current_project_id) {
        this.current_project_id = current_project_id;
    }

    public int getCurrent_land_status() {
        return current_land_status;
    }

    public void setCurrent_land_status(int current_land_status) {
        this.current_land_status = current_land_status;
    }

    public int getCurrent_building_type() {
        return current_building_type;
    }

    public void setCurrent_building_type(int current_building_type) {
        this.current_building_type = current_building_type;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "user_id=" + user_id +
                ", user_ip='" + user_ip + '\'' +
                ", user_name='" + user_name + '\'' +
                ", realname='" + realname + '\'' +
                ", role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", current_project_id=" + current_project_id +
                ", current_land_status=" + current_land_status +
                ", current_building_type=" + current_building_type +
                ", type=" + type +
                '}';
    }
}
