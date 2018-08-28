package view;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import controller.DateUtil;
import controller.WeatherSystemController;
import controller.ReportService;
import model.daysCategory;
import model.Repository;

public class WeatherSystemView 
{
	private WeatherSystemController theWeatherSystemController;
	private Scanner keyboard = new Scanner(System.in);
	/*
	 * This variable store the value of menu-choice
	 */
	private int choice;
	
	Repository repo = Repository.getRepository();
	
 
	/*
	 * Call this constructor to create WeatherSystemView object with default details.
	 */
	//Creating instance of  WeatherData Controller
	
	public WeatherSystemView() {
		this.theWeatherSystemController = new WeatherSystemController();	
		theWeatherSystemController.restoreRepository();
		
	}
	
/*
 * This method prepare a Personal WeatherData Manger menu using switch-case and infinite loop.
 * Also ask for user choice. 
 * 
 */
	public void showMenu()
	{
		while(true)
		{
			printMenu();
			switch (choice) {
				case 1:
					String name;
					keyboard.nextLine();//new line char is read here which is already present in stream and its not in used 
					System.out.print("Enter the Days name: ");
					name = keyboard.nextLine();
					theWeatherSystemController.onAddCategory(name);
					
					//theWeatherSystemController.onAddCategory();
					pressAnyKeyToContinue();
					break;
					
				case 2:	
					
					theWeatherSystemController.onCategoryList();
					pressAnyKeyToContinue();
					break;
					
				case 3:
					 					 
					theWeatherSystemController.onWeatherDataEntry();
					pressAnyKeyToContinue();
					break;
					
				case 4:
					 
					theWeatherSystemController.onExpenseList();
					pressAnyKeyToContinue();
					break;
				 
				case 5:
					 
					theWeatherSystemController.onCategorizedAverageSunshineList();
					pressAnyKeyToContinue();
					break;
				case 0:
					theWeatherSystemController.onExit();
					break;
			}
		}
	}
	/*
	 * This method prints a menu(CUI/CLI menu)
	 */
	public void printMenu() 
	{
		System.out.println("-----Personal WeatherData Menu-----");
		System.out.println("1. Add Days For The Category");
		System.out.println("2. All Days Category List");
		System.out.println("3. WeatherData Entry");
		System.out.println("4. WeatherData List");
		System.out.println("5. Categorized WeatherData List");
		System.out.println("0. Exit");
		System.out.println("------------------------------");
		System.out.print("Enter your Choice: ");
		choice = keyboard.nextInt();
	}
	 
	public void pressAnyKeyToContinue()
	{
		System.out.println("Press any key to continue...");
		try {
			System.in.read();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
}
