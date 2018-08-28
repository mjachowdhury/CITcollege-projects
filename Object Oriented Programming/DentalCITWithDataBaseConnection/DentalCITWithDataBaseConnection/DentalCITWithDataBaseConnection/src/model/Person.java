package model;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import java.io.Serializable;

abstract public class Person implements Serializable {

	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;

	// Constructor

	public Person(String newName, String newAddress) {
		this.name = newName;
		this.address = newAddress;
	}

	// Getter and Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
