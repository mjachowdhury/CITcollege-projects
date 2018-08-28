package application;

import java.io.Serializable;

public class Person implements Serializable {
	Patient pName;
	private String name, address;

	public Person() {
	}

	public Person(String name, String address) {
		this.name = name;
		this.address = address;

	}

	public Person(String name, String address, Patient p) {
		this.name = name;
		this.address = address;
		this.pName = p;
	}

	public Patient getPatientName() {
		return pName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*@Override
	public String toString() {
		return "Person [pName=" + pName + ", name=" + name + ", address=" + address + "]";
	}*/

}
