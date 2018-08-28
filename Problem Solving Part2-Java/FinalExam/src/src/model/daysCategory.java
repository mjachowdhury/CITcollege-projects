package model;

import java.io.Serializable;

	//variable
	public class daysCategory implements Serializable {
	private Long categoryId = System.currentTimeMillis();//Tempory Id generated 
	private String name;
	
	//constructor
	public daysCategory(String name) {
		this.name = name;
	}

	public daysCategory(Long categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public daysCategory() {
		 
	}

	//getter and setter
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
