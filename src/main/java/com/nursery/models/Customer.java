package com.nursery.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;

@Entity
public class Customer {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@NotNull
	private String name;
	private String city;
	@Email(message="Not a valid Email Format")
	private String userid;
	@NotBlank(message="password must not be blank")
	private String pwd;
	private String phone;
	private String gender;
	
	public Customer() {}
	public Customer(int id) {
		this.id=id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", city=" + city + ", userid=" + userid + ", pwd=" + pwd
				+ ", phone=" + phone + "=" + "]";
	}
	
	
}
