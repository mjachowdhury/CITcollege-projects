package BankAccount;

public class BankAccount {

	String customerName = "Blank";
	String address = "Blank";
	int telephone = 0;
	boolean student = false;
	double balance = 0.0;
	double overDraftLimit = 0.0;
	
	//setting account student or not for student
	/*public void setAStudent(){
		student = true;
	}
	
	public void setNotAStudent(){
		student = false;
	}*/
	
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
		
		String studentStatus;
		if(student==true){
			studentStatus = "Account holder is a student.";
		}
		else{
			studentStatus = "Account holder is not a student.";
		}
		return "Bank account Summary.\n"+
				"Name : "+customerName+"\n"+
				"Address : "+address+"\n"+
				"Telephone : "+telephone+"\n"+
				"Account Balance : "+balance+"\n"+
				studentStatus;
	}
	
	
	
}
