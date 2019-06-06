package model;

import java.io.Serializable;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	// Field variable
	private String name;
	private String type;
	private int maxGuests = 0;
	private int currentGuests = 0;
	private double roomRate;
	private boolean available = true;
	private Guest currentGuestDuringRoomBooking;
	private double roomCost;

	// Constructor
	public Room(String name, String type) {
		setName(name);
		setType(type);
		setMaxGuests(type);
		setRoomRate(type);
	}

	// Setter and getter
	private void setName(String name) {
		this.name = name;
	}

	private void setType(String type) {
		this.type = type;
	}

	/*
	 * This method sets max guest based on the room type
	 */
	private void setMaxGuests(String type) {
		if (type.equals("SUITE"))
			this.maxGuests = 3;
		if (type.equals("DOUBLE"))
			this.maxGuests = 2;
		if (type.equals("SINGLE"))
			this.maxGuests = 1;
	}

	/*
	 * This method sets room price based on the room type
	 */
	private void setRoomRate(String type) {
		if (type.equals("SUITE"))
			this.roomRate = 150.00;
		if (type.equals("DOUBLE"))
			this.roomRate = 100.00;
		if (type.equals("SINGLE"))
			this.roomRate = 75.00;
	}

	/*
	 * This method will set -total guest in the room -room will be allocated -
	 * total room cost also will check whether lecturer or not
	 */
	public void setCurrentGuest(Guest g, int total, int noOfNights) {
		this.currentGuestDuringRoomBooking = g;
		setCurrentGuests(total);
		setAvailable(false);
		setRoomCost(g.isLecturer(), noOfNights, total);
	}

	private void setCurrentGuests(int currentGuests) {
		this.currentGuests = currentGuests;
	}

	private void setAvailable(boolean available) {
		this.available = available;
	}

	/*
	 * This method will set the room cost based on number of guest also will
	 * check if its lecturer then will get 10% discount
	 */
	private void setRoomCost(boolean isLecturer, int guests, int noOfNights) {
		this.roomCost = guests * roomRate * noOfNights;
		if (isLecturer)
			roomCost = roomCost * .9;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public int getMaxGuests() {
		return this.maxGuests;
	}

	public int getCurrentGuests() {
		return this.currentGuests;
	}

	public Guest getCurrentGuest() {
		return this.currentGuestDuringRoomBooking;
	}

	public double getRoomRate() {
		return this.roomRate;
	}

	public double getRoomCost() {
		return this.roomCost;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void cancelReservation() {
		setCurrentGuests(0);
		setAvailable(true);
		// still has guest in currentGuest variable, but will not show on
		// guestList
	}

	public String guestOccupying() {
		return "ROOM TYPE                            : " + this.getType()
				+ "\nRESERVED BY                                 : " + getCurrentGuest().getName()
				+ "\nEMAIL ADDRESS                               : " + getCurrentGuest().getEmail()
				+ "\nTOTAL NUMBER OF GUESTS FOR THIS RESERVATION : " + getCurrentGuests() + "\n";
	}

	public String toString() {
		if (this.isAvailable())
			return this.getType() + ": " + this.getName() + " - AVAILABLE - MAX GUESTS CAN STAY : "
					+ this.getMaxGuests() + " PERSONS AT " + this.getRoomRate() + " PER PERSON.\n";
			return "ROOM TYPE      : "+this.getType() 
				+ "\nROOM NUMBER    : " + this.getName() 
				+ "\nGUEST NAME     : " + getCurrentGuest().getName()
				+"\nCONTACT NUMBER : "+getCurrentGuest().getContactNumber()
				+"\nEMAIL ADDRESS  : "+getCurrentGuest().getEmail()
				+"\nROOM RATE      : "+this.getRoomRate()
				+ "\nTOTAL GUEST    : "
				+ (this.getCurrentGuests() ) + " PERSON IN THE ROOM.\n";
	}

}
