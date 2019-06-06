package lotteryResult;

import java.util.Scanner;

public class LotteryResultMain {

	public static void main(String[] args) {
		//creating object instantiating 
		LotteryResult thisWeekResult = new LotteryResult();
		
		System.out.println("Welcome to the national lottery program.");
		
		
		Scanner keyboard = new Scanner(System.in);
		int number;
		System.out.println("Please type the number one by one ,"
				+"pressing the return key each time.");
		int i;
		for(i=0;i<5;i++){
			number = keyboard.nextInt();
			//n = Integer.parseInt(number);
			System.out.println("Number is "+ number);
			thisWeekResult.balls[i] = number;
		}
		System.out.println("Please type the bonus number,"
				+"then press the return key.");
		number = keyboard.nextInt();
		//n = Integer.parseInt(number);
		System.out.println("Bonus number is "+number);
		thisWeekResult.bonusBall = number;
		
		// print out the numbers
		System.out.println("\n *** This week's result ***");
		System.out.println(thisWeekResult.toString());

	}

}
