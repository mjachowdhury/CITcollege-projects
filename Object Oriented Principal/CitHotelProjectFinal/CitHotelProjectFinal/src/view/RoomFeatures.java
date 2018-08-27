package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.DoubleRoom;
import model.RoomDetails;
import model.Single;
import model.Suite;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */
public class RoomFeatures {
	Scanner user_input = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// Displays the Room Features of a Hotel.
	void displayRoomFeature() throws Exception {
		try {
			RoomDetails room_type = null;// here Room is "interface" So it is abstract so due to this We can not make object but we can use Reference of Room
			// There are 3 types of Room The User will Decide which Type of Room Want to Stay.
			System.out.println("\n\tROOM TYPES\n\n[1].FOR SUITE\n[2].FOR DOUBLE\n[3].FOR SINGLE\n");
			System.out.print("\tENTER ROOM TYPE :- ");
			int type = user_input.nextInt();
			switch (type) {
			case 1:
				room_type = new Suite(); // The Memory of Object of Interface Allocate to Suite type.
				room_type.displayDetail(); // calling the DisplayDetails methods
				System.out.println("\n\tPRESS ENTER TO CONTINUE.....");
				br.readLine();
				break;
			case 2:
				room_type = new DoubleRoom(); // The Memory of Object of Interface Allocate to Double type.
				room_type.displayDetail(); // calling the DisplayDetails methods
				System.out.println("\n\tPRESS ENTER TO CONTINUE......");
				br.readLine();
				break;
			case 3:
				room_type = new Single(); // The Memory of Object of Interface Allocate to Single type.
				room_type.displayDetail(); // calling the DisplayDetails methods
				System.out.println("\n\tPRESS ENTER TO CONTINUE.....");
				br.readLine();
				break;
			case 4:
				// hotelMenus.errorDisplay();
				// openingMenu();
			default:
				System.out.println("\tINVALID ROOM TYPE..\n");
				System.out.println("\n\tPRESS ENTER TO CONTINUE......");
				br.readLine();
				break;
			}
		} catch (Exception e) {
			System.out.println("INVALID INPUT!");
		}
	}

}
