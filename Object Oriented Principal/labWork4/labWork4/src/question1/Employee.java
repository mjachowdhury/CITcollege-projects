package question1;

/*
 * This class contains about employee details
 */
public abstract class Employee {
	private String firstName;
	private String lastName;
	private String PPSN;// unique number

	/**
	 * @param firstName
	 * @param lastName
	 * @param pPSN
	 */
	public Employee(String firstName, String lastName, String ppsn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.PPSN = ppsn;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the pPSN
	 */
	public String getPPSN() {
		return PPSN;
	}

	/**
	 * @param pPSN
	 *            the pPSN to set
	 */
	public void setPPSN(String pPSN) {
		PPSN = pPSN;
	}

	// Abstract method for this class means that, that method have to implement
	// rest of the class whoever will extends employee class.
	// public abstract boolean getStatus();
	public abstract void getStatus();

	public String toString() {
		return "Employee: \nFirstName :" + firstName + "\nLastName :" + lastName + "\nPPSN\t :" + PPSN;
	}

	public void print() {
		System.out.println(toString());
	}

}
