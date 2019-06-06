package question1;
/*
 * This class contains about part time employee details
 */
public class PartTimeEmployee extends Employee {

	private double hourlyPay;
	private int hours;
	private boolean isPartTime = true;

	/**
	 * @param firstName
	 * @param lastName
	 * @param ppsn
	 * @param hourlyPay
	 */
	public PartTimeEmployee(String firstName, String lastName, String ppsn, double hourlyPay, int hoursWorked,
			boolean isPartTime) {

		super(firstName, lastName, ppsn);

		setHourlyPay(hourlyPay);
		setHours(hoursWorked);
		setPartTime(isPartTime);

		// this.hourlyPay = hourlyPay;
		// this.hours = hoursWorked;
		// this.isPartTime = isPartTime;
	}

	/**
	 * @return the isPartTime
	 */
	public boolean isPartTime() {
		return isPartTime;
	}

	/**
	 * @param isPartTime
	 *            the isPartTime to set
	 */
	public void setPartTime(boolean isPartTime) {
		this.isPartTime = isPartTime;
	}

	/**
	 * @return the hourlyPay
	 */
	public double getHourlyPay() {
		return hourlyPay;
	}

	/**
	 * @param hourlyPay
	 *            the hourlyPay to set
	 */
	public void setHourlyPay(double hourlyPay) {
		this.hourlyPay = (hourlyPay < 0.0) ? 0.0 : hourlyPay;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hoursWorked) {
		this.hours = ((hoursWorked >= 0) && (hoursWorked < 40)) ? hoursWorked : 0;
	}

	public double calculateWeeklySalary() {
		return getHourlyPay() * getHours();
	}

	@Override
	public void getStatus() {
		if (!isPartTime()) {
			System.out.println("Your are not part time employee");
			// return false;
		} else
			// return isPartTime;
			System.out.println("Your are part time employee.");
	}

	public String toString() {
		return super.toString() + "\nHourly Pay : " + hourlyPay + "\nHours :" + hours + "\nTotal Weekly Salary: "+calculateWeeklySalary()+"\nStatus Is Part Time? : "
				+ isPartTime;
	}

	public void print() {
		System.out.println(toString());
	}

}
