package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Dentist extends Person implements Serializable {
	private String username, password;
	Dentist dName;
	private ArrayList<Patient> patients;

	// private Controller controller;
	private ArrayList<Invoice> invoice;
	private ArrayList<Payment> payment;
	private ArrayList<Procedure> procedure;
	public ArrayList<Patient> patientList;

	public Dentist() {
		patients = new ArrayList<>();
		invoice = new ArrayList<>();
		procedure = new ArrayList<>();

		patientList = new ArrayList<>();
		// controller = new Controller();
	}

	public Dentist(String username, String name, String address, String password) {
		super(name, address);
		this.username = username;
		this.password = password;
		patients = new ArrayList<>();
	}

	public Dentist(Dentist dentist, ArrayList<Dentist> dentistList) {
		this.dName = dentist;
	}

	public Dentist(String name, ArrayList<Dentist> dentistList) {
		this.username = name;

	}

	
	
	
	public Dentist getdName() {
		return dName;
	}

	public void setdName(Dentist dName) {
		this.dName = dName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addPatient(Patient p) {
		patients.add(p);
	}

	public void removePatient(int i) {
		if ((i > -1) && (i < patients.size()))
			patients.remove(i);
	}

	public void remPatientByName(String name) {
		for (int i = 0; i < patients.size(); i++)
			if (getPatient(i).getName().equals(name))
				patients.remove(i);
	}

	public Patient getPatient(int i) {
		return this.getPatientList().get(i);

		/*
		 * if ((i > -1) && (i < patients.size())) return patients.get(i); return
		 * null;
		 */
	}

	public Invoice getInvoice(int i) {
		if ((i > -1) && (i < invoice.size()))
			return invoice.get(i);
		return null;
	}

	public Procedure getProcedure(int i) {
		if ((i > -1) && (i < procedure.size()))
			return procedure.get(i);
		return null;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public ArrayList<Patient> getPatientList() {
		ArrayList<Patient> patientList = new ArrayList<>();
		Iterator<Patient> tempList = this.patients.iterator();

		if (tempList.next() != null) {
			// dentistList.add(new Dentist(tempList.get(i), this.dentistList));
			patientList.add(new Patient());
		}
		// ArrayList<String> tempList = (ArrayList<String>)
		// this.patients.iterator();

		for (int i = 0; i < patientList.size(); i++) {
			patientList.add(new Patient().getPatientName());
		}
		return patientList;
	}

	public ArrayList<Invoice> getInvoices() {
		return invoice;
	}

	public ArrayList<Procedure> getProcedures() {
		return procedure;
	}

	public Invoice getInvoice(int patNum, int invNum) {
		return getPatient(patNum).getInvoice(invNum);
	}

	public ArrayList<Procedure> getProcedureList(int patIndex, int invIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getProceduresI();
	}

	public ArrayList<Payment> getPaymentList(int patIndex, int invIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getIn_paymentList();
	}

	public Procedure getProcedure(int patIndex, int invIndex, int procIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getProcedure(procIndex);
	}

	public Payment getPayment(int patIndex, int invIndex, int payIndex) {
		return getPatient(patIndex).getInvoice(invIndex).getPayment(payIndex);
	}

	@Override
	public String toString() {
		return "Dentist Username =" + username + 
				"\nPatients =" + patients + 
				"\nInvoice =" + invoice + 
				"\nPayment =" + payment + 
				"\nProcedure=" + procedure;
	}

}
