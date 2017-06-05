package com.balance.prj.model;

import java.util.Date;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
public class PrjBaseinfo {
    private int id;//id
    private String prj_name;// 项目名称
    private String introduction;//项目描述
    private String flow;//工作流程
    private String architecture;// 组织架构
    private Date last_modified_at;  //最后更改时间
    private String last_modified_by;  //最后更改人
    private Date created_at;  //创建时间
    private String created_by;  //创建人
    private String type;//类型 1 项目简介   2 工作流程 3 组织架构

    @Override
	public String toString() {
		return "PrjBaseinfo [id=" + id + ", prj_name=" + prj_name + ", introduction=" + introduction + ", flow=" + flow
				+ ", architecture=" + architecture + ", last_modified_at=" + last_modified_at + ", last_modified_by="
				+ last_modified_by + ", created_at=" + created_at + ", created_by=" + created_by + ", type=" + type
				+ "]";
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrj_name() {
        return prj_name;
    }

    public void setPrj_name(String prj_name) {
        this.prj_name = prj_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
