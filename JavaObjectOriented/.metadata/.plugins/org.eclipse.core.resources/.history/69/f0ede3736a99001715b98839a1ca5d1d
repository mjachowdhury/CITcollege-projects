package vehicle;
/*
 * This class will inheritance from the Vehicle class
 */
public class Car extends Vehicle{
	private boolean powerStearing = false;
	private boolean ledScreen = true;
	
	//constructor
	public Car() {
		super();
	}
	//I do not have to assign all the field variable here I can call parents class super() and put all the parameters name
	public Car(String name, String color, String model, String complany, String engine, boolean powerStearing, boolean ledScreen) {
		super(name, color, model, complany, engine);	
		this.powerStearing = powerStearing;//these to for this class
		this.ledScreen = ledScreen;
	}
	/**
	 * @return the powerStearing
	 */
	public boolean getPowerStearing() {
		return powerStearing;
	}
	/**
	 * @param powerStearing the powerStearing to set
	 */
	public void setPowerStearing(boolean powerStearing) {
		this.powerStearing = powerStearing;
	}
	/**
	 * @return the ledScreen
	 */
	public boolean getLedScreen() {
		return ledScreen;
	}
	/**
	 * @param ledScreen the ledScreen to set
	 */
	public void setLedScreen(boolean ledScreen) {
		this.ledScreen = ledScreen;
	}
	
	/**
	 * @return the name
	 * This variable is not visible here but we can call here with the help of super.
	 * And if I want to add some other string I can do it like this 
	 */
	public String getName() {
		return "Your vehicle name is :"+super.getName();
	}
	
	

}
