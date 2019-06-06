package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.Book;
import model.Lecturer;
import model.Repository;

public class FileStorage {

	/**
	 * Declare a reference of repository by calling a static method. Which
	 * return a singleton repository object.
	 */
	Repository repo = Repository.getRepository();

	// Write file method reusable
	public void serialize(String file, Object obj) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);// Store object in the file
			// use finally block -TODO
			oos.close();
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Writing file
	private void persistRepository() {
		serialize("books.ser", repo.theBook);
		serialize("lecturers.ser", repo.theLecturer);
	}

	// Read file method
	public Object deser(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();// dser
			ois.close();
			fis.close();
			return obj;
		} catch (Exception ex) {
			System.out.println("No existing file");
			return null;
		}
	}

	// Reading file
	public void restoreRepository() {
		@SuppressWarnings("unchecked")
		List<Book> theBook = (List<Book>) deser("books.ser");// Object down
																// casting
		@SuppressWarnings("unchecked")
		List<Lecturer> theLecturer = (List<Lecturer>) deser("lecturers.ser");

		if (theBook != null) {
			// set existing book in repository
			repo.theBook = theBook;
		}
		if (theLecturer != null) {
			// set existing lecturer in repository
			repo.theLecturer = theLecturer;
		}
	}

	/**
	 * This method stop JVM before that it store data permanantly
	 */

	/*
	 * public void onExit() { persistRepository(); System.exit(0); }
	 */
	public void save() {
		persistRepository();
		// System.exit(0);
	}
}
