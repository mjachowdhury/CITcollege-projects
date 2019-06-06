package vehicle;

/*
 * Polyformisiom is the one object has different form
 */
public class Bike extends Vehicle {
	
	private boolean fourStroke = true;

	/**
	 * 
	 */
	public Bike() {
		super();
		 
	}

	/**
	 * @param name
	 * @param color
	 * @param model
	 * @param complany
	 * @param engine
	 */
	public Bike(String name, String color, String model, String complany, String engine, boolean fourStroke) {
		super(name, color, model, complany, engine);
		this.fourStroke = fourStroke; 
	}

	/**
	 * @return the fourStroke
	 */
	public boolean getFourStroke() {
		return fourStroke;
	}

	/**
	 * @param fourStroke the fourStroke to set
	 */
	public void setFourStroke(boolean fourStroke) {
		this.fourStroke = fourStroke;
	}
	
	public String getInfo(){
		return "This is a Bike";
	}
	 

}
