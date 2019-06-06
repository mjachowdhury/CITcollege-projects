
import controller.CarController;
import view.ViewApplication;

public class MainApplication {
	 
	
	public static void main(String [] args )
	{
		
		ViewApplication v = new ViewApplication();
		CarController carC = new CarController();
		
		
		carC.load();//loading last race info
		System.out.println("--------------------------------------------\n");
		carC.getTheDate();	
	    v.start();
	    
	}
}

