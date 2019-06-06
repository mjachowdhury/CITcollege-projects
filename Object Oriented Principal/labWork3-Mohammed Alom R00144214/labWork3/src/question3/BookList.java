
/*
 * Part b)	In order to write the code for the class below you have to create another class.  
What class must you create? 
Once known create the UML for this dummy class and include only methods you need. No need for gets or sets. 
Now create that dummy class and the following class below.
 */

// In order to write the code for the class I have to create BookList class
package question3;

import java.util.ArrayList;

public class BookList {

	private int MAX_NO_OF_BOOKS;

	ArrayList<Object> books = new ArrayList<Object>();

	/**
	 * @param mAX_NO_OF_BOOKS
	 * @param books
	 */
	public BookList(int mAX_NO_OF_BOOKS) {

		MAX_NO_OF_BOOKS = mAX_NO_OF_BOOKS;

	}

	public void addBook(Book b) {

		if (MAX_NO_OF_BOOKS >= 15) {
			System.out.println("CAN NOT ADD ANY MORE BOOK.");
		} else {
			books.add(b);
		}
	}

	/*
	 * private ArrayList<Book> books;
	 * 
	 * public BookList(int maxNumberOfBooks){ this.books = new
	 * ArrayList<Book>(); this.MAX_NO_OF_BOOKS = maxNumberOfBooks; }
	 * 
	 * public void addBook(Book b){
	 * 
	 * if(MAX_NO_OF_BOOKS >=15){
	 * System.out.println("CAN NOT ADD ANY MORE BOOK."); } else{ books.add(b); }
	 * }
	 * 
	 *//**
		 * @return the books
		 */
	/*
	 * public ArrayList<Book> getBooks() { return books; }
	 * 
	 *//**
		 * @param books
		 *            the books to set
		 */
	/*
	 * public void setBooks(ArrayList<Book> books) { this.books = books; }
	 * 
	 *//**
		 * @return the maxNoOfBooks
		 *//*
		 * public int getMaxNoOfBooks() { return MAX_NO_OF_BOOKS; }
		 */

}
