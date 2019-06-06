
public class FarmMain {
	
	public static void main(String args[]){
		//set up a farm
		
		Farm homeFarm = new Farm();
		
		homeFarm.farmerName = "Mohammed";
		homeFarm.numberOfCows = 10;
		homeFarm.numberOfGoats = 15;
		homeFarm.balesOfHay =50;
		
		//print out the information about the farm.
		System.out.println(homeFarm.toString());
		
		//new animals are brought
		System.out.println("A cow and A Goat are brought");
		homeFarm.newCowArrives();
		homeFarm.newGoatArrives();
		
		//print the farm information now
		System.out.println(homeFarm.toString());
		
		//more hay is made
		System.out.println("More hay is gathered.");
		homeFarm.makingHay(50);
		
		//print out new farm info
		System.out.println(homeFarm.toString());
	}

}
