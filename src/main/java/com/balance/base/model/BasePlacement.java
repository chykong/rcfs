package com.balance.base.model;

/**
 * Created by  杨康康 on 2017/8/29.
 * 安置房概况model
 */
public class BasePlacement {
    private int id;
    private String content;  //内容
    private String attach_name;  //附件
    private String attach_path;  //附件
    private int prj_base_info_id; //项目id

    public int getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(int prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach_name() {
        return attach_name;
    }

    public void setAttach_name(String attach_name) {
        this.attach_name = attach_name;
    }

    public String getAttach_path() {
        return attach_path;
    }

    public void setAttach_path(String attach_path) {
        this.attach_path = attach_path;
    }

    @Override
    public String toString() {
        return "BasePlacement{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", attach_name='" + attach_name + '\'' +
                ", attach_path='" + attach_path + '\'' +
                ", prj_base_info_id=" + prj_base_info_id +
                '}';
    }
}
