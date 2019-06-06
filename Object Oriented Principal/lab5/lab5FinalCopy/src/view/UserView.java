package view;

import java.util.Scanner;
import controller.BookAndLecturerController;
import controller.FileStorage;
import exception.MyCustomException;
import model.Lecturer;
import model.Repository;

public class UserView {

	private static final int ADD_LECTURER = 1;
	private static final int FIND_LECTURER = 2;
	private static final int ADD_BOOK = 3;
	private static final int REMOVE_BOOK = 4;
	private static final int SEARCH_BOOK = 5;
	private static final int CALCULATE_YEARLY_BOOK_PAYMENT = 6;
	private static final int SHOW_ALL_THE_BOOK = 7;
	private static final int DISPLAY_ALL_LECTURER = 8;
	private static final int DISPLAY_ALL_LECTURER_WITH_BOOK = 9;
	private static final int SAVE = 10;
	private static final int EXIT = 0;
	//Declaring Class variable
	private BookAndLecturerController bl;
	private UserMenu userMenu;
	private FileStorage fileStorage;
	private Scanner keyboard;
	
	int userChoice;

	Repository repo = Repository.getRepository();
	//Constructor
	public UserView() {
		this.bl = new BookAndLecturerController();
		this.userMenu = new UserMenu();
		this.fileStorage = new FileStorage();
		fileStorage.restoreRepository();
		keyboard = new Scanner(System.in);
	}

	public void userMenu() {
		System.out.println("\t**********WELCOME TO THE PROGRMM.*****************");
		userMenu.menu();
		userChoice = keyboard.nextInt();

		while (userChoice != EXIT) {

			switch (userChoice) {

			case ADD_LECTURER: {
				System.out.println("\tYOU CHOOSE TO ADD LECTURER TO THE SYSTEM.");
				System.out.println("\t-----------------------------------------");

				System.out.println("PLEASE ENTER THE NAME: ");
				while (!keyboard.hasNext("[A-Za-z]+")) {//if user pass wrong value it will continue loop until gets right 
					System.out.println("Wrong Input!! Please Enter Character.");
					keyboard.next();
					System.out.println("PLEASE ENTER THE NAME:");
				}
				String name = keyboard.next();

				System.out.println("PLEASE ENTER THE ID : ");
				while (!keyboard.hasNextInt()) {//if user pass wrong value it will continue loop until gets right 
					System.out.println("Wrong Input!! Please Enter Integer value.");
					keyboard.next();
					System.out.println("PLEASE ENTER THE ID:");
				}
				int id = keyboard.nextInt();

				this.bl.addLecturer(name, id);//adding lecturer
				this.fileStorage.save();//saving to the file and storing them lecturer repo
				System.out.println("\tLECTURER ADDED SUCCESSFULLY IN THE SYSTEM.");
				System.out.println("\t*****************************************\n");			
				break;
			}

			case FIND_LECTURER: {
				System.out.println("YOU HAVE CHOOSE TO FIND LECTURERS\n");
				if (bl.getNoOfLecturer() == 0) {
					System.out.println("NO LECTURER FOUND IN THE SYSTEM. PLEASE ADD LECTURER.\n");
				} else {
					try {
						System.out.println("PLEASE ENTER THE LECTURER ID:");
						while (!keyboard.hasNextInt()) {// if user pass wrong value it will continue loop until gets right																																										
							System.out.println("Wrong Input!! Please Enter Integer value.");
							keyboard.next();
							System.out.println("PLEASE ENTER THE ID:");
						}
						int id = keyboard.nextInt();
						this.bl.searchLecturerByID(id);// calling search
														// lecturer method
					} catch (MyCustomException ex) {
						System.out.println(ex);
					}
				}
				break;
			}

			case ADD_BOOK: {
				System.out.println("\tWE HAVE CHOOSE TO ADD BOOK INTO THE SYSTEM.");
				System.out.println("\t-------------------------------------------\n");
				if (bl.getNoOfLecturer() == 0) {
					System.out.println("NO LECTURER IN THE SYSTEM...PLEASE ADD LECTURER INTO THE SYSTEM.\n");
				} else {
					try {
						System.out.println("CHOOSE LECTURER TO ADD BOOK:\n");
						this.bl.displayAllTheLecturers();
					} catch (MyCustomException ex) {
						System.out.println(ex);
					}
					System.out.println("ENTER LECTURER ID:");
					while (!keyboard.hasNextInt()) {//if user pass wrong value it will continue loop until gets right 
						System.out.println("Wrong Input!! Please Enter Integer value.");
						keyboard.next();
						System.out.println("PLEASE ENTER THE ID:");
					}
					int LecturerId = keyboard.nextInt();
					Lecturer selectedLecturer = repo.theLecturer.get(LecturerId - 1);

					keyboard.nextLine();
					System.out.println("PLEASE ENTER THE TITLE OF THE BOOK: ");
					while (!keyboard.hasNext("[A-Za-z]+")) {//if user pass wrong value it will continue loop until gets right 
						System.out.println("Wrong Input!! Please Enter Character.");
						keyboard.next();
						System.out.println("PLEASE ENTER THE TITLE OF THE BOOK :");
					}
					String title = keyboard.nextLine();

					System.out.println("PLEASE ENTER THE ISBN NUMBER: ");
					while (!keyboard.hasNextInt()) {//if user pass wrong value it will continue loop until gets right 
						System.out.println("Wrong Input!! Please Enter Integer value.");
						keyboard.next();
						System.out.println("PLEASE ENTER THE ISBN NUMBER:");
					}
					int ISBN = keyboard.nextInt();

					keyboard.nextLine();
					System.out.println("PLEASE ENTER THE BOOK AUTHOR NAME :");
					while (!keyboard.hasNext("[A-Za-z]+")) {//if user pass wrong value it will continue loop until gets right 
						System.out.println("Wrong Input!! Please Enter Character.");
						keyboard.next();
						System.out.println("PLEASE ENTER THE BOOK AUTHOR NAME:");
					}
					String author = keyboard.nextLine();

					System.out.println("PLEASE ENTER THE BOOK PRICE :");
					while (!keyboard.hasNextDouble()) {//if user pass wrong value it will continue loop until gets right 
						keyboard.next();
						System.out.println("WRONG INPUT!! PLEASE ENTER DOUBLE VALUE.");
						System.out.println("PLEASE ENTER BOOK PRICE :");
					}
					double price = keyboard.nextDouble();

					this.bl.addBook(selectedLecturer, title, ISBN, author, price);//Adding book and invoking method
					this.fileStorage.save();//saving its the file and book repo
					System.out.println("\tBOOK ADDED SUCCESSFULLY IN THE SYSTEM.");
					System.out.println("\t************************************\n");
				}
				break;
			}

			case REMOVE_BOOK: {
				System.out.println("YOU HAVE CHOOSE TO REMOVE THE BOOK");
				if (bl.getNoOfBook() == 0) {
					System.out.println("NO BOOK FOUND.\n");
				} else {
					try {
						System.out.println("PLEASE ENTER THE ISBN NUMBER:");
						while (!keyboard.hasNextInt()) {//if user pass wrong value it will continue loop until gets right 
							System.out.println("Wrong Input!! Please Enter Integer value.");
							keyboard.next();
							System.out.println("PLEASE ENTER THE ISBN NUMBER:");
						}
						int iSBN = keyboard.nextInt();

						System.out.println("PLEASE ENTER THE AUTHOR NAME:");
						while (!keyboard.hasNext("[A-Za-z]+")) {//if user pass wrong value it will continue loop until gets right 
							System.out.println("Wrong Input!! Please Enter Character.");
							keyboard.next();
							System.out.println("PLEASE ENTER THE BOOK AUTHOR NAME:");
						}
						String author = keyboard.next();

						this.bl.removeBookFromSystem(iSBN, author);//invoking remove method for book
						System.out.println("\tBOOK REMOVED SUCCESSFULLY FROM THE SYSTEM");
						System.out.println("\t****************************************\n");
					
					} catch (MyCustomException ex) {
						System.out.println(ex);
					}
				}
				break;
			}

			case SEARCH_BOOK: {
				System.out.println("YOU HAVE CHOOSE TO FIND A BOOK.\n");
				if (bl.getNoOfBook() == 0) {
					System.out.println("NO BOOK FOUND.\n");
				} else {
					try {
						System.out.println("ENTER THE BOOK ISBN NUMBER:");
						while (!keyboard.hasNextInt()) {// if user pass wrong value it will	 continue loop until gets right
							System.out.println("Wrong Input!! Please Enter Integer value.");
							keyboard.next();
							System.out.println("PLEASE ENTER THE ISBN NUMBER:");
						}
						int iSBN = keyboard.nextInt();

						this.bl.searchBookByISBN(iSBN);// calling search book
														// method
					} catch (MyCustomException ex) {
						System.out.println(ex);
					}
				}
				break;
			}

			case CALCULATE_YEARLY_BOOK_PAYMENT: {
				System.out.println("\tYOU HAVE CHOOSE TO SEE THE TOTAL BOOK PAYMENT.");
				System.out.println("\t----------------------------------------------");
				try {
					System.out.println("TOTAL PAID :" + this.bl.calculateTotalBookPayment());//calling total book price method
				} catch (Exception e) {
					System.out.println("NO BOOK ADDED INTO THE SYSTEM. NO PRICE!!!\n");
				}
				break;
			}

			case SHOW_ALL_THE_BOOK: {
				System.out.println("\tYOU HAVE CHOOSE THE DISPLAY ALL THE BOOKS.");
				System.out.println("\t------------------------------------------");
				if (bl.getNoOfBook() == 0) {
					System.out.println("NO BOOK INTO THE SYSTEM.\n");
				} else {
					try {
						this.bl.displayAllTheBooks();//invoking display all the book method
					} catch (MyCustomException ex) {
						System.out.println(ex);
					}
				}
				break;
			}

			case DISPLAY_ALL_LECTURER: {
				System.out.println("\tYOU CHOOSE TO SEE ALL THE LECTURERS.");
				System.out.println("\t------------------------------------");
				if (bl.getNoOfLecturer() == 0) {
					System.out.println("NO LECTURER FOUND\n");
				} else {
					try {
						this.bl.displayAllTheLecturers();
					} catch (MyCustomException ex) {
						System.out.println(ex);
					}
				}
				break;
			}

			case DISPLAY_ALL_LECTURER_WITH_BOOK: {
				System.out.println("\tYOU CHOOSE TO SEE ALL THE LECTURERS");
				System.out.println("\t-----------------------------------");
				if (bl.getNoOfLecturer() == 0) {
					System.out.println("NO LECTURER FOUND\n");
				} else {
					this.bl.displayLecturerAndBookDetails();// invoking display all the lecturer method
				}
				break;
			}

			case SAVE: {
				this.fileStorage.save();//invoking saving method
				break;
			}
			/*
			 * case EXIT:{ this.bl.onExit(); break; }
			 */
			default:
				System.out.println("Choice must be between (0 - 10) integer number.");
			}
			userMenu.menu();
			userChoice = keyboard.nextInt();

		}
		System.out.println("\tWE HAVE SELECTED TO EXIT THE PROGRAM.");
		System.out.println("\t\tTHANK YOU FOR USING.");
		System.out.println("\t\t\tGOOD BYE");
		System.out.println("\t-------------------------------------");
	}
}
