package ifExample;

import java.util.Scanner;

public class IfExampleApp {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		String password;
		System.out.println("Please enter your password :");
		password = input.nextLine();
		while(!password.equals("javaProgram")){
			System.out.println("Try again.");
			password = input.nextLine();
		}
		System.out.println("Correct!!");
		//both will work
		//if(password.equals("javaProgram")){
			/*if(password.equalsIgnoreCase("javaProgram")){
			System.out.println("Password is corret");
		}
		else{
			System.out.println("Wrong password.Sorry");
		}*/
	}
}
