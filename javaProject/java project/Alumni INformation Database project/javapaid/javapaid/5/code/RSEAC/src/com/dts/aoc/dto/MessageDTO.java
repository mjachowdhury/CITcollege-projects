package com.dts.aoc.dto;

import java.sql.Date;

public class MessageDTO {

	private int messageid;
	private String from;
	private String to;
	private String subject;
	private String message;
	private String senddate;
	private Date senddate1;
	/**
	 * @return the messageid
	 */
	public int getMessageid() {
		return messageid;
	}
	/**
	 * @param messageid the messageid to set
	 */
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the senddate
	 */
	public String getSenddate() {
		return senddate;
	}
	/**
	 * @param senddate the senddate to set
	 */
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	/**
	 * @return the senddate1
	 */
	public Date getSenddate1() {
		return senddate1;
	}
	/**
	 * @param senddate1 the senddate1 to set
	 */
	public void setSenddate1(Date senddate1) {
		this.senddate1 = senddate1;
	}
}
