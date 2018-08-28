package A2;

public class Elf {

	private String name;
	private double wage;
	private double hours;
	
	public Elf(String name) {
		setName(name);
		setWage(20.50);
		setHours(20);
	}
	
	public double totalOutstandingSalary() {
		return wage * hours;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	public double getWage() {
		return this.wage;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	public double getHours() {
		return this.hours;
	}
}
