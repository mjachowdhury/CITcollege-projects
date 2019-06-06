package BankAccount;

public class BankAccountMain {

	public static void main(String [] args){
		BankAccount customerAccount1 = new BankAccount();
		BankAccount customerAccount2 = new BankAccount();
		
		customerAccount1.balance = 456.00;
		customerAccount2.balance = 789.00;
		
		customerAccount1.setAStudent();
		customerAccount2.setNotAStudent();
		
		if(customerAccount1.student == true){
			System.out.println("Account 1 is belong to a Student");
		}
		else{
			System.out.println("Account 1 is does not belong to a student.");
		}
		
		//paying a fix amount
		customerAccount1.payIn(500.00);
		System.out.println("Customer account 1 Blance is now :"+customerAccount1.balance);
		
		double aCheque = 45.50;
		customerAccount2.payIn(aCheque);//paying in via another variable
		System.out.println("Custoerm balance is now customer 2 :"+customerAccount2.balance);
		
	}
}
