package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {

	private String name;
	private int ID;	
	private ArrayList<Book> BookList;//ArrayList of Books
	public static int MAX_NO_OF_BOOKS = 15;
	
	//Constructor
	public Lecturer(String name, int id) {
		super();
		this.name = name;
		this.ID = id;
		BookList = new ArrayList<Book>();
	}
	 
	//Getter and Setter
	public String getName() {return name;}
	public void setName(String name) {this.name = name;} 
	public int getID() {return ID;} 
	public void setID(int iD) {ID = iD;}
	
	public static int getMAX_NO_OF_BOOKS() {return MAX_NO_OF_BOOKS;}
	public static void setMAX_NO_OF_BOOKS(int mAX_NO_OF_BOOKS) {MAX_NO_OF_BOOKS = mAX_NO_OF_BOOKS;}

	public ArrayList<Book> getList() {return BookList;}
	public void setList(ArrayList<Book> list) {this.BookList = list;}
	
	//Adding and removing Books 
	public void addBook(Book book) {getList().add(book);}
	public void removeBook(Book book) {getList().remove(book);}
	
	//ToString method
	public String toString() {
		return "LECTURER NAME :" + name + ", ID=" + ID;
	}

	public void printLecturerDetails() {System.out.println(toString());}
}//end of class
