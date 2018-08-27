package question1;

import java.util.Scanner;

/*
 * Part a)	Write an application using an int and String variable, a while loop and two if tests 
 * to output the following song to the screen:	
 * 
 * Part b)	When you have the program working as above, alter it to deal with any number of 
 * bottles where the number is entered from the keyboard. 
 */
public class BottleSong {

	public static void main(String[] args) {

		int numberOfBottles = 0;
		String word = "bottle";

		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many bottle you want to put :");
		numberOfBottles = keyboard.nextInt();
		keyboard.close();

		while (numberOfBottles > 0) {
			System.out.println(numberOfBottles + " " + word + " of juice on the wall");
			System.out.println("Take one down.");
			System.out.println("Pass it around.");
			System.out.println();
			numberOfBottles = numberOfBottles - 1;
		} // End while loop
		if (numberOfBottles == 1) {
			word = "bottle";// one bottle
		} else {
			System.out.println("No more bottles of juice left on the wall.");
		}
	}// end main

}// end class
