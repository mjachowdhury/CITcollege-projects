package encapsulationExample;

import java.util.Scanner;

public class EncapsulationExample {
	Scanner input = new Scanner(System.in);
	private double length;
	private double width;
	
	/*
	 * Accept details from the user mentod
	 */
	public void acceptDetails()
	{
		System.out.println("Enter length :");
		length = input.nextDouble();
		System.out.println("Enter width : ");
		width = input.nextDouble();
	}
	
	/*
	 *  finding the area method
	 */
	public double getArea()
	{
		return length * width;
	}
	/*
	 * Show detail method
	 */
	public void showDetails()
	{
		System.out.println("Length is : "+length);
		System.out.println("Widht is : "+width);
		System.out.println("Total area is :" +getArea());
	}
	
	public static void main(String [] args)
	{
		EncapsulationExample encapsulationExample = new EncapsulationExample();
		encapsulationExample.acceptDetails();
		encapsulationExample.showDetails();
	}

}
