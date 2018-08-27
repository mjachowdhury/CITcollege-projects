package model;

import java.io.Serializable;

public class Book implements Serializable {

	private String title;
	private int ISBN;
	private String author;
	private double price;
	private int noOfBook;
	 
	 
	/**
	 * @param title
	 * @param iSBN
	 * @param author
	 * @param price
	 */
	//Constructor
	public Book(String title, int iSBN, String author, double price) {
		this.title = title;
		this.ISBN = iSBN;
		this.author = author;
		this.price = price;
	}
	
	//Getter and Setter
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the iSBN
	 */
	public int getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN
	 *            the iSBN to set
	 */
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the noOfBook
	 */
	public int getNoOfBook() {
		return noOfBook;
	}

	/**
	 * @param noOfBook the noOfBook to set
	 */
	public void setNoOfBook(int noOfBook) {
		this.noOfBook = noOfBook;
	}
	//To String method
	public String toString() {
		return "BOOK TITLE :" + title + "\nISBN :" + ISBN + "\nAUTHOR :" + author + "\nPRICE :" + price+"\n";
	}
	
	public void printAllTheBooks(){
		System.out.println(toString());
	}		 
}//end of class
