package model;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */
import java.io.Serializable;
import database.DBConnection;

public class Payment implements Serializable {

	/**
	 * Default serialVersionUID = 1L;
	 */
	private static final long serialVersionUID = 1L;

	// Field variables

	private int paymentNo;
	private double paymentAmt;
	private String date;
	private DBConnection db;

	// Constructor
	public Payment(int payNum, DBConnection database) {
		this.db = database;
		this.paymentNo = payNum;
		this.paymentAmt = this.db.getDoubleWithInt("paymentAmount", "Payments", "paymentNumber", this.getPaymentNo());
		this.date = this.db.getStringDataWithInt("paymentDate", "Payments", "paymentNumber", this.getPaymentNo());
	}

	// Getter and Setter
	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public String getPaymentDate() {
		return this.date;
	}

	// toString method
	public String toString() {
		return "Payment#\t: " + this.getPaymentNo() + "\nAmount\t\t: " + this.getPaymentAmt() + "\nDate\t\t: "
				+ this.getPaymentDate() + "\n\n";
	}

	public void print() {
		System.out.println(this);
	}
}
