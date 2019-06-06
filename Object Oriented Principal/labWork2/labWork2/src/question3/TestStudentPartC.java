package question3;

import question3.view.StudentView;

/*
 * Here is the main method it will call the StudentView Class to start the program.
 */
public class TestStudentPartC {

	public static void main(String[] args) {

		System.out.println("\t\tWEL COME TO THE STUDENT MANAGEMENT PROGRAM");
		System.out.println("\t\t==========================================");
		StudentView studentView = new StudentView();
		studentView.startUserInterface();
	}

}

/*
 * The benefits of using the ArrayList over Array are huge. Its give much more
 * efficiency and to add as many as object compare to Array is fixed size data
 * structure Array use primitive type data arrayList use generic type data Array
 * use = operator to assign value ArrayList use .add operator to assign value.
 */
