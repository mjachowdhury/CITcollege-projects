package com.dts.aoc.dto;

import java.sql.Connection;
import java.sql.Date;

import com.dts.core.util.DataObject;

public class VacancyDTO extends DataObject{
	
	//instance variables
	
	private int vacancyid;
	private String companyname;
	private String companyprofile;
	private String vacancyposition;
	private String jobdesc;
	private String category;
	private String location;
	private String desiredprofile;
	private String desiredexp;
	private String createddate;
	private Date createddate1;
	private String expirydate;
	private Date expirydate1;
	private String contactperson;
	private String designation;
	private String phoneno;
	private String email;
	

	/**
	 * @return the vacancyid
	 */
	public int getVacancyid() {
		return vacancyid;
	}


	/**
	 * @param vacancyid the vacancyid to set
	 */
	public void setVacancyid(int vacancyid) {
		this.vacancyid = vacancyid;
	}


	/**
	 * @return the companyname
	 */
	public String getCompanyname() {
		return companyname;
	}


	/**
	 * @param companyname the companyname to set
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	/**
	 * @return the companyprofile
	 */
	public String getCompanyprofile() {
		return companyprofile;
	}


	/**
	 * @param companyprofile the companyprofile to set
	 */
	public void setCompanyprofile(String companyprofile) {
		this.companyprofile = companyprofile;
	}


	/**
	 * @return the vacancyposition
	 */
	public String getVacancyposition() {
		return vacancyposition;
	}


	/**
	 * @param vacancyposition the vacancyposition to set
	 */
	public void setVacancyposition(String vacancyposition) {
		this.vacancyposition = vacancyposition;
	}


	/**
	 * @return the jobdesc
	 */
	public String getJobdesc() {
		return jobdesc;
	}


	/**
	 * @param jobdesc the jobdesc to set
	 */
	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * @return the desiredprofile
	 */
	public String getDesiredprofile() {
		return desiredprofile;
	}


	/**
	 * @param desiredprofile the desiredprofile to set
	 */
	public void setDesiredprofile(String desiredprofile) {
		this.desiredprofile = desiredprofile;
	}


	/**
	 * @return the createddate
	 */
	public String getCreateddate() {
		return createddate;
	}


	/**
	 * @param createddate the createddate to set
	 */
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}


	/**
	 * @return the expirydate
	 */
	public String getExpirydate() {
		return expirydate;
	}


	/**
	 * @param expirydate the expirydate to set
	 */
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}


	/**
	 * @return the contactperson
	 */
	public String getContactperson() {
		return contactperson;
	}


	/**
	 * @param contactperson the contactperson to set
	 */
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}


	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}


	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}


	/**
	 * @return the phoneno
	 */
	public String getPhoneno() {
		return phoneno;
	}


	/**
	 * @param phoneno the phoneno to set
	 */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the createddate1
	 */
	public Date getCreateddate1() {
		return createddate1;
	}


	/**
	 * @param createddate1 the createddate1 to set
	 */
	public void setCreateddate1(Date createddate1) {
		this.createddate1 = createddate1;
	}


	/**
	 * @return the expirydate1
	 */
	public Date getExpirydate1() {
		return expirydate1;
	}


	/**
	 * @param expirydate1 the expirydate1 to set
	 */
	public void setExpirydate1(Date expirydate1) {
		this.expirydate1 = expirydate1;
	}


	/**
	 * @return the desiredexp
	 */
	public String getDesiredexp() {
		return desiredexp;
	}


	/**
	 * @param desiredexp the desiredexp to set
	 */
	public void setDesiredexp(String desiredexp) {
		this.desiredexp = desiredexp;
	}

}
