package com.balance.base.model;

import java.util.Date;

/**
 * Author  刘凯
 * Date  2017/6/10.
 */
public class BaseMessageread {
    private int id;
    private int message_id;
    private int user_id;
    private int status;//是否已读   0未读，1已读
    private Date read_at;  //接收时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getRead_at() {
		return read_at;
	}
	public void setRead_at(Date read_at) {
		this.read_at = read_at;
	}
	@Override
	public String toString() {
		return "BaseMessageread [id=" + id + ", message_id=" + message_id + ", user_id=" + user_id + ", status="
				+ status + ", read_at=" + read_at + "]";
	}

  
}
