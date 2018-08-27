package question1;

import java.util.ArrayList;

public class TestEmployee {

	public static void main(String[] args) {
		/*
		 * Creating full and part time employee object.
		 */
		FullTimeEmployee fullTimeEmployee1 = new FullTimeEmployee("Mohammed", "Alom", "PPS-111-222", 3000.00, true);
		FullTimeEmployee fullTimeEmployee2 = new FullTimeEmployee("Chowdhury", "Hero", "PPS-333-444", 5000.00, true);
		PartTimeEmployee partTimeEmployee1 = new PartTimeEmployee("Jason", "Howard", "PPS-555-666", 12.50, 25, true);
		PartTimeEmployee partTimeEmployee2 = new PartTimeEmployee("Sara", "Brosnan", "PPS-777-888", 14.50, 35, true);

		/*
		 * Adding full and part time employee into ArrayList
		 */
		ArrayList<Employee> employee = new ArrayList<Employee>();

		employee.add(fullTimeEmployee1);
		employee.add(fullTimeEmployee2);
		
		employee.add(partTimeEmployee1);
		employee.add(partTimeEmployee2);
		
		/*
		 * Iterating all the employee
		 */
		System.out.println("Full & Part Time Employees Details:");
		System.out.println("==================================\n");
		for (Employee currentEmployee : employee) {
			System.out.println(currentEmployee);// invokes toString
			System.out.println();
		}

	}

}
