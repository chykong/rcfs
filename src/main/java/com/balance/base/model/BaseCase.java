package com.balance.base.model;

import java.util.Date;

/**
 * Author  刘凯
 * Date  2017/6/10.
 */
public class BaseCase {
    private int id;
    private String title;  //项目名称
    private String content;  //项目介绍
    private Date last_modified_at;  //最后更改时间
    private String last_modified_by;  //最后更改人
    private Date created_at;  //创建时间
    private String created_by;  //创建人

    @Override
    public String toString() {
        return "BaseCase{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
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
