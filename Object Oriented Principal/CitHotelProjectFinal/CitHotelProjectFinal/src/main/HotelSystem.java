package main;

import java.io.IOException;
import controller.RoomList;
import exception.MyCustomException;
import model.Room;
import view.HotelMainView;
import view.HotelMenus;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */
public class HotelSystem {

	public static void main(String[] args) throws MyCustomException {
		// Creating HotelMenus Object
		HotelMenus hotelMenus = new HotelMenus();
		// Calling screenHeader
		hotelMenus.screenHeader();
		try {
			hotelMenus.introHotel();
		} catch (Exception e) {
			System.out.println(e);
		}

		RoomList rooms = new RoomList();
		rooms.loadRoomsFromFile();

		// If no files found for room, create and load new rooms
		// Creating all the rooms and type
		if (rooms.totalRooms() == 0) {
			Room SUITE1 = new Room("SUITE1", "SUITE");
			Room SUITE2 = new Room("SUITE2", "SUITE");
			Room SUITE3 = new Room("SUITE3", "SUITE");
			
			Room DOUBLE1 = new Room("DOUBLE1", "DOUBLE");
			Room DOUBLE2 = new Room("DOUBLE2", "DOUBLE");
			Room DOUBLE3 = new Room("DOUBLE3", "DOUBLE");
			Room DOUBLE4 = new Room("DOUBLE4", "DOUBLE");
			Room DOUBLE5 = new Room("DOUBLE5", "DOUBLE");
			Room DOUBLE6 = new Room("DOUBLE6", "DOUBLE");

			Room SINGLE1 = new Room("SINGLE1", "SINGLE");
			Room SINGLE2 = new Room("SINGLE2", "SINGLE");
			Room SINGLE3 = new Room("SINGLE3", "SINGLE");
			Room SINGLE4 = new Room("SINGLE4", "SINGLE");
			Room SINGLE5 = new Room("SINGLE5", "SINGLE");
			Room SINGLE6 = new Room("SINGLE6", "SINGLE");

			// Adding all the rooms
			rooms.addRoom(SUITE1);
			rooms.addRoom(SUITE2);
			rooms.addRoom(SUITE3);

			rooms.addRoom(DOUBLE1);
			rooms.addRoom(DOUBLE2);
			rooms.addRoom(DOUBLE3);
			rooms.addRoom(DOUBLE4);
			rooms.addRoom(DOUBLE5);
			rooms.addRoom(DOUBLE6);

			rooms.addRoom(SINGLE1);
			rooms.addRoom(SINGLE2);
			rooms.addRoom(SINGLE3);
			rooms.addRoom(SINGLE4);
			rooms.addRoom(SINGLE5);
			rooms.addRoom(SINGLE6);
		}

		// Program start here
		HotelMainView start = new HotelMainView(rooms);
		try {
			start.openingMenu();
		} catch (IOException e) {
			System.out.println(e);
		}		 
	}	
}
