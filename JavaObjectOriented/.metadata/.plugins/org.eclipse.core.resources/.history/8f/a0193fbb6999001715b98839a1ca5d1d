package vehicle;

public class Vehicle {
	//instance variable
	private String name = "";
	private String color = "";
	private String model = "";
	private String company = "";
	private String engine = "800";
	public static int count = 0;//this variable will able to track how many we created in the factory eg-mobile,car,how many empoyee etc
	
	//empty constructor
	public Vehicle(){	
		count++;//this variable will able to track how many object we created in the factory eg-mobile,car,how many empoyee etc
				//and it will reflect all the object
	}
	
	//constructor with parameters
	public Vehicle(String name, String color, String model, String complany, String engine){
		this.name = name;
		this.color = color;
		this.model = model;
		this.company = company;
		this.engine = engine;
		count++;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the engine
	 */
	private String getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	private void setEngine(String engine) {
		this.engine = engine;
	}

	//getting the speed value will use getEngine function
	public int getSpeed(){
		String a = getEngine();
		if (a == "800"){
			return 90;
		}
		else{
			return 120;
		}
	}
	
	public static String getVehicle(){
		return "You have set the vehicle name and color.";
	}
	
}
