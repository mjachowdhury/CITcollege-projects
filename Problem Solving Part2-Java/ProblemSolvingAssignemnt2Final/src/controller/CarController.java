package controller;

import java.io.BufferedReader;
import java.io.File; 
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
 

import model.CarModel;
 
public class CarController 
{
	 
	private static final String FILE_LOCATION = "C:\\ProblemSolvingPart2\\CarRace.txt";
	 
	private static final int maxDistance = 10000;//maximum distance 
	
	private ArrayList<CarModel> theCar;
	 
	
	//Constructor	
	public CarController() 
	{
		//Creating instance of car model
		this.theCar = new ArrayList<CarModel>(); 	 		 
	}


	public ArrayList<CarModel> getTheCar() 
	{
		return this.theCar;
	}

	
	//Adding car driver name method	
	public void addCar(String driverName)
	{	 
		CarModel c = new CarModel(driverName);	
		 theCar.add(c);	 
	}
	
	
	//To get all driver names method	
	public void getDriverNames()
	{
		Iterator<CarModel> car = theCar.iterator();
			while(car.hasNext())
				{
					CarModel cm = car.next();
					cm.getDriverNames();
				}	
	}
	
	
	//Race start method	
	public void raceStart() 
	{
	    boolean raceFinished = false;
	    long startTime = System.currentTimeMillis();
	    long finishTime=0;
	    
	    CarModel winner = null;
	   
	    while (true) 
	    {
	        if (raceFinished) 
	        {
	            processResults(winner, finishTime - startTime);
	            return;
	        }
	        for (CarModel car : theCar) 
	        {
	            int carDistance = car.getCarDistance();
	            carDistance += randomWithRange(0, 50);//Adding random number
	            car.setCarDistance(carDistance);
	            
	            if (carDistance >= maxDistance)
	            {
	                raceFinished = true;
	                if (winner == null || carDistance > winner.getCarDistance()) 
	                {
	                    winner = car;   // We have new winner
	                    finishTime = System.currentTimeMillis();
	                }
	            }
	        }
	    }//end while loop
	    
	}//end method

	
	
	//To determine winner and time method
	private void processResults(CarModel winner, long winnerTime) 
	{
		 
	    System.out.println("FIRST WINNER IS :" + winner.getDriverNames() + 
	    					"\nTOTAL DISTANCE TRAVELED : " + winner.getCarDistance() + " METERS " + 
	    					"\nTOTAL TIME TO FINISH THE RACE : " + winnerTime + " Mili-Second");

	    System.out.println("\n\nALL OTHERS COMPETITORS RACE DETAILS");
	    
	    for (CarModel car : theCar) 
	    {
	        if (!car.equals(winner)) 
	        {// need to skip winner
	            System.out.println("DRIVER NAME :" + car.getDriverNames() + 
	            					"\nCAR DISTANCE : " + car.getCarDistance() + "\n");
	            
	            System.out.println();
	            
	        }
	    }	   
	}//end method
	
	
	
	//Random number generated method 	
	public static int randomWithRange(int min, int max) //method for random number generator
	{
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
	
	//To stamp the date with the race program	
	public void getTheDate()
	{
		Calendar rightNow = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		
		String todayString = formatter.format(rightNow.getTime() );
		System.out.println("Today's Date is :" + todayString );
	}
	 
	
	
	//Save to file method		
	public void save() 
	{ 
		try
		{
				
			File carFile = new File(FILE_LOCATION);
			
			//if file is not exists it will create new file.	
			if( !carFile.exists() )
			{
				carFile.createNewFile();
			}
				
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		    Date today = Calendar.getInstance().getTime();
		    String reportDate = df.format(today);
		    String dateToPrintToFile = reportDate;
				
		    //creating file writer object
		    FileWriter carWriter = new FileWriter( carFile );
			    			
		    carWriter.write("Last race took place on : " + dateToPrintToFile + " \n\n");
		        
		    System.out.println("\n");
		        
		    // go through ArrayList <CarModel> inside the CarModel and write a line to the file for each item on the list
		        
		    for(CarModel currentCarModel : this.theCar )
			{
				carWriter.write("Driver name was : " + currentCarModel.getDriverNames() 
								+ " - " + currentCarModel.getCarDistance() + " meters travelled.\n\n" );				 
			}
				
			System.out.println();
			System.out.println();
	
			carWriter.close();
				
			}	
				catch(Exception ex)
				{
					System.out.println( ex.getMessage() );
				}
		
	}//end save file method

	

	//To read the last race result
	public void load()
	{
		System.out.println("------------------------Reading last car race result info.---------------------------\n");
		
	       //Name of the file
	       String fileName=FILE_LOCATION;
	       
	       try
	       {

	          //Create object of FileReader
	          FileReader fr = new FileReader(fileName);

	          //Instantiate the BufferedReader Class
	          BufferedReader bufferReader = new BufferedReader(fr);

	          //Variable to hold the one line data
	          String line;

	          // Read file line by line and print on the console
	          while ((line = bufferReader.readLine()) != null)   
	          {
	            System.out.println(line);
	          }
	          
	          //Close the buffer reader
	          	bufferReader.close();
	       }
	       	  catch(Exception e)
	       		{
	       			System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       		}
		 
	}// end of loadFromFile method

}//end of class
