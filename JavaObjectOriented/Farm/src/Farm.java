import java.io.*;

public class Farm {
	//contains data for a farm
	String farmerName;
	int numberOfCows;
	int numberOfGoats;
	int balesOfHay;
	
	public void newCowArrives(){
		//called when new cow is brought
		numberOfCows=numberOfCows+1;
	}
	
	public void newGoatArrives(){
		//called when new goat is brought
		numberOfGoats=numberOfGoats+1;
	}
	
	public void makingHay(int howMuchHay){
		//adds how much to stock
		balesOfHay = balesOfHay + howMuchHay;
	}

	@Override
	public String toString() {
		/*
		 * returns string summerization farm
		 */
		return "Farm =" + farmerName + "'s farm has- numberOfCows=" + numberOfCows + "cows "
											+ "	and numberOfGoats=" + numberOfGoats
										 + " goats and balesOfHay=" + balesOfHay ;
	}
	
	
	

}
