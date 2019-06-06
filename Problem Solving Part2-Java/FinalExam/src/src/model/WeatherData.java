package model;

import java.io.Serializable;
import java.util.Date;

public class WeatherData implements Serializable {
	
	//variable
	private Long categoryId;
	private Float hours;
	private Date date;
	private String remark;
	
	//constructor
	public WeatherData() {		 
	}


	public WeatherData(Long categoryId, Float hours, Date date, String remark) {
		this.categoryId = categoryId;
		this.hours = hours;
		this.date = date;
		this.remark = remark;
	}
	
	public WeatherData( Float hours, Date date, String remark) {
		 
		this.hours = hours;
		this.date = date;
		this.remark = remark;
	}


	//getter and setter
	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public Float getHours() {
		return hours;
	}


	public void setHours(Float hours) {
		this.hours = hours;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

}
