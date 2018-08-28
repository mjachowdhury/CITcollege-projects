package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.CarController;
import model.CarModel;
 

public class ViewApplication {

	private int menuChoice;

	private CarController theCarController;
	 

	private static final int ENTER_DETAILS_ABOUT_CAR_DRIVER = 1;
	private static final int CAR_RACE = 2;
	private static final int EXIT = 3;
	
	
	public ViewApplication()
	{
		//Creating instance of CarController
		this.theCarController = new CarController();
	}
	
	public void start()
	{
		Scanner keyboard = new Scanner(System.in);
		displayMenu();
		
		menuChoice = keyboard.nextInt();
		 
		while(menuChoice !=EXIT)
		{
			switch (menuChoice) 
			{
				case ENTER_DETAILS_ABOUT_CAR_DRIVER:
				{
					String driverName = null;
					int noOfCars;
				
					System.out.println("How many cars you want to put for the race?\n");
					noOfCars = keyboard.nextInt();

					for(int i=0;i<noOfCars;i++)
					{
						System.out.println("Enter the driver name - " + (i +1) + " please :\n");
						driverName = keyboard.next();
						this.theCarController.addCar(driverName);					 
					}
					
					System.out.println("You have entered the driver names for the race : \n");
					
					ArrayList<CarModel> theCar = this.theCarController.getTheCar(); 
					for(CarModel car : theCar)
					{
						System.out.println("Driver Name is :  " +car);
					}
					 
					break;
				}
				
				case CAR_RACE:
				{				 
					this.theCarController.raceStart();;
					this.theCarController.save();				 
					break;
				}			
			 
			}
			
			displayMenu();
			menuChoice = keyboard.nextInt();
		}
	}

	public static void displayMenu() {// display method

		System.out.println();
		System.out.println("-------------------------Simple Car Race Program.---------------------------");
		System.out.println();
		System.out.println("1. To Add the car details.\n");
		System.out.println("2. To Start the car race.\n");
		System.out.println("3. Exit the program.\n");

	}

}
