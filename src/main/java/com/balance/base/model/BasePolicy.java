package com.balance.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 * 政策
 */
public class BasePolicy {
    private int id;
    private String title;  //标题
    private String content;  //内容
    private int prj_base_info_id;//项目id
    @JsonIgnore
    private Date last_modified_at;  //最后更改时间
    @JsonIgnore
    private String last_modified_by;  //最后更改人
    @JsonIgnore
    private Date created_at;  //创建时间
    @JsonIgnore
    private String created_by;  //创建人

    @Override
    public String toString() {
        return "BasePolicy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", prj_base_info_id=" + prj_base_info_id +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(int prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
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
