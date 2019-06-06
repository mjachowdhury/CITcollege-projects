/*
 * Write a program called TestStudentPartA that will allow a 
 * teacher to input student details such as student first name, 
 * student second name and examination mark at the keyboard for
 * at least 20 students. Once all examination marks have been 
 * entered, the program must display the mark and grade for 
 * each of the students as follows:

	“William Buckley received a Merit for his mark of 68”. 
	And so on for the rest of the students
 */

/*
 * The grades are given by the following table:
      Mark range     grade
      85 – 100           distinction	(2)
      65 – 84             merit
      40 – 64             pass
      0  - 39               fail

 */

package question2;

import java.util.Scanner;

public class TestStudentPartA {

	public static void main(String[] args) {
		//Field variables
		String firstName;
		String lastName;
		double mark;

		// creating student object with default number of students
		Student[] student = new Student[2];

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the Student details.");
		System.out.println("==========================");

		// this loop will take student details from the user

		for (int i = 0; i < student.length; i++) {
			System.out.println("Enter Studnet First Name :");
			firstName = keyboard.next();
			System.out.println("Enter Studnet Last Name :");
			lastName = keyboard.next();
			System.out.println("Enter Studnet Mark :");
			mark = keyboard.nextDouble();

			// creating student object and putting all the values inside the
			// array
			Student stud = new Student(firstName, lastName, mark);
			student[i] = stud;// Storing the student details into student array
		} // end for loop

		keyboard.close();

		System.out.println("==========================================");
		System.out.println("           Student Details                ");
		System.out.println("------------------------------------------");

		// This loop will iterate all the student details from the student array
		for (int i = 0; i < student.length; i++) {
			System.out.println(student[i].getFirstName() + " " + student[i].getLastName() + " received a "
					+ student[i].getGrade() + " for his mark of " + student[i].getMark() + ".");
		}

	}

}
