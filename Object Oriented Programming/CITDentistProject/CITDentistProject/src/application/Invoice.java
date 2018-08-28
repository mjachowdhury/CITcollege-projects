package application;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice implements Serializable {
	
	private Invoice invoice;
	static int COUNTER = 110;
	private final int invoiceNo;
	private double invoiceAmt;
	private LocalDate date;
	private boolean isPaid;
	private ArrayList<Procedure> in_procList;
	private ArrayList<Payment> in_paymentList;

	public Invoice() {
		invoiceNo = COUNTER++;
		in_procList = new ArrayList<>();
		in_paymentList = new ArrayList<>();
	}

	public Invoice(LocalDate date) {
		invoiceNo = COUNTER++;
		in_procList = new ArrayList<>();
		in_paymentList = new ArrayList<>();
		this.date = date;
	}

	public void addProcedure(Procedure p) {
		in_procList.add(p);
	}

	public void removeProcedure(int i) {
		if ((i > -1) && (i < in_procList.size()))
			in_procList.remove(i);
	}

	public Procedure getProcedure(int i) {
		if ((i > -1) && (i < in_procList.size()))
			return in_procList.get(i);
		return null;
	}

	public void getProcedures() {
		for (int i = 0; i < in_procList.size(); i++) {
			System.out.println(in_procList.get(i));
		}
		//return in_procList;
	}
	
	public ArrayList<Procedure> getProceduresI() {
		for (int i = 0; i < in_procList.size(); i++) {
			System.out.println(in_procList.get(i));
		}
		return in_procList;
	}

	public void addPayment(Payment p) {
		in_paymentList.add(p);
	}

	public void removePayment(int i) {
		if ((i > -1) && (i < in_paymentList.size()))
			in_paymentList.remove(i);
	}

	public Payment getPayment(int i) {
		if ((i > -1) && (i < in_paymentList.size()))
			return in_paymentList.get(i);
		return null;
	}

	public void getPayments() {
		for (int i = 0; i < in_paymentList.size(); i++) {
			System.out.println(in_paymentList.get(i));
		}
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public Invoice getInvoiceNumber(){
		return invoice;
	}
	
	
	public double getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(double invoiceAmt) {

		for (int i = 0; i < in_procList.size(); i++) {
			invoiceAmt += in_procList.get(i).getProcCost();
		}

		this.invoiceAmt = invoiceAmt;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public ArrayList<Procedure> getIn_procList() {
		return in_procList;
	}

	public ArrayList<Payment> getIn_paymentList() {
		return in_paymentList;
	}

	public String toString() {
		return Integer.toString(getInvoiceNo());
	}

	public void print() {
		System.out.println(toString());
	}

	public String toString1() {
		return "Invoice =" + invoice + 
				"\nInvoiceNo =" + invoiceNo + 
				"\nInvoiceAmount =" + invoiceAmt + 
				"\nDate =" + date + 
				"\nIsPaid =" + isPaid + 
				"\nInvoice_procList =" + in_procList + 
				"\nInvoice_paymentList=" + in_paymentList;
	}
	
	
	
}