package com.balance.base.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  李晓明
 * Date  2017/6/11.
 */
public class BaseMessageSearchVO extends PageSearchVO {
	private String id;
	private String title;
	private String status;
	private String send_at;
	private String send_by;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSend_at() {
		return send_at;
	}
	public void setSend_at(String send_at) {
		this.send_at = send_at;
	}
	public String getSend_by() {
		return send_by;
	}
	public void setSend_by(String send_by) {
		this.send_by = send_by;
	}
	@Override
	public String toString() {
		return "BaseMessage [id=" + id + ", title=" + title + ", status=" + status + ", send_at=" + send_at
				+ ", send_by=" + send_by + "]";
	}
	

}
