import java.util.Scanner;

public class TheCarRaceGame 
{

	private int numberOfCars = 0;
	private String[] driverNames=null;
	private static final int maxDistance = 10000;//maximum distance 

	public  void race()
	{
		
		Scanner keyboard = new Scanner(System.in);
		String userResponse="";
		
		System.out.println("****WELCOME TO THE CAR RACE COMPITION****");
		System.out.println("");
		System.out.println("DO YOU WANT TO PLAY CAR RACE (Y/N)?");
		userResponse=keyboard.next();
		
		while(userResponse.equalsIgnoreCase("y"))
		{
			System.out.println("HOW MANY CARS DO YOU WANT TO PUT FOR RACE :");
			numberOfCars = keyboard.nextInt();
			
			driverNames = new String[numberOfCars];
			
			
			for (int i = 0; i < driverNames.length; i++) //putting the driver names inside the array with the loop
			{
				System.out.println("PUT THE DRIVER NAME - " + (i + 1) + " PLEASE:");
				driverNames[i] = keyboard.next();
			}

			int[] carDistance = new int[numberOfCars]; //how far each car will go during the race
			
			 
			for (int time = 0; time <= maxDistance; time++) // to get the time spent of the each car to finish the race
			{
				for (int i = 0; i < carDistance.length; i++) // that will show the distance 
				{
					carDistance[i] += randomWithRange(0, 50); //race will go 0-50 random speed

						if (carDistance[i] >= maxDistance) //will decide the winner from the car
						{
							System.out.println("FIRST WINNER DETAILS :\nDRIVER NAME :" + driverNames[i]+"\nTOTAL DISTANCE TRAVELED : " + carDistance[i]+" METERS "+"\nTOTAL TIME TO FINISH THE RACE : "+time);
							System.out.println();
							System.out.println("\n\nALL OTHERS COMPETITORS RACE DETAILS");
							
							for (int t = 0; t < carDistance.length; t++) // will find out all other drivers time and distance 
							{
							System.out.println("DRIVER NAME :" + driverNames[t]+"\nCAR DISTANCE : " + carDistance[t]+"\n");
							System.out.println();
							}
						
							
							System.out.println("THANK YOU FOR PLAYING CAR RACE GAME. GOOD BYE.");
	
							System.exit(0);
						}
					}			
			  }			 
		}   
	}

	public static int randomWithRange(int min, int max) //method for random number generator
	{
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
	 
}
