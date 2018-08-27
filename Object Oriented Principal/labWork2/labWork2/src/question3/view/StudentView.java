package question3.view;

import java.util.Scanner;

import question3.controller.StudentController;

public class StudentView {
	private static final int ADD_A_STUDENT = 1;
	private static final int REMOVE_A_STUDENT = 2;
	private static final int DISPLAY_ALL_STUDENT = 3;
	private static final int EXIT = 4;
	private Scanner keyboard;

	private int menuChoice = 0;

	private StudentController theStudentController;

	public StudentView() {
		// Creating StudentController object
		this.theStudentController = new StudentController();
		// always initialize variables so no null pointer exceptions
		keyboard = new Scanner(System.in);
	}

	public void startUserInterface() {

		printMenu();
		while (!keyboard.hasNextInt()) {
			keyboard.next();

			System.out.println("Wrong Choice!!");
			System.out.println("Please Enter (1 - 4) to Continue..");
			printMenu();
		}
		menuChoice = keyboard.nextInt();

		while (menuChoice != EXIT) {

			switch (menuChoice) {

			case ADD_A_STUDENT: {
				System.out.println("\tWE HAVE SELECTED TO ADD STUDENT DETAILS.");
				System.out.println("\t----------------------------------------");
				System.out.println();

				String firstName;
				String lastName;
				double mark = 0;

				System.out.println("PLEASE ENTER FIRST NAME :");
				while (!keyboard.hasNext("[A-Za-z]+")) {
					keyboard.next();
					System.out.println("WRONG INPUT!! PLEASE ENTER CHARACTER.");
					System.out.println("PLEASE ENTER YOUR FIRST NAME :");
				}
				firstName = keyboard.next();

				System.out.println("PLEASE ENTER LAST NAME :");
				while (!keyboard.hasNext("[A-Za-z]+")) {
					keyboard.next();
					System.out.println("WRONG INPUT!! PLEASE ENTER CHARACTER.");
					System.out.println("PLEASE ENTER LAST NAME :");
				}
				lastName = keyboard.next();

				System.out.println("PLEASE ENTER MARK :");
				while (!keyboard.hasNextDouble()) {
					keyboard.next();
					System.out.println("WRONG INPUT!! PLEASE ENTER DOUBLE VALUE.");
					System.out.println("PLEASE ENTER MARK :");
				}
				mark = keyboard.nextDouble();

				this.theStudentController.addStudent(firstName, lastName, mark);
				System.out.println("STUDENT DETAILS ENTERED SYCCESSFULLY.");
				break;
			}

			case REMOVE_A_STUDENT: {
				System.out.println("\tWE HAVE SELECTED TO REMOVE THE STUDENTS.");
				System.out.println("\t------------------------------------------");
				System.out.println();

				if (theStudentController.getNoOfStudent() == 0) {
					System.out.println("NO STUDENT INTO THE SYSTEM YET.");
					System.out.println("YOU HAVE TO ADD BEFORE YOU CAN REMOVE.");
				} else {
					String fName;
					String lName;

					System.out.println("ENTER STUDENT FRIST NAME:");
					while (!keyboard.hasNext("[A-Za-z]+")) {
						keyboard.next();
						System.out.println("WRONG INPUT!! PLEASE ENTER CHARACTER.");
						System.out.println("ENTER STUDENT FRIST NAME:");
					}
					fName = keyboard.next();

					System.out.println("ENTER STUDENT LAST NAME: ");
					while (!keyboard.hasNext("[A-Za-z]+")) {
						keyboard.next();
						System.out.println("WRONG INPUT!! PLEASE ENTER CHARACTER.");
						System.out.println("ENTER STUDENT LAST NAME: ");
					}
					lName = keyboard.next();

					this.theStudentController.removeStudent(fName, lName);
					System.out.println("STUDENT REMOVED SUCCESSFULLY FROM THE SYSTEM.");
				} // end of else
				break;
			}

			case DISPLAY_ALL_STUDENT: {
				System.out.println("\tWE HAVE SELECTED TO VIEW ALL THE STUDENTS.");
				System.out.println("\t------------------------------------------");
				System.out.println();

				if (theStudentController.getNoOfStudent() == 0) {
					System.out.println("NO STUDENT INTO THE SYSTEM YET.");
					System.out.println("YOU HAVE TO ADD STUDENT BEFORE YOU CAN DISPLAY.");
				} else {
					this.theStudentController.displayAllStudent();
				}
				break;
			}

			}// end of switch
			printMenu();
			menuChoice = keyboard.nextInt();
		} // end of while loop
		System.out.println("Good Bye");
	}// end of method

	public void printMenu() {
		System.out.println();
		System.out.println("\t\tPLEASE CHOOSE FOLLOWING OPTIONS");
		System.out.println("\tCHOICE MUST BE BETWEEN (1 - 4) INTEGER NUMBER.");
		System.out.println();
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t|(1). TO ADD A STUDENT INTO THE SYSTEM.          |");
		System.out.println("\t|(2). TO REMOVE A STUDENT FROM THE SYSTEM.       |");
		System.out.println("\t|(3). TO DISPLAY ALL THE STUDENT FROM THE SYSTEM.|");
		System.out.println("\t|(4). TO EXIT FROM THE SYSTEM.                   |");
		System.out.println("\t|------------------------------------------------|");
		System.out.println();
		System.out.println("ENTER YOUR CHOICE:");
	}

}
