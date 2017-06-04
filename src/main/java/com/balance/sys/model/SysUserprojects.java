package com.balance.sys.model;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
public class SysUserprojects {
    private int user_id;//
    private int prj_base_info_id;//项目id

    public SysUserprojects() {
    }

    public SysUserprojects(int user_id, int prj_base_info_id) {
        this.user_id = user_id;
        this.prj_base_info_id = prj_base_info_id;
    }

    @Override
    public String toString() {
        return "SysUserprojects{" +
                "user_id=" + user_id +
                ", prj_base_info_id=" + prj_base_info_id +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(int prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }
}
