package com.nursery.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Admin {

	@Id
	private String userid;
	@NotBlank(message="admin password must not be blank")
	private String pwd;
	@NotBlank(message="admin name must not be blank")
	private String uname;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@Override
	public String toString() {
		return "Admin [userid=" + userid + ", pwd=" + pwd + ", uname=" + uname + "]";
	}
	
}
