package model;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import java.io.Serializable;

public class ProcedureType implements Serializable {
	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;

	// Field Variables
	private String name;
	private Double cost;

	// Constructor
	public ProcedureType(String newName, Double newCost) {
		this.name = newName;
		this.cost = newCost;
	}

	// Getter and Setter
	public String getName() {
		return this.name;
	}

	public Double getCost() {
		return this.cost;
	}

	// toString method
	public String toString() {
		return "" + name + " " + cost;
	}
}
