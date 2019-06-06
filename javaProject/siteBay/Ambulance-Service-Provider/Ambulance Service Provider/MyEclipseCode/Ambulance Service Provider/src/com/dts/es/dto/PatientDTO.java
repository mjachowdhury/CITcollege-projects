package com.dts.es.dto;
import java.sql.*;

public class PatientDTO {
	private int pid; 	
	private String firstname;
	private String lastname;
	private String address;
	private String dob;
	private Date dob1;
	private String city;
	private String state;
	private String location;
	private String jdate;
	private Date jdate1;
	private String phone;
	private String height;
	private String weight;
	private String type;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setJdate(String jdate) {
	this.jdate = jdate;
	}
	public String getJdate()
	{
		 return jdate;
		
	}
	public Date getJdate1() {
		return jdate1;
	}
	public void setJdate1(Date jdate1) {
		this.jdate1 = jdate1;
	}
	public Date getDob1() {
		return dob1;
	}
	public void setDob1(Date dob1) {
		this.dob1 = dob1;
	}
}
