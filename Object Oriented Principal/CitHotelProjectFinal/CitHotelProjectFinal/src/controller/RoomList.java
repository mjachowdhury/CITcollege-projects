package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import model.Room;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */

public class RoomList implements Iterable<Room> {
	private ArrayList<Room> rooms;

	public RoomList() {
		rooms = new ArrayList<Room>();
	}

	/*
	 * Adding room method
	 */
	public void addRoom(Room r) {
		rooms.add(r);
	}

	public Iterator<Room> iterator() {
		// Makes RoomList iterable
		return rooms.iterator();
	}

	/*
	 * Checking whether room available or not
	 */
	public RoomList availableRooms() {
		RoomList returnRooms = new RoomList();
		for (Room r : rooms) {
			if (r.isAvailable())
				returnRooms.addRoom(r);
		}
		return returnRooms;
	}

	/*
	 * Checking whether room already booked or not
	 */
	public RoomList roomAlreadyBooked() {
		RoomList returnRooms = new RoomList();
		for (Room r : rooms) {
			if (!r.isAvailable())
				returnRooms.addRoom(r);
		}
		return returnRooms;
	}

	/*
	 * This method Checking suitable room for customer based on the number of
	 * guest during booking and will return those rooms
	 */
	public RoomList findSuitableRooms(int guests) {
		RoomList returnRooms = new RoomList();
		for (Room r : rooms) {
			if (r.isAvailable() && r.getMaxGuests() >= guests)
				returnRooms.addRoom(r);
		}
		return returnRooms;
	}

	/*
	 * This method will return biggest room based on the number of guest.
	 */
	public int biggestAvailableRoom() {
		int max = 0;
		for (Room r : availableRooms()) {
			if (r.getMaxGuests() > max)
				max = r.getMaxGuests();
		}
		return max;
	}

	/*
	 * This method will return number of rooms
	 */
	public int numberRooms() {
		int count = 0;
		for (Room r : rooms) {
			count++;
		}
		return count;
	}

	/*
	 * This method will return all the guest staying in the hotel
	 */
	public String guestList() {
		String returnString = "";
		for (Room r : roomAlreadyBooked()) {
			returnString = returnString + r.getName() + " " + r.guestOccupying() + "\n";
		}
		if (returnString.equals(""))
			return "NO GUEST STAYING";
		return returnString;
	}//end method

	/*
	 * This method will return guest either student or lecturer based on their
	 * email address
	 */
	public String guestList(boolean lecturer) {
		// Overloading method to return only lecturer/student
		String returnString = "";
		for (Room r : roomAlreadyBooked()) {
			if (r.getCurrentGuest().isLecturer() == lecturer)
				returnString = returnString + r.getName() + " " + r.guestOccupying() + "\n";
		}
		if (returnString.equals("") && lecturer)
			return "NO LECTURER GUEST FOUND IN THE SYSTEM.";
		if (returnString.equals("") && !lecturer)
			return "NO STUDENT GUEST FOUND IN THE SYSTEM.";
		return returnString;
	}//end method

	/*
	 * This method will return room list (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String returnString = "";
		for (Room r : rooms) {
			returnString = returnString + r.toString() + "\n";
		}
		if (returnString.equals(""))
			return "NO ROOMS TO DUSPLAY.";
		return returnString;
	}//end method

	/*
	 * This method will verify if its found similar email address during booking
	 */
	public boolean alreadyReserved(String email) {
		boolean result = false;
		for (Room r : roomAlreadyBooked()) {
			if (r.getCurrentGuest().getEmail().toLowerCase().equals(email.toLowerCase())) {
				result = true;
			}
		}
		return result;
	}//end method

	/*
	 * This method will return all the booked reservation
	 */
	public String getReservation(String email) {
		String returnString = "";
		for (Room r : roomAlreadyBooked()) {
			if (r.getCurrentGuest().getEmail().toLowerCase().equals(email.toLowerCase())) {
				returnString = r.toString();
			}
		}
		return returnString;
	}//end method

	/*
	 * This method will return booked room if someone looking for either during
	 * payment or cancel the booking based on their email
	 */
	public Room getRoom(String email) {
		for (Room r : roomAlreadyBooked()) {
			if (r.getCurrentGuest().getEmail().toLowerCase().equals(email.toLowerCase())) {
				return r;
			}
		}
		System.out.println("ROOM NOT FOUND.");
		return null;
	}//end method

	// ===========================SAVING AND READING FILEPART=================================
	// ========================================================================================

	/*
	 * This method will write to the file
	 */
	public void writeToTheFile() {
		// Writes details to file
		int writeCounter = 1;
		for (Room r : rooms) {
			try {
				FileOutputStream fos = new FileOutputStream(".\\hotelRooms\\Room" + writeCounter + ".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(r);
				oos.close();
				fos.close();
				System.out.println("SAVED AS ROOM " + writeCounter);
				writeCounter++;
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}//end loop
	}//end method

	/*
	 * This method will return total number of rooms from the file
	 */
	public int totalRooms() {
		// finds the total number of room files on system
		int roomTotal = 0;
		final File file = new File(".\\hotelRooms\\");
		for (final File child : file.listFiles()) {
			if (child.getName().toLowerCase().substring(0, 4).equals("room") && child.getName().toLowerCase()
					.substring(child.getName().length() - 3, child.getName().length()).equals("ser")) {
				roomTotal++;
			}
		}
		return roomTotal;
	}//end method

	/*
	 * THis method will load rooms details from the file
	 */
	public void loadRoomsFromFile() {
		// loads the hotel rooms records from files
		for (int x = 1; x <= totalRooms(); x++) {
			try {
				FileInputStream fis = new FileInputStream(".\\hotelRooms\\Room" + x + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				rooms.add((Room) ois.readObject());
				ois.close();
				fis.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}//end for loop
	}//end of method

}//end of class
