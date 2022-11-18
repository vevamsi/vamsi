package com.nursery.dtos;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.models.Plant;

public class ProductDTO {
	private String pname;
	private String descr;
	private int category;
	private int price;
	private String photo;
	private MultipartFile pic;
	

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


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
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


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public MultipartFile getPic() {
		return pic;
	}


	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}


	public static Plant toEntity(ProductDTO dto) {
		Plant entity=new Plant();
		BeanUtils.copyProperties(dto, entity,"pic");		
		return entity;
	}
}
