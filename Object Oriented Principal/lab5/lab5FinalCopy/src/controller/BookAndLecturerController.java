package controller;

import java.util.List;

import exception.MyCustomException;
import model.*;

/**
 * This class contain most of the operation related to book and lecturer
 * <p>
 * This class prepare various method to handle the user action. This class make
 * use of <code> Repository</code> to store the data.
 * 
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/25
 */
public class BookAndLecturerController {

	/**
	 * Declare a reference of repository by calling a static method. Which
	 * return a singleton repository object.
	 */
	Repository repo = Repository.getRepository();

	/*
	 * Constructor
	 */
	public BookAndLecturerController() {
	}

	int noOfBook;
	int noOfLecturer;

	// Getter and Setter
	public int getNoOfBook() {
		return noOfBook;
	}

	public int getNoOfLecturer() {
		return noOfLecturer;
	}

	public void setNoOfBook(int noOfBook) {
		this.noOfBook = noOfBook;
	}

	public void setNoOfLecturer(int noOfLecturer) {
		this.noOfLecturer = noOfLecturer;
	}

	/*
	 * Adding lecturer method
	 * 
	 * @param nameFromView, idFromView
	 */
	public void addLecturer(String nameFromView, int idFromView) {
		Lecturer l = new Lecturer(nameFromView, idFromView);
		this.repo.theLecturer.add(l);
		noOfLecturer++;
	}

	/*
	 * Adding book method
	 * 
	 * @param Lecturer and Book constructor
	 */
	public void addBook(Lecturer lec, String titleFromView, int iSBNFromView, String authorFromView,
			double priceFromView) {
		Book b = new Book(titleFromView, iSBNFromView, authorFromView, priceFromView);
		this.repo.theBook.add(b);
		noOfBook++;
		lec.addBook(b);
	}

	/*
	 * This method will display Lecturer and his/her book details. First calling
	 * the Lecturer repository then looping through the ArrayList items then
	 * calling Lecturer and storing i index to lecturer variable then printing
	 * its value After that looping through Lecture and getting all the items by
	 * calling getList() which is Book then printing all the book details which
	 * is belong to the Lecturer.
	 */
	public void displayLecturerAndBookDetails() {
		List<Lecturer> l = repo.theLecturer;
		for (int i = 0; i < l.size(); i++) {
			Lecturer lecturer = l.get(i);
			System.out.println("ID - " + lecturer.getID() + ". LECTURER NAME : " + lecturer.getName() + "\n"
					+ "HIS/HER BOOK DETAILS :\n");
			for (int x = 0; x < lecturer.getList().size(); x++) {
				System.out.println((x + 1) + ". " + lecturer.getList().get(x).toString());
			}
		}
	}

	/*
	 * This method first calling Lecturer repo. then looping through all the
	 * lecturer and storing all the Lecturer in the lecturer variable then
	 * displaying all the lecturer from the system
	 */
	public void displayAllTheLecturers() throws MyCustomException {
		List<Lecturer> l = repo.theLecturer;
		for (int i = 0; i < l.size(); i++) {
			Lecturer lecturer = l.get(i);
			System.out.println((i + 1) + ".LECTURER NAME: " + lecturer.getName() + "\tID :" + lecturer.getID() + "\n");
		}
		throw new MyCustomException("NO LECTURER FOUND IN THE SYSTEM.\n");
	}

	/*
	 * This method first calling Book repo. then looping through all the book
	 * and storing all the Book in the book variable then displaying all the
	 * book from the system
	 */
	public void displayAllTheBooks() throws MyCustomException {
		List<Book> b = repo.theBook;
		for (int i = 0; i < b.size(); i++) {
			Book book = b.get(i);
			System.out.println((i + 1) + ". BOOK TITLE :" + book.getTitle() + "\nISBN :" + book.getISBN() + "\nAUTHOR :"
					+ book.getAuthor() + "\nPRICE :" + book.getPrice() + "\n");
		}
		throw new MyCustomException("NO LECTURER BOOK IN THE SYSTEM.\n");
	}

	/*
	 * This method will search lecture by his/her ID passing by user
	 * @param idFromView First getting all the lecturer from its repo. then
	 * looping through all the items if user passing id and lecturer id is equal
	 * then it will show the Lecturer details.
	 */
	public void searchLecturerByID(int idFromView) throws MyCustomException {
		List<Lecturer> lect = repo.theLecturer;
		for (Lecturer l : lect) {
			if (l.getID() == idFromView) {
				System.out.println("LECTURER NAME :" + l.getName() + "\nID :" + l.getID() + "\n");
				return;
			}
		} // end of loop
		
			throw new MyCustomException("NO LECTURER FOUND. TYR AGAIN!!\n");
		
		//System.out.println("NO LECTURER FOUND. TYR AGAIN!!");
	}// end of method

	/*
	 * This method will search Book by ISBN passing by user
	 * @param iSBNForm View First getting all the Book from its repo. then
	 * looping through all the items if user passing iSBN and Book iSBN is equal
	 * then it will show the Book details.
	 */
	public void searchBookByISBN(int iSBNFromView)throws MyCustomException {
		List<Book> book = repo.theBook;
		for (Book b : book) {
			if (b.getISBN() == iSBNFromView) {
				System.out.println("BOOK TITLE :" + b.getTitle() + "\nISBN :" + b.getISBN() + "\nAUTHOR :"
						+ b.getAuthor() + "\nPRICE :" + b.getPrice() + "\n");
				return;
			}
		}
		throw new MyCustomException("NO BOOK FOUND. TYR AGAIN!!\n");
		//System.out.println("NO BOOK FOUND. TYR AGAIN!!");
	}

	/*
	 * This method will remove first from the Book repo. then it will remove
	 * from the Lecturer List
	 */

	public void removeBookFromSystem(int iSBNFromView, String authorFromView) throws MyCustomException{
		List<Book> book = repo.theBook;
		Book bookToBeRemoved = null;
		for (Book b : book) {
			if (b.getISBN() == iSBNFromView && b.getAuthor().equals(authorFromView)) {
				bookToBeRemoved = b;
				System.out
						.println("THIS BOOK REMOVED:" + "BOOK ISBN - " + b.getISBN() + "BOOK AUTHOR -" + b.getAuthor());
			}
		}
		if (bookToBeRemoved != null) {
			repo.theBook.remove(bookToBeRemoved);
			Lecturer lect = null;
			for (int x = 0; x < repo.theLecturer.size(); x++) {
				for (int i = 0; i < repo.theLecturer.get(x).getList().size(); i++) {
					List<Book> book1 = repo.theLecturer.get(i).getList();
					for (Book c : book1) {
						if (c.getISBN() == iSBNFromView && c.getAuthor().equals(authorFromView)) {

							lect = repo.theLecturer.get(x);
						}
					}
				}
			}
			if (lect != null)
				lect.removeBook(bookToBeRemoved);
		} else {
			throw new MyCustomException("NO BOOK FOUND. TYR AGAIN!!\n");
			//System.out.println();
		}
	}//end of method

	/*
	 * Calculating total book payment
	 */
	public double calculateTotalBookPayment() {
		List<Book> book = repo.theBook;
		double totalPaid = 0;
		try {
			for (Book bookPrice : book) {
				totalPaid += bookPrice.getPrice();
			}
		} catch (Exception e) {
			System.out.println("NO BOOK ADDED INTO THE SYSTEM. NO PRICE!!!");
		}
		return totalPaid;
	}
}// end of class
