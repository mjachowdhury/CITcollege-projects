package com.dts.es.dto;

public class PatientDischargeDTO {
	private int pid;
	private String state;
	private String dischargeDate;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

}
