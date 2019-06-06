
import java.util.Scanner;

public class LabExamMain {
	
	public static void main (String [] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		AssemblyLine toy = new AssemblyLine();
		
		int userChoice=0;
		
		while (userChoice!=5)
		{
			System.out.println("Well come to the Santa Assembly Line.");
			System.out.println("Please choose your options:");
			System.out.println();
			
			toy.printMenu();
			userChoice = keyboard.nextInt();
			
			if(userChoice==1)
			{
				toy.AddItem();
			}
			else if (userChoice==2)
			{
				toy.PrintIteam();
			}
			else if(userChoice==3)
			{
				toy.Search();
			}
			else if(userChoice==4)
			{
				toy.ClearItem();
			}
			else 
			{
				System.out.println("Choice is not right. Please Choose the right options from the menu.");
				System.out.println();
			}
			
			
		}
	}

}
