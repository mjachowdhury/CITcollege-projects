package model;

import java.io.Serializable;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */
public class Guest implements Serializable {

	private static final long serialVersionUID = 1L;
	// Field variable
	private String name;
	private String email;
	private boolean lecturer;
	private int noOfNights = 0;
	private String contactNumber;

	// Constructor
	public Guest(String name, String email, int noOfNights, String contactNumber) {
		setName(name);
		setEmail(email);
		setLecturerEmail(email);
		setNoOfNights(noOfNights);
		setContactNubmer(contactNumber);
	}

	// Setter and Getter
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

	public void setContactNubmer(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/*
	 * This method will figure out either lecturer or student based on email
	 * address if email contains @cit then its lecturer or @mycit.ie then its
	 * student
	 */
	public void setLecturerEmail(String email) {
		if (email.split("@")[1].equals("mycit.ie")) {
			lecturer = false;
		} else {
			lecturer = true;
		}
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public int getNoOfNights() {
		return noOfNights;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public boolean isLecturer() {
		return this.lecturer;
	}

	public String toString() {
		return this.getName() + " " + this.getEmail() + " " + this.getContactNumber() + " " + this.getNoOfNights();
	}

}
