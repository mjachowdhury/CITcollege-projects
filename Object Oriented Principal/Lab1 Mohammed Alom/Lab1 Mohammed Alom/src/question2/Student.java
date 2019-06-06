package question2;

/*
 * This class is about student details and setting and getting the values of the student and finding the grade.
 */
public class Student {

	// instance variables
	private String firstName;
	private String lastName;
	private double mark;
	private String grade;

	// constructor no 1
	public Student() {
	}

	// constructor no 2 with parameters
	public Student(String firstName, String lastName, double mark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mark = mark;
	}

	//Getter and Setter method
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the mark
	 */
	public double getMark() {
		return mark;
	}

	/**
	 * @param mark
	 *            the mark to set
	 */
	public void setMark(double mark) {
		this.mark = mark;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {

		if ((mark >= 85 && mark <= 100)) {
			grade = "Distinction";
		} else if ((mark >= 65 && mark <= 84)) {
			grade = "Merit";
		} else if ((mark >= 40 && mark <= 64)) {
			grade = "Pass";
		} else if ((mark >= 0 && mark <= 39)) {
			grade = "Fail";
		}

		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

}
