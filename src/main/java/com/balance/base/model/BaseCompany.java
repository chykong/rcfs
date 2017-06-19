package com.balance.base.model;

import java.util.Date;

/**
 * Author 孔垂云 Date 2017/6/3.
 */
public class BaseCompany {
	private int id;
	private String name; // 名称
	private String type; // 类型
	private Date last_modified_at; // 最后更改时间
	private String last_modified_by; // 最后更改人
	private Date created_at; // 创建时间
	private String created_by; // 创建人
	private int prj_base_info_id;// 项目id

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

}
