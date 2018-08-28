package application;

import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable {
	static int COUNTER = 100;
	private final int paymentNo;
	private double paymentAmt;
	private LocalDate paymentDate;

	public Payment() {
		this.paymentNo = COUNTER++;
	}

	public Payment(double paymentAmt, LocalDate paymentDate) {
		this.paymentNo = COUNTER++;
		this.paymentAmt = paymentAmt;
		this.paymentDate = paymentDate;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate date) {
		this.paymentDate = date;
	}

	public String toString() {
		return "\napplication.Payment No:\t\t" + getPaymentNo() + "\napplication.Payment Amount:\t" + getPaymentAmt()
				+ "\napplication.Payment Date:\t" + getPaymentDate();
	}

	public void print() {
		System.out.println(toString());
	}
}
