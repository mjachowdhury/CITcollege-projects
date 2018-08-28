package model;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import database.DBConnection;

public class Patient extends Person implements Serializable {

	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;

	private int patientNumber;

	// Creating Instance variable of DBConnection
	private DBConnection db;

	// Constructor
	public Patient(String newName, DBConnection database) {
		super(newName, database.getStringData("patientAddress", "Patients", "patientName", newName));
		this.db = database;
		this.patientNumber = this.db.getIntData("patientNumber", "Patients", "patientName", newName);
	}

	// Getter and setter
	public int getPatientNumber() {
		return this.patientNumber;
	}

	public Invoice getInvoice(int invIndex) {
		return this.getPatientInvoiceList().get(invIndex);
	}

	public Payment getPayment(int invIndex, int payIndex) {
		return this.getPatientInvoiceList().get(invIndex).getPayment(payIndex);
	}

	public Procedure getProcedure(int invIndex, int procIndex) {
		return this.getPatientInvoiceList().get(invIndex).getProcedure(procIndex);
	}

	public boolean isInvoiceListEmpty() {
		return this.db.isTableEmpty("Invoices");

	}

	/**
	 * This method will add invoice with date
	 * 
	 * @param date
	 */
	public void addInvoice(Date date) {
		this.db.addToInvoiceTable(date, this.getPatientNumber());
	}

	/**
	 * This method will remove the invoice
	 * 
	 * @param invNum
	 */
	public void removeInvoice(int invNum) {
		if (this.db.isTableEmpty("Invoices")) {
			System.out.println("Patient invoice list is empty");
		} else {
			this.db.removeRowWithInt("Invoices", "invoiceNumber", invNum);
		}
	}

	/**
	 * This method will return total patient invoice list form the DB
	 * 
	 * @return
	 */
	public ArrayList<Invoice> getPatientInvoiceList() {
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		ArrayList<Integer> tempList = this.db.getIntDataColumnWithWhere("invoiceNumber", "Invoices", "invoicePatient",
				this.getPatientNumber());
		for (int i = 0; i < this.db.getListSizeWithInt("Invoices", "invoicePatient", this.getPatientNumber()); i++) {
			invoiceList.add(new Invoice(tempList.get(i).intValue(), this.db));
		}
		return invoiceList;
	}

	/**
	 * this method will calculate the remaining bill of the patient
	 * 
	 * @return remaining bill
	 */
	public double getRemainingBill() {
		double bill = 0;
		for (int i = 0; i < this.getPatientInvoiceList().size(); i++) {
			bill += this.getInvoice(i).calcAmountRemaining();
		}
		return bill;
	}

	/**
	 * THis method will find the invoice number by its index
	 * 
	 * @param index
	 * @return the invoice
	 */
	public Invoice findInvoiceByNum(int index) {
		Invoice inv = null;
		for (int i = 0; i < this.getPatientInvoiceList().size(); i++) {
			if (index == this.getInvoice(i).getInvoiceNo()) {
				inv = this.getInvoice(i);
			}
		}
		return inv;
	}

	// toString method
	public String toString() {
		return "\t*****" + "\nPatient#: " + this.patientNumber + "\nName\t: " + super.getName() + "\nAddress\t: "
				+ super.getAddress() + "\n";
	}

	public void print() {
		System.out.println(this);
	}

}
