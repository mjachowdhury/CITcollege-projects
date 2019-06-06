package model;

import java.util.ArrayList;
/**
 * The class is used as Database/Repository and its a singleton
 * Repository should not get multiple
 */
import java.util.List;

/**
 * The class is used as Database/Repository and its a singleton.
 * 
 * @author Mohammed
 *
 */
public class Repository {
	/**
	 * The list holds all book added by user
	 */
	public List<Book> theBook = new ArrayList<Book>();

	/**
	 * The list holds all lecturer added by user
	 */
	public List<Lecturer> theLecturer = new ArrayList<Lecturer>();

	/**
	 * A singleton reference of repository.
	 */
	private static Repository repository;// creating object

	/**
	 * Private constructor to restrict object creation form outside.
	 */
	private Repository() {
	}

	// when we call getRepository will get one Repository object
	/**
	 * This method provides a singleton object of Repository.
	 * 
	 * @return
	 */
	public static Repository getRepository() {
		if (repository == null) {// if repository null it will give new
									// repository
			repository = new Repository();
		}
		return repository;
	}

}
