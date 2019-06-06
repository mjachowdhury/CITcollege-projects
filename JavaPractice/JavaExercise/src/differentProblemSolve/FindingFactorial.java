package differentProblemSolve;

import java.util.Scanner;

/*
 * This program is about the recursion. Finding the factorial.
 */
public class FindingFactorial {
	
	Scanner input = new Scanner(System.in);
	int result;
	
	public int getFactorial(int num)
	{
		if(num == 1)
			return 1;
		else{
			result = getFactorial(num - 1 )*num; // calling the function
		}
		return result;
		
	}
	
	public void getDetails()
	{
		System.out.println("Enter the number to find factorial : ");
		int num = input.nextInt();
	}
	
 
	public static void main(String[] args) {
	 
		//Creating the object
		FindingFactorial findingFactorial = new FindingFactorial();
		
		//Calling the method
		System.out.println("Factorial of 7 is : "+ findingFactorial.getFactorial(7));
	}

}
