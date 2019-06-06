package question2.model;

/*
 * This class details about the film
 */
public class Film {
	// Fields
	private String name;
	private String code;

	// Constructor
	public Film(String name, String code) {
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String toString() {
		return "Film name : " + name + " | Code is : " + code + ".";
	}

	/*
	 * This method will print the film details.
	 */
	public void print() {
		System.out.println(toString());
	}

}
