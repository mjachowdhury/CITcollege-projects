package com.dts.es.dto;

public class HospitalizedStatusDTO {

	
	private String roomNo;
	private String discease;
	private String medicine;
	private int pid;
	private int hid;
	private int docid;
	
	public int getPid() {
	return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getHid() {
	return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getDiscease() {
		return discease;
	}
	public void setDiscease(String discease) {
		this.discease = discease;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	} 
}
