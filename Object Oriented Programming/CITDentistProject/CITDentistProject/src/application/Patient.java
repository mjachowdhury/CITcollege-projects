package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Person implements Serializable {
	static int COUNTER = 110;
	private final int patientNo;
	private String phoneNo;
	private ArrayList<Invoice> p_invoiceList;

	Patient pName;
	Dentist dentistName;
	private ArrayList<Patient> pList;

	public Patient() {
		patientNo = COUNTER++;
		p_invoiceList = new ArrayList<>();
	}

	public Patient(String name, String address, String phoneNo) {
		super(name, address);
		patientNo = COUNTER++;
		this.phoneNo = phoneNo;
		p_invoiceList = new ArrayList<>();
	}

	public Dentist getDentistName() {
		return dentistName;
	}

	public void setDentistName(Dentist dentistName) {
		this.dentistName = dentistName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getPatientNo() {
		return patientNo;
	}

	public void addInvoice(Invoice i) {
		p_invoiceList.add(i);
	}

	public Invoice getInvoice(int i) {
		if ((i > -1) && (i < p_invoiceList.size()))
			return p_invoiceList.get(i);
		else
			return null;
	}

	public int getInvoiceSize() {
		return p_invoiceList.size();
	}

	public void removeInvoice(int i) {
		if ((i > -1) && (i < p_invoiceList.size()))
			p_invoiceList.remove(i);
	}

	public void getInvoices() {
		for (int i = 0; i < p_invoiceList.size(); i++) {
			System.out.println(p_invoiceList.get(i));
		}
	}

	public ArrayList<Invoice> getP_invoiceList() {
		return p_invoiceList;
	}
	
	/*public ArrayList<Invoice> getPatient_invoiceList() {
		return p_invoiceList;
	}*/

	public ArrayList<Invoice> getPatientInvoiceList() {
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		ArrayList<Integer> tempList = (ArrayList<Integer>) this.p_invoiceList.iterator();
		for (int i = 0; i < invoiceList.size(); i++) {
			invoiceList.add(new Invoice().getInvoiceNumber());
		}
		return invoiceList;
	}

	public String toString() {
		return String.format("Patient No:\t%s \nName:\t\t%s \nAddress:\t%s\n", patientNo, getName(), getAddress());
	}
	
	
 
	public String toString1() {
		return "Patient PatientNo =" + patientNo + 
				"\nPhoneNo =" + phoneNo + 
				"\nPatient InvoiceList =" + p_invoiceList+ 
				"\nPatient Name =" + pName + 
				"\npList=" + pList;
	}

	public void print() {
		System.out.println(toString());
	}

}
