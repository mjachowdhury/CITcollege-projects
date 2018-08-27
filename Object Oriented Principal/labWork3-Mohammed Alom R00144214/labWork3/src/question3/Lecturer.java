package question3;

public class Lecturer {

	private static final int MAX_NO_OF_BOOKS = 15;
	private String name;
	private int ID;

	BookList books;

	/**
	 * @param name
	 * @param iD
	 * @param books
	 */
	public Lecturer(String name, int iD) {
		this.name = name;
		this.ID = iD;
		this.books = new BookList(MAX_NO_OF_BOOKS);

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the books
	 */
	public BookList getBooks() {
		return books;
	}

	/**
	 * @param books
	 *            the books to set
	 */
	public void setBooks(BookList books) {
		this.books = books;
	}

	/**
	 * @return the maxNoOfBooks
	 */
	public static int getMaxNoOfBooks() {
		return MAX_NO_OF_BOOKS;
	}

	public String toString() {
		return "Lecturer Name is -" + name + "\nIDis -" + ID + "\nbooks -" + books + ".";
	}

	/*
	 * This method will add book
	 */
	public void addBook(Book b) {
		books.addBook(b);
	}

	/*
	 * This method will print toString();
	 */
	public void print() {
		System.out.println(toString());
	}

}
