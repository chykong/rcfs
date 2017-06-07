package com.balance.prj.model;
/**
 * @author 刘凯
 * @date 2017年6月4日
 */

import java.util.Date;

public class PrjReport {
    private int id;//
    private int prj_base_info_id;//项目id
    private String content;//内容 
    private String title;//标题
    private String file_path;//文件路径
    private String file_name;//文件名
    private int progress;//项目进度
    private Date last_modified_at;//最后修改时间
    private String last_modified_by;//最后修改人
    private String created_by;//创建人
    private Date created_at;//创建时间

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "PrjMeeting{" +
                "id=" + id +
                ", prj_base_info_id=" + prj_base_info_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", progress=" + progress +
                ", last_modified_at=" + last_modified_at +
                ", last_modified_by='" + last_modified_by + '\'' +
                ", created_by='" + created_by + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
