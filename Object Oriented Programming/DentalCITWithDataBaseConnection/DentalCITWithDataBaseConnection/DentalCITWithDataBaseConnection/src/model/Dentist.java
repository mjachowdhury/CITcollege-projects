/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import database.DBConnection;

/**
 * This is Dentist class and its extending the Person class and its
 * Serializable.
 * 
 * @author Mohammed
 *
 */
public class Dentist extends Person implements Serializable {

	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String password;

	// Creating DBConnection instance
	private DBConnection db;

	/**
	 * First Constructor
	 */
	public Dentist() {
		super("---", "---");
		this.password = "---";

	}

	/**
	 * Second Constructor
	 * 
	 * @param newName
	 * @param database
	 */
	public Dentist(String newName, DBConnection database) {
		super(newName, database.getStringData("userAddress", "Dentists", "userName", newName));
		this.password = database.getStringData("userPassword", "Dentists", "userName", newName);
		this.db = database;

	}

	public String getAddress() {
		return this.db.getStringData("userAddress", "Dentists", "userName", this.getName());
	}

	public String getPassword() {
		return this.db.getStringData("userPassword", "Dentists", "userName", this.getName());
	}

	/**
	 * This method will return all the patient list
	 * 
	 * @return
	 */
	public ArrayList<Patient> getPatientList() {
		ArrayList<Patient> patientList = new ArrayList<>();
		ArrayList<String> tempList = this.db.getStringDataColumnWithWhere("patientName", "Patients", "patientDentist",
				this.getName());
		for (int i = 0; i < this.db.getListSizeWithString("Patients", "patientDentist", this.getName()); i++) {
			patientList.add(new Patient(tempList.get(i), this.db));
		}
		return patientList;
	}

	public boolean isPatientListEmpty() {
		return this.db.isTableEmpty("Patients");
	}

	/**
	 * this method will add the patient
	 * 
	 * @param newName
	 * @param newAddress
	 */
	public void addPatient(String newName, String newAddress) {
		this.db.addToPatientTable(newName, newAddress, this.getName());
	}

	/**
	 * This method will remove the patient from the DB
	 * 
	 * @param name
	 */
	public void removePatient(String name) {
		if (this.isPatientListEmpty()) {
			System.out.println("Patient list is empty");
		} else {
			this.db.removeRowWithString("Patients", "patientName", name);
		}
	}

	/**
	 * This method will remove the invoice
	 * 
	 * @param patIndex
	 * @param invIndex
	 */
	public void removeInvoice(int patIndex, int invIndex) {
		this.getPatient(patIndex).removeInvoice(invIndex);
	}

	public Patient getPatient(int patIndex) {
		return this.getPatientList().get(patIndex);
	}

	public Invoice getInvoice(int patNum, int invNum) {
		return getPatient(patNum).getInvoice(invNum);
	}

	public Payment getPayment(int patIndex, int invIndex, int payIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getPayment(payIndex);
	}

	public Procedure getProcedure(int patIndex, int invIndex, int procIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getProcedure(procIndex);
	}

	public ArrayList<Invoice> getInvoiceList(int patIndex) {
		return getPatient(patIndex).getPatientInvoiceList();
	}

	public ArrayList<Payment> getPaymentList(int patIndex, int invIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getPaymentList();
	}

	public ArrayList<Procedure> getProcedureList(int patIndex, int invIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getProcList();
	}

	public String toString() {
		return "\n************\n" + "Name\t: " + super.getName() + "\nAddress\t: " + super.getAddress()
				+ "\n------------\n";
	}

	public void print() {
		System.out.println(this);
	}

}

/*
 * public Invoice findInvoiceByNumber(int patIndex, int invNumber) { Invoice
 * invoice = null; ArrayList<Invoice> list = this.getInvoiceList(patIndex); for
 * (int i = 0; i < list.size(); i++) { if (invNumber ==
 * list.get(i).getInvoiceNo()) { invoice = list.get(i); } } return invoice; }
 */

/*
 * public int findInvoiceIndex(int patIndex, int invNum) { int index = -1;
 * ArrayList<Invoice> list = this.getInvoiceList(patIndex); for (int i = 0; i <
 * list.size(); i++) { if (invNum == list.get(i).getInvoiceNo()) { index = i; }
 * } return index; }
 */
