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

public class Invoice implements Serializable {

	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;

	// Field variables

	private int invoiceNo;
	private double invoiceAmt;
	private String date;
	private boolean isPaid;

	// Instance variable of DBConnection
	private DBConnection db;

	// Constructor
	public Invoice(int invNum, DBConnection database) {
		this.db = database;
		this.date = this.db.getStringDataWithInt("invoiceDate", "Invoices", "invoiceNumber", invNum);
		this.invoiceNo = invNum;
		this.isPaid = this.db.getBooleanWithInt("invoicePaid", "Invoices", "invoiceNumber", invNum);
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * This method will return total invoice amount
	 * 
	 * @return
	 */
	public double getInvoiceAmt() { // needs doing
		invoiceAmt = 0;
		for (int i = 0; i < this.getProcList().size(); i++) {
			invoiceAmt += this.getProcedure(i).getProcCost();
		}
		return invoiceAmt;
	}

	public void setInvoiceAmt(double invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}

	public String getInvoiceDate() {
		return this.date.toString();
	}

	public boolean isPaid() {
		return this.isPaid = this.db.getBooleanWithInt("invoicePaid", "Invoices", "invoiceNumber", this.getInvoiceNo());
	}

	public void setPaid(boolean isPaid) {
		this.db.updatePaid(isPaid, this.getInvoiceNo());
	}

	/**
	 * This method will return total procedure list
	 * 
	 * @return
	 */
	public ArrayList<Procedure> getProcList() {
		ArrayList<Procedure> procList = new ArrayList<>();
		ArrayList<Integer> tempList = this.db.getIntDataColumnWithWhere("procedureNumber", "Procedures",
				"procedureInvoice", this.getInvoiceNo());
		for (int i = 0; i < tempList.size(); i++) {
			procList.add(new Procedure(tempList.get(i).intValue(), this.db));
		}
		return procList;
	}

	/**
	 * This method will return total payment list
	 * 
	 * @return
	 */
	public ArrayList<Payment> getPaymentList() {
		ArrayList<Payment> paymentList = new ArrayList<>();
		ArrayList<Integer> tempList = this.db.getIntDataColumnWithWhere("paymentNumber", "Payments", "paymentInvoice",
				this.getInvoiceNo());
		for (int i = 0; i < tempList.size(); i++) {
			paymentList.add(new Payment(tempList.get(i).intValue(), this.db));
		}
		return paymentList;
	}

	public boolean isInProcListEmpty() {
		return this.db.isTableEmpty("Procedures");
	}

	/**
	 * THis method will add the procedure
	 * 
	 * @param type
	 */

	public void addProcedure(ProcedureType type) {
		this.db.addToProcedureTable(type.getName(), type.getCost(), this.getInvoiceNo());
	}

	/**
	 * This method will remove the procedure
	 * 
	 * @param procNum
	 */
	public void removeProcedure(int procNum) {
		if (this.db.isTableEmpty("Procedures")) {
			System.out.println("Invoice procedure list is empty");
		} else {
			this.db.removeRowWithInt("Procedures", "procedureNumber", procNum);
		}
	}

	public boolean isInPaymentListEmpty() {
		return this.db.isTableEmpty("Payments");
	}

	/**
	 * This method will add payment to the patient list
	 * 
	 * @param newPaymentAmt
	 * @param newPaymentDate
	 */
	public void addPayment(double newPaymentAmt, Date newPaymentDate) {
		this.db.addToPaymentTable(newPaymentAmt, newPaymentDate, this.getInvoiceNo());
	}

	/**
	 * This method will remove the payment from the patrient list
	 * 
	 * @param payNum
	 */
	public void removePayment(int payNum) {
		if (this.db.isTableEmpty("Payments")) {
			System.out.println("Invoice payment list is empty");
		} else {
			this.db.removeRowWithInt("Payments", "paymentNumber", payNum);
		}
	}

	/**
	 * This method will calculate the total payment for the patient
	 * 
	 * @return
	 */
	public double getPaymentTotal() {
		double total = 0.0;
		for (int i = 0; i < this.getPaymentList().size(); i++) {
			total += this.getPaymentList().get(i).getPaymentAmt();
		}
		return total;
	}

	/**
	 * This method will calculate to total cost for the procedures
	 * 
	 * @return
	 */
	public double getCostTotal() {
		double total = 0.0;
		for (int i = 0; i < this.getProcList().size(); i++) {
			total += this.getProcList().get(i).getProcCost();
		}
		return total;
	}

	public String getPatientName() {
		String patient;
		patient = "get patient name on invoice";
		return patient;
	}

	public Payment getPayment(int index) {
		return this.getPaymentList().get(index);
	}

	public Procedure getProcedure(int index) {
		return this.getProcList().get(index);
	}

	/**
	 * This method will return procedure by its index
	 * 
	 * @param index
	 * @return
	 */
	public Procedure findProcByNum(int index) {
		Procedure proc = null;
		for (int i = 0; i < this.getProcList().size(); i++) {
			if (index == this.getProcedure(i).getProcNo()) {
				proc = this.getProcedure(i);
			}
		}
		return proc;
	}

	/**
	 * This method will return procedure index
	 * 
	 * @param num
	 * @return
	 */
	public int findProcIndex(int num) {
		int index = -1;
		for (int i = 0; i < this.getProcList().size(); i++) {
			if (num == this.getProcList().get(i).getProcNo()) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * This method will return payment by its index
	 * 
	 * @param index
	 * @return
	 */
	public Payment findPayByNum(int index) {
		Payment pay = null;
		for (int i = 0; i < this.getPaymentList().size(); i++) {
			if (index == this.getPayment(i).getPaymentNo()) {
				pay = this.getPayment(i);
			}
		}
		return pay;
	}

	/**
	 * This method will return payment index
	 * 
	 * @param num
	 * @return
	 */
	public int findPayIndex(int num) {
		int index = -1;
		for (int i = 0; i < this.getPaymentList().size(); i++) {
			if (num == this.getPaymentList().get(i).getPaymentNo()) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * This method will return total customer amount paid
	 * 
	 * @return
	 */
	public Double getTotalPaid() {
		double paid = 0;
		for (int i = 0; i < this.getPaymentList().size(); i++) {
			paid += this.getPaymentList().get(i).getPaymentAmt();
		}
		return paid;
	}

	/**
	 * this method will return if any amount left from invoice amount and from
	 * total customer paid
	 * 
	 * @return
	 */

	public Double calcAmountRemaining() {
		double bill = 0;
		bill = this.getInvoiceAmt() - this.getTotalPaid();
		return bill;
	}

	/**
	 * This method will check whether patient paid total amount or not
	 */
	public void paidCheck() {
		if (this.calcAmountRemaining() <= 0) {
			this.setPaid(true);
		} else {
			this.setPaid(false);
		}
	}

	// toString method
	public String toString() {
		return "\nInvoice#\t: " + this.invoiceNo + "\nDate\t\t: " + this.date.toString() + "\nAmount\t\t: "
				+ this.invoiceAmt + "\nRemaining\t: " + this.calcAmountRemaining() + "\nPaid?\t\t  " + this.isPaid
				+ "\n\n";
	}

	public void print() {
		System.out.println(this);
	}

}
