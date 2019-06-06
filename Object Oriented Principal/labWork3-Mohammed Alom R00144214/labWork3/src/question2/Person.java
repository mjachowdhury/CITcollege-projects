/*
 Part a)	Write code for a class Person. A Person object is to have 
 attributes name, age and address.
 */
package question2;

import java.util.ArrayList;

public class Person {
	// Here Cat Object is acting as a pet of the Person Object
	private ArrayList<Cat> myCat;

	private String name;
	private String address;
	private int age;

	/**
	 * @param name
	 * @param address
	 * @param age
	 */
	public Person(String name, String address, int age) {
		this.name = name;
		this.address = address;
		this.age = age;
		myCat = new ArrayList<Cat>();
	}

	// this is to do with arrayList
	/**
	 * @return the myCat
	 */
	public ArrayList<Cat> getMyCat() {
		return myCat;
	}

	/**
	 * @param myCat
	 *            the myCat to set
	 */
	public void setMyCat(ArrayList<Cat> myCat) {
		this.myCat = myCat;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", age=" + age + "]";
	}

	/*
	 * This method will print toString() method.
	 */
	public void print() {
		System.out.println(toString());
	}

	/*
	 * This method will add 20 cat in the person object. And will act owner of
	 * the 20 cat object.
	 */
	public void addCat(Cat cat) {
		if (myCat.size() > 20) {
			throw new IllegalArgumentException("Exceeded the limit");
		}
		myCat.add(cat);
	}

}
