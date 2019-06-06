package BankAccount;

public class BankAccount {

	String cuntomerName = "Blank";
	String address = "Blank";
	int telephone = 0;
	boolean student = false;
	double balance = 0.0;
	double overDraftLimit = 0.0;
	
	//setting account student or not for student
	public void setAStudent(){
		student = true;
	}
	
	public void setNotAStudent(){
		student = false;
	}
	
	/*
	 * this method for customer payIn
	 */
	public void payIn(double amount){
		balance = balance + amount;
	}
	
	/*
	 * This method will return available balance
	 */
	public double returnBalance(){
		return balance;
	}
	/*
	 * This method will return total balance available
	 */
	public double cashAvailable(){
		double answar = 0.0;
		answar = balance + overDraftLimit;
		return answar;
	}

	@Override
	public String toString() {
		return "BankAccount [cuntomerName=" + cuntomerName + ", address=" + address + ", telephone=" + telephone
				+ ", student=" + student + ", balance=" + balance + ", overDraftLimit=" + overDraftLimit + "]";
	}
	
	
	
}
