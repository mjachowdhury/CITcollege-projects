package lotteryResult;
import java.util.Arrays;

/*
 * This program is about to print the lotter result
 */
public class LotteryResult {
	//declaring array and initializing the array
	int [] balls = new int [6];
	int bonusBall;
	 
	
	public String toString() {
		return "The balls are ="+balls[0]+","
								+balls[1]+","
								+balls[2]+","
								+balls[3]+","
								+balls[4]+","
								+balls[5]+", and the bonud ball is "+bonusBall;
	}	
		
}
