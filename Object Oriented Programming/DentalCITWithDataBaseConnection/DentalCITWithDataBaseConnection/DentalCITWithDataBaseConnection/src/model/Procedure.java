package model;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import java.io.Serializable;

import database.DBConnection;

public class Procedure implements Serializable {

	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;

	private int procNo;
	private String procName;
	private double procCost;
	private DBConnection db;

	// Constructor
	public Procedure(int procNum, DBConnection database) {
		this.db = database;
		this.procNo = procNum;
		this.procName = this.db.getStringDataWithInt("procedureName", "Procedures", "procedureNumber", procNum);
		this.procCost = this.db.getDoubleWithInt("procedureCost", "Procedures", "procedureNumber", procNum);
	}

	// Getter and setter
	public int getProcNo() {
		return procNo;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public double getProcCost() {
		return procCost;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	// ToString method
	public String toString() {
		return "Procedure#\t: " + this.procNo + "\nName\t\t: " + this.procName + "\nCost\t\t: " + this.procCost
				+ "\n\n";
	}

	public void print() {
		System.out.println(this);
	}

}
