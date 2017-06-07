package com.balance.base.model;

import java.util.Date;

/**
 * Author 孔垂云 Date 2017/6/3.
 */
public class BaseParticipant {
	private int type;// 类型，1管理公司人员2项目参与人员
	private int id;//

	private int prj_base_info_id;// 项目ID

	private String name;// 姓名
	private String mobile;// 手机
	private String duty;// 职务
	private String company;// 公司名称
	private String project_duty;// 职责
	private String release_date;// 发布时间
	private String groups;// 组别
	private Date last_modified_at; // 最后更改时间
	private String last_modified_by; // 最后更改人
	private Date created_at; // 创建时间
	private String created_by; // 创建人
	private String note;// 备注

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getProject_duty() {
		return project_duty;
	}

	public void setProject_duty(String project_duty) {
		this.project_duty = project_duty;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getPrj_base_info_id() {
		return prj_base_info_id;
	}

	public void setPrj_base_info_id(int prj_base_info_id) {
		this.prj_base_info_id = prj_base_info_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "BaseParticipant [type=" + type + ", id=" + id + ", prj_base_info_id=" + prj_base_info_id + ", name="
				+ name + ", mobile=" + mobile + ", duty=" + duty + ", company=" + company + ", project_duty="
				+ project_duty + ", release_date=" + release_date + ", groups=" + groups + ", last_modified_at="
				+ last_modified_at + ", last_modified_by=" + last_modified_by + ", created_at=" + created_at
				+ ", created_by=" + created_by + ", note=" + note + "]";
	}
}
