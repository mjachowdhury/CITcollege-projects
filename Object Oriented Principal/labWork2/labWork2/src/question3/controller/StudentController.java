package question3.controller;

import question3.model.Student;
import question3.model.StudentService;

public class StudentController {

	private StudentService studentService;
	private int noOfStudent = 0;

	public StudentController() {
		// Creating the StudentService object.
		this.studentService = new StudentService();

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

	/*
	 * This method will add the student this method is connected StudentService
	 * and StudentView.Its working as a controller
	 */
	public void addStudent(String firstNameFromView, String lastNameFromView, double markFromView) {
		Student s = new Student(firstNameFromView, lastNameFromView, markFromView);

		this.studentService.addStudentToList(s);
		noOfStudent++;
	}

	/*
	 * public void removeStudent(){ Student s = new Student();
	 * studentService.removeStudnetFromList(s); }
	 */

	/*
	 * This method will remove the student this method is connected
	 * StudentService and StudentView.Its working as a controller
	 */
	public void removeStudent(String fname, String lName) {
		int indexOfNameToRomove = -1;
		for (Student currentStudent : this.studentService.getTheStudents()) {
			if (currentStudent.getFirstName().equals(fname) && currentStudent.getLastName().equals(lName)) {
				indexOfNameToRomove = this.studentService.getTheStudents().indexOf(currentStudent);
			}
		}
		// System.out.println("Did not find student.");
		this.studentService.getTheStudents().remove(indexOfNameToRomove);
	}

	/*
	 * This method will display all the student this method is connected
	 * StudentService and StudentView.Its working as a controller
	 */
	public void displayAllStudent() {
		this.studentService.displayAllStudent();
	}
}
