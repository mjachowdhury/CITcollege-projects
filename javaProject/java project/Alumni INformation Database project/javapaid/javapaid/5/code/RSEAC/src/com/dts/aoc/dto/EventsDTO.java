package com.dts.aoc.dto;

import java.sql.Date;


public class EventsDTO {

	//instance variables
	
	private int eventid;
	private String eventname;
	private String eventdate;
	private String eventtime;
	private String venue;
	private String description;
	private Date eventdate1;
	private String status;
	/**
	 * @return the eventid
	 */
	public int getEventid() {
		return eventid;
	}
	/**
	 * @param eventid the eventid to set
	 */
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	/**
	 * @return the eventname
	 */
	public String getEventname() {
		return eventname;
	}
	/**
	 * @param eventname the eventname to set
	 */
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	/**
	 * @return the eventdate
	 */
	public String getEventdate() {
		return eventdate;
	}
	/**
	 * @param eventdate the eventdate to set
	 */
	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}
	/**
	 * @return the eventtime
	 */
	public String getEventtime() {
		return eventtime;
	}
	/**
	 * @param eventtime the eventtime to set
	 */
	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}
	/**
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}
	/**
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the eventdate1
	 */
	public Date getEventdate1() {
		return eventdate1;
	}
	/**
	 * @param eventdate1 the eventdate1 to set
	 */
	public void setEventdate1(Date eventdate1) {
		this.eventdate1 = eventdate1;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
