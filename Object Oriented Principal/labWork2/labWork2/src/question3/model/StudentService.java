package question3.model;

import java.util.ArrayList;

public class StudentService {

	// Declaring the Student as ArrayList
	private ArrayList<Student> theStudents;
	private int noOfStudent = 0;

	// Creating Student object through ArrayList
	public StudentService() {
		this.theStudents = new ArrayList<Student>();
	}

	/**
	 * @return the noOfStudent
	 */
	public int getNoOfStudent() {
		return noOfStudent;
	}

	/**
	 * @param noOfStudent
	 *            the noOfStudent to set
	 */
	public void setNoOfStudent(int noOfStudent) {
		this.noOfStudent = noOfStudent;
	}

	/**
	 * @return the theStudents
	 */
	public ArrayList<Student> getTheStudents() {
		return theStudents;
	}

	/**
	 * @param theStudents
	 *            the theStudents to set
	 */
	public void setTheStudents(ArrayList<Student> theStudents) {
		this.theStudents = theStudents;
	}

	/*
	 * This method will add student object in the ArrayList
	 */
	public void addStudentToList(Student s) {
		this.theStudents.add(s);
		noOfStudent++;
	}

	// public void removeStudnetFromList(Student s) {
	// this.theStudents.remove(s);
	// }

	/*
	 * This method will display the total student from the system.
	 */
	public void displayAllStudent() {
		for (Student s : theStudents) {
			s.print();
		}
	}

}
