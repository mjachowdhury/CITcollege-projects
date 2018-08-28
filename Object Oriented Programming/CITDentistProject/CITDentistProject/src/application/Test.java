package application;

import java.util.Date;

public class Test {

	public static void main(String[] args) {

		/*// Adding patients to the dentist
		Patient patient1 = new Patient("Patrick", "Cork");
		Patient patient2 = new Patient("Chun", "Hongkong");
		Patient patient3 = new Patient("Allen", "Tiperrary");
		Patient patient4 = new Patient("Aisling", "Limerick");

		Dentist dentist = new Dentist();
		dentist.addPatient(patient1);
		dentist.addPatient(patient2);
		dentist.addPatient(patient3);

		//Outputting all the patients of the dentist
		System.out.println("*************************************************");
		System.out.println("These are all the patients of my dentist object.");
		dentist.getPatients();

		// Outputting one of the patients of the dentist
		System.out.println("*************************************************");
		System.out.println("This is the result when I only want to output one patient from the list.\n");
		System.out.println(dentist.getPatient(0));

		// Removing one of the patient by patient no and name
		dentist.removePatient(1);
		dentist.remPatientByName("Allen");

		//There should only be one patient remaining called Patrick after removing the other 2 from the code above.
		System.out.println("*************************************************");
		System.out.println("This is the result after I removed 2 patients from the list");
		dentist.getPatients();

		// Adding invoices to the patient
		Date today = new Date();
		Invoice invoice1 = new Invoice(today);
		Invoice invoice2 = new Invoice(today);
		Invoice invoice3 = new Invoice(today);
		patient1.addInvoice(invoice1);
		patient1.addInvoice(invoice2);
		patient1.addInvoice(invoice3);

		// Outputting all of the patient's invoice
		System.out.println("\n*************************************************");
		System.out.println("These are all the invoices for my patient 1 object.");
		patient1.getInvoices();

		// Outputting one of the patient's invoice
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I only want to output one invoice from the patient.");
		System.out.println(patient1.getInvoice(1));
		
		// Removing one of the patient's invoice
		patient1.removeInvoice(0);
		
		// Outputting the patient's invoices after removing one of them
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I only want to output all the invoices after removing one of them.");
		patient1.getInvoices();
		
		
		// Adding a procedure to one of the patient's invoice
		Procedure proc1 = new Procedure("Cleaning", 50);
		Procedure proc2 = new Procedure("Extraction", 80);
		patient1.getInvoice(1).addProcedure(proc1);
		patient1.getInvoice(1).addProcedure(proc2);
		
		// Outputting the procedures from the patient 1 second invoice
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I want to output all of the patient's procedure from one of their invoice.");
		patient1.getInvoice(1).getProcedures();
		
		patient1.getInvoices();
		
		// Outputting one procedure from the patient 1 second invoice
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I want to output one of the patient's procedure from one of their invoice.");
		System.out.println(patient1.getInvoice(1).getProcedure(1));
		
		// Removing one of the patient's procedure from the second invoice
		patient1.getInvoice(1).removeProcedure(0);
		
		// Outputting all the procedures of the patient 1 from his second invoice
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I want to output all of the patient's procedure from one of their invoice after removing one.");
		patient1.getInvoice(1).getProcedures();
		
		// Adding payment to the patient's payment list
		Payment pay1 = new Payment(50);
		Payment pay2 = new Payment(80);
		patient1.getInvoice(1).addPayment(pay1);
		patient1.getInvoice(1).addPayment(pay2);
		
		// Outputting all the payments done by patient 1
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I want to output all of the patient's payment.");
		patient1.getInvoice(1).getPayments();
		
		// Outputting one of the payments done by patient 1
		System.out.println("\n*************************************************");
		System.out.println("This is the result when I want to output one of the patient's payment.");
		System.out.println(patient1.getInvoice(1).getPayment(1));
		*/
	}
}