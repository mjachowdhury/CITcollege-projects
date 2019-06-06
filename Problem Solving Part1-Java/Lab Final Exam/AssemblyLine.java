
import java.util.Scanner;

public class AssemblyLine {
	
	private String userResponse;
	 
	private String [] addToy = new String [10]; 
	private String [] personNames = new String [] {"Mohammed","Jahangir","Alom","Chowdhury","Des","Simon","Opi","Dominic","Sipa","Simi"};
	 
	 Scanner keyboard = new Scanner(System.in);
	 
	public  void  AddItem()
	{
		
		for(int i=0;i<personNames.length;i++)
		{
			 
			System.out.println("Please put the Toy name : ");
			addToy[i]=keyboard.next();
			 
		}
		
	}
	
	public  void PrintIteam()
	{
		 
		System.out.println("Would you like to see all the Items : (Y/N)");
		userResponse = keyboard.next();
		
		while(userResponse.equalsIgnoreCase("y"))
		{
			for(int i=0;i<addToy.length;i++)
			{
				System.out.println(addToy[i]);
			}
		 
		    break;
		}		
		
	}
	
	
	public  void ClearItem()
	{
		System.out.println("Would you like to Clear all the Assembly Line Items? (Y/N)");
		userResponse=keyboard.next();
		System.out.println();
		
		    for (int i = 0; i < addToy.length; i++)
		        addToy[i] = null;
		}
	
	
	public  void Search()
	{
		String search;
		System.out.println("Please enter a name for search :");
		search=keyboard.next();
		 
		for(int i=0;i<addToy.length;i++)
		{
			if(addToy[i].equals(search))
			{
				System.out.println("Toy is found");
				System.out.println();				 
			}					
		}
	}
	
	 public  void printMenu() 
	 {
		  

	        System.out.println();

	        System.out.println("1 For Adding Toy : " );

	        System.out.println("2 For Print Total Items: ");

	        System.out.println("3 For Search The Name:");

	        System.out.println("4 For Clear The Program: ");
	        
	        System.out.println("5 For Exit The Program: ");

	        System.out.println();

	    }

}
