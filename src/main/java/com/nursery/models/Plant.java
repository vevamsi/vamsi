package com.nursery.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pname;
	private String descr;
	@ManyToOne
	@JoinColumn(name = "catid")
	private Category category;
	private int price;
	private String photo;
	public Plant() {}
	public Plant(int id) {this.id=id;}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	@Override
	public String toString() {
		return "Plant [id=" + id + ", pname=" + pname + ", descr=" + descr + ", category=" + category + ", price="
				+ price + ", photo=" + photo + "]";
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
