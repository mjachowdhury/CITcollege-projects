package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import model.*;
 
public class WeatherSystemController {
	
	/*
	 * Declare a reference of repository by calling a static method.
	 * Which return a singleton repository object.
	 */
	Repository repo = Repository.getRepository();
	 
	/*
	 * Declare a reference of reportService to call method to calculate average reports.
	
	 */
	ReportService reportService = new ReportService();
	/*
	 * Declare a Scanner object to take standard input from keyboard.
	 */
	private Scanner keyboard = new Scanner(System.in);
	
	/*public WeatherSystemController() {
		 
	}*/

	/*
	 * This method is taking days category name as input to add new category
	 *in the system.
	 */
	public void onAddCategory(String name)
	{
		 
		daysCategory cat = new daysCategory(name);
		repo.catList.add(cat);
		System.out.println("Success: Days Added.");
		 
	}
	
	/*
	 * call this method to print existing category
	 */
	public void onCategoryList()
	{
		System.out.println("daysCategory List");
		List<daysCategory> cList = repo.catList;
		for(int i=0; i<cList.size(); i++){
			daysCategory c = cList.get(i);
			 
			System.out.println((i+1) + ". "+c.getName() + ", "+c.getCategoryId());
		}
		 
	}
	
	/*
	 * Call this method to enter weather data details. The entered details will be added in repository.
	 */
	public void onWeatherDataEntry()
	{
		System.out.println("Enter Details WeatherData Entry...");
		onCategoryList();
		System.out.print("Choose Days Category: ");
		int catChoice = keyboard.nextInt();
		daysCategory selectedCat = repo.catList.get(catChoice-1);
		
		System.out.print("Enter Hours: ");
		Float hours = keyboard.nextFloat();
		
		System.out.print("Enter Remark: ");
		keyboard.nextLine();
		String remark = keyboard.nextLine();
		
		System.out.print("Enter Date(DD/MM/YYYY): ");
		String dateAsString = keyboard.nextLine();
		Date date = DateUtil.stringToDate(dateAsString);
		
		//Add Weather data details in WeatherData object
		WeatherData eweatherDataxp = new WeatherData();
		
		 
		eweatherDataxp.setCategoryId(selectedCat.getCategoryId());
		eweatherDataxp.setHours(hours);
		eweatherDataxp.setRemark(remark);
		eweatherDataxp.setDate(date);
		
		//Store weather object in repository
		
		repo.weatherList.add(eweatherDataxp);
		System.out.println("Success: WeatherData Added");
				 
	}

	 

	/*
	 * The method prints all entered Weather data.
	 */
	public void onExpenseList() {
		System.out.println("Weather Data listing...");
		List<WeatherData> expList = repo.weatherList;
		
		for(int i=0; i<expList.size(); i++)
		{
			WeatherData exp = expList.get(i);
			String catName = reportService.getCategoryNameById(exp.getCategoryId());
			String dateString = DateUtil.dateToString(exp.getDate());
			System.out.println((i+1)+ ". "+ catName+", "+exp.getHours()+", "+exp.getRemark()+", "+dateString);
		}
		
	}
	 
	
	/*
	 * This method is called from menu to prepare category-wise-weather data-total.
	 */
	public void onCategorizedAverageSunshineList() {
		 
		System.out.println("Categorized Weather Data listing...");
		Map<String,Float> resultMap = reportService.calculateCategorizedTotal();
		Set<String> categories = resultMap.keySet();
		Float averageTotal = 0.0F;
		
		for(String categoryName : categories)
		{
			Float catWiseTotal = resultMap.get(categoryName);
			averageTotal = averageTotal+catWiseTotal;
			
			System.out.println(categoryName+" : "+ catWiseTotal );
		}
		System.out.println("------------------------");
		System.out.println("Average Total : "+averageTotal);
	}
	
	/*
	 * This method stop JVM before that it store data permanently 
	 */
	
	public void onExit()
	{
		persistRepository();
		System.exit(0);
	}

	 
	//Write file method reusable
	public void serialize(String file, Object obj)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);//Store expense list in the file
			
			oos.close();
			fos.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}		
	}
	
	//Writing file 
	private void persistRepository() 
	{
		serialize("weatherData.ser", repo.weatherList); 
		serialize("daysCategory.ser", repo.catList);
	}
	//Read file method
	public Object deser(String file)
	{
		try 
		{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();//dser
		return obj;
		}
		catch (Exception ex) 
		{			 
			//ex.printStackTrace();
			System.out.println("No existing file");
			return null;
		}
		
	}
	
	//Reading file
	public void restoreRepository() {
		 List<WeatherData> expList = (List<WeatherData>) deser("weatherData.ser");//Object down casting
		 List<daysCategory> catList = (List<daysCategory>) deser("daysCategory.ser");
		 
		 if(expList !=null)
		 {
			 // set existing expenses in repository
			 repo.weatherList= expList;
		 }
		 if(catList !=null)
		 {
			 // set existing category in repository
			 repo.catList= catList;
		 }		
	}

}


	 