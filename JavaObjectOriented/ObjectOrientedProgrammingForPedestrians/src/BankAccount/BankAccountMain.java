package BankAccount;

public class BankAccountMain {

	public static void main(String [] args){
		BankAccount customerAccount1 = new BankAccount();
		BankAccount customerAccount2 = new BankAccount();
		
		customerAccount1.balance = 456.00;
		customerAccount2.balance = 789.00;
		
		customerAccount1.overDraftLimit = 200.00;
		
		//customerAccount1.setAStudent();
		//customerAccount2.setNotAStudent();
		
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
		
		
		double bal;
		bal = customerAccount1.returnBalance();
		System.out.println("New Balance for customer1 is now:"+bal);
		System.out.println("Cash availabe :"+customerAccount1.cashAvailable());
		
		//increasing overDraft limit
		System.out.println();
		System.out.println("Increasing over draft limit by 200 euros.");
		customerAccount1.overDraftLimit = 400.00;
		System.out.println("Cash available now :"+ customerAccount1.cashAvailable());
		
		System.out.println();
		System.out.println("Customer account 1 details.");
		System.out.println(customerAccount1.toString());
		
		System.out.println();
		System.out.println("Customer account 2 details.");
		System.out.println(customerAccount2.toString());
	}
}
