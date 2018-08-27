package view;

import java.io.IOException;
import java.util.Scanner;

import controller.RoomList;
import exception.MyCustomException;
import model.Guest;
import model.Room;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */
public class HotelMainView {

	private RoomList rooms;
	private HotelMenus hotelMenus;
	private RoomFeatures roomFeatures;

	private Scanner keyboard;
	private int choice;

	// Constructor
	public HotelMainView(RoomList rooms) {
		this.rooms = rooms;
		keyboard = new Scanner(System.in);
		hotelMenus = new HotelMenus();
		roomFeatures = new RoomFeatures();
	}

	public void openingMenu() throws IOException {
		hotelMenus.clearScreen();

		hotelMenus.mainMenu();
		if (keyboard.hasNextInt() == true) {
			choice = keyboard.nextInt();
		}
		keyboard.nextLine();
			switch (choice) {
			case 1:
				try {
					roomFeatures.displayRoomFeature();
				} catch (Exception e) {
					e.printStackTrace();
				}
				openingMenu();
				break;
			case 2:
				try {
					display_AvailableRooms();
				} catch (MyCustomException e) {
					System.out.println(e);
				}
				break;
			case 3:
				display_Guests();
				break;
			case 4:
				reservation_Menu();
				break;
			case 5:
				payments_Menu();
				break;
			case 6:
				try {
					saveMenu();
				} catch (MyCustomException e1) {
					e1.printStackTrace();
				}
				System.out.println("\tWE HAVE SELECTED TO EXIT THE PROGRAM.");
				System.out.println("\t\tTHANK YOU FOR USING.");
				System.out.println("\t\t\tGOOD BYE");
				System.out.println("\t-------------------------------------");
				break;
			default:
				hotelMenus.errorDisplay();
				openingMenu();
				break;
			}// end of switch					 
	}// end of openingMenu method

	private void display_AvailableRooms() throws MyCustomException {
		System.out.println("\tAVAILABLE ROOMS");
		System.out.println("\t***************");
		System.out.println("ROOM TYPE | ROOM NO | \tAVAILABILITY | \t\t\tROOM CHARGE");
		System.out.println("-------------------------------------------------------------------");
		System.out.println(rooms.availableRooms().toString());
		try {
			openingMenu();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void display_Guests() throws IOException {
		hotelMenus.displayGuests();
		if (keyboard.hasNextInt() == true) {
			choice = keyboard.nextInt();
		}
		keyboard.nextLine();

		switch (choice) {
		case 1:
			System.out.println(rooms.guestList());
			break;
		case 2:
			System.out.println(rooms.guestList(false));
			break;
		case 3:
			System.out.println(rooms.guestList(true));
			break;
		case 4:
			openingMenu();
			break;
		default:
			hotelMenus.errorDisplay();
			display_Guests();
		}
		openingMenu();
	}// end of display_Guest method

	private void reservation_Menu() throws IOException {
		hotelMenus.reservationMenu();
		if (keyboard.hasNextInt() == true) {
			choice = keyboard.nextInt();
		}
		keyboard.nextLine();

		switch (choice) {
		case 1:
			new_Reservation();
			break;
		case 2:
			view_Reservation();
			break;
		case 3:
			cancel_Reservation();
			break;
		case 4:
			openingMenu();
			break;
		default:
			hotelMenus.errorDisplay();
			reservation_Menu();
		}
	}// end of reservationMenu method

	private void new_Reservation() throws IOException {
		String name;
		String email;
		String roomName;
		int noOfNights = 0;
		int guests = 0;
		boolean guestsOK = false;
		boolean roomOK = false;
		
		Guest g;
		System.out.println();

		if (rooms.biggestAvailableRoom() == 0) {
			System.out.println("\tSORRY!! NO ROOMS AVAILABLE\n");
			openingMenu();
			return;
		}

		System.out.println("\tNEW RESERVATION");
		System.out.println("\t***************");
		System.out.println("\tPLEASE ENTER GUEST FULL NAME:");
		name = keyboard.next();
		keyboard.next();
		System.out.println("\tPLEASE ENTER YOUR CONTACT NUMBER :");
		String contactNumber = keyboard.next();

		email = getEmail();
		if (email.equals("cancel")) {
			reservation_Menu();
			return;
		}
		if (rooms.alreadyReserved(email)) {
			System.out.println(
					"\tRESERVATION ALREADY MADE UNDER THIS EMAIL.CHOOSE ANOTHER PERSON TO MAKE THIS RESERATION.");
			new_Reservation();
		}

		System.out.println("\tHOW MANY NIGHTS YOU WANT TO STAY? ");
		noOfNights = keyboard.nextInt();
		g = new Guest(name, email, noOfNights, contactNumber);

		System.out.println("\tHOW MANY GUEST WILL STAY ON THIS RESERAVTION? ");
		System.out.println("\tPLEASE BEWARE CURRENT MAXIMUM PEOPLE PER RESERVATION : "
				+ rooms.availableRooms().biggestAvailableRoom());
		do {
			if (keyboard.hasNextInt() == true) {
				guests = keyboard.nextInt();
				if (guests > 0 && guests <= rooms.availableRooms().biggestAvailableRoom()) {
					guestsOK = true;
				} else if (guests > rooms.availableRooms().biggestAvailableRoom()) {
					System.out.println("\tTOO MANY PERSON FOR THIS BOOKING | PLEASE MAKE SEPARATE RESERVATION.");
				} else {
					System.out.println("\tINVALID CHOICE | MINIMUM 1 AND MAXIMUM "
							+ rooms.availableRooms().biggestAvailableRoom());
				}
			} else if (keyboard.next().equals("cancel")) {
				openingMenu();
				return;
			}
		} while (!guestsOK);
		keyboard.nextLine();// added new
		do {
			System.out.println("\tPLEASE CHOOSE A SUITABLE ROOOM :");
			System.out.println(rooms.findSuitableRooms(guests).toString());
			if (g.isLecturer())
				System.out.println("\tLECTURER GET A 10% DISCOUNT !!");
			roomName = keyboard.next();
			for (Room r : rooms.findSuitableRooms(guests)) {
				if (roomName.toLowerCase().equals(r.getName().toLowerCase())) {
					System.out.println("CONFIRM THIS RESERVATION : \n\n" + "GUEST NAME .......: " + g.getName()
							+ "\nCONTACT NUMBER ...: " + g.getContactNumber() + "\nEMAIL ADDRESS ....: " + g.getEmail()
							+ "\nROOM TYPE ........: " + r.getType() + "\nROOM RATE ........: " + r.getRoomRate()
							+ "\nNO OF GUEST ......: " + guests + "\nNO OF NIGHTS .....: " + g.getNoOfNights() + "\n");
					System.out.println("PRESS 'y' || 'yes' TO CONFIRM THE RESERVATION");
					System.out.println("PRESS 'n' || 'NO' TO RETURN THE MAIN MENU");
					String response = keyboard.next();
					if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")) {
						r.setCurrentGuest(g, guests, noOfNights);
						System.out.println("RESERVATION HAS MADE UNDER YOUR NAME.");
						System.out.println("TOTAL PAYABLE ON CHECKOUT : \u20ac " + r.getRoomCost());
						roomOK = true;
					} else if (response.toLowerCase().equals("n") || response.toLowerCase().equals("NO")) {

						openingMenu();
						return;// added new
					}					 
				}
			}
			// new_Reservation();
			if (!roomOK)
				System.out.println("\tINVALID ROOM CHOICE.");
		} while (!roomOK);

		openingMenu();
	}// end of new reservation method

	private void view_Reservation() throws IOException {
		String email;

		if (rooms.roomAlreadyBooked().numberRooms() == 0) {
			System.out.println("\tNO RESERVATION FOUND IN THE SYSTEM.\n");
			reservation_Menu();
			return;
		}

		System.out.println("\tVIEW RESERVATION");
		System.out.println("\t****************");

		email = getEmail();
		if (email.equals("cancel")) {
			reservation_Menu();
			return;
		}

		if (rooms.alreadyReserved(email)) {
			System.out.println(this.rooms.getReservation(email));
		} else {
			System.out.println("\tNO RESERVATION FOUND UNDER THIS EMAIL.");
		}

		reservation_Menu();
	}// end of view reservation method

	private void cancel_Reservation() throws IOException {
		String email;

		if (this.rooms.roomAlreadyBooked().numberRooms() == 0) {
			System.out.println("\tNO RESERVATION FOUND IN THE SYSTEM.\n");
			reservation_Menu();
			return;
		}

		System.out.println("\tCANCEL RESERVATION.");
		System.out.println("\t******************");

		email = getEmail();
		if (email.toLowerCase().equals("cancel")) {
			reservation_Menu();
			return;
		}

		if (this.rooms.alreadyReserved(email)) {
			System.out.println("\tDO YOU WANT TO CANCEL THE RESERVATION ?\n" + this.rooms.getReservation(email));
			System.out.println("PRESS 'y' || 'yes' TO CANCEL THE RESERVATION");
			System.out.println("PRESS 'n' || 'NO' TO RETURN THE PREVIOUS MENU");
			String response = keyboard.next();
			if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")) {
				this.rooms.getRoom(email).cancelReservation();
				System.out.println("\tRESERVATION CANCELED.");
			}
		} else {
			System.out.println("\tNO RESERVATION FOUND UNDER THIS EMAIL.");
		}

		reservation_Menu();
	}// end of cancel reservation method

	private void payments_Menu() throws IOException {

		hotelMenus.paymentsMenu();
		if (keyboard.hasNextInt() == true) {
			choice = keyboard.nextInt();
		}
		keyboard.nextLine();

		switch (choice) {
		case 1:
			new_Payment();
			break;
		case 2:
			try {
				view_Outstanding();
			} catch (MyCustomException e) {
				System.out.println(e);
				// e.printStackTrace();
			}

			break;
		case 3:
			openingMenu();
			break;
		default:
			hotelMenus.errorDisplay();
			payments_Menu();
		}
	}// end of payment menu method

	private void new_Payment() throws IOException {
		System.out.println("\tYOU HAVE CHOOSE TO MAKE A PAYMET FOR A RESERVATION.");
		System.out.println("\t===================================================");
		if (this.rooms.roomAlreadyBooked().totalRooms() == 0) {
			System.out.println("\tNO OUTSTANDING PAYMENT AVAILABEL.\n");

			payments_Menu();
		}
		System.out.println("\tPLEASE ENTER YOUR EMAIL USED FOR BOOKING A ROOM.");
		String email = getEmail();
		if (email.toLowerCase().equals("cancel")) {
			hotelMenus.paymentsMenu();
			return;
		}
		if (!this.rooms.alreadyReserved(email)) {
			System.out.println("\tNO RESERVATION FOUND UNDER THIS EMAIL");
			new_Payment();
			return;
		}
		System.out.println("\tGUEST FINAL BILL RECEIPT");
		System.out.println("\t######################\n");
		System.out.println(rooms.getReservation(email));
		System.out.println("\t==========================");
		System.out.println("\tGRAND TOTAL: \u20ac" + this.rooms.getRoom(email).getRoomCost() + "\n");
		System.out.println("\tCONFIRM PAYEMNT RECEIVED?\n");
		System.out.println("\tPRESS 'y' || 'yes' TO CONFIRM.");
		System.out.println("\tPRESS 'n' || 'NO' TO RETURN THE PREVIOUS MENU");
		String response = keyboard.next();
		if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")
				|| response.toLowerCase().equals("ok")) {
			this.rooms.getRoom(email).cancelReservation();
			System.out.println("\tPAYMENT RECEIVED.THANK YOU FOR STAYING IN THE CIT HOTEL.....\n");
		} else {
			System.out.println("\tPAYMENT DID NOT RECEIVED | RETURNING TO PREVIOUS MENU.");
		}

		payments_Menu();
	}// end of new payment method

	private void view_Outstanding() throws MyCustomException {
		System.out.println("\tYOU HAVE CHOOSE TO SEE OUTSTANDING PAYMENT");
		System.out.println("\t==========================================");
		if (this.rooms.roomAlreadyBooked().totalRooms() == 0)
			System.out.println("\tNO OUTSTANDING PAYMENTS AVAILABLE.\n");
		for (Room r : this.rooms.roomAlreadyBooked()) {
			System.out.println("ROOM TYPE              : " + r.getName() 
							+ "\nOCCUPIED BY            : "
							+ r.getCurrentGuest().getName() + "\nTOTAL DUE ON CHECK OUT : \u20ac" + r.getRoomCost() + "\n");
		}

		try {
			payments_Menu();
		} catch (IOException e) {
			System.out.println(e);
			// e.printStackTrace();
		}

	}// end of view outstanding method

	private void saveMenu() throws MyCustomException {
		System.out.println("\tDO YOU WANT TO SAVE TO THE FILES - PRESS-  (Yes/y/ok");
		String response = keyboard.next();
		if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")
				|| response.toLowerCase().equals("ok")) {
			rooms.writeToTheFile();
		} else {
			System.out.println("\tYOU HAVE CHOOSE NOT TO SAVE!!");
		}
	}

	/*
	 * This method will verify email during booking based on cit email address
	 */

	private String getEmail() {
		String email;
		boolean emailValid = false;
		System.out.println("\tPLEASE ENTER EMAIL - IT MUST BE EITHER - @cit.ie | @mycit.ie");
		System.out.println("\tENTER CANCEL TO RETURN TO PREVIOUS MENU.");

		do {
			email = keyboard.next();
			if (email.equals("cancel")) {
				return email;
			}
			if (!email.contains("@") || !email.contains(".")) {
				System.out.println(
						"\tTHIS IS NOT A VALID EMAIL ADDRESS | PLEASE TRY AGAIN | ENTER (cancel) TO RETURN TO PREVIOUS MENU.");
			}
			if (email.contains("@")) {
				if (email.split("@")[1].toLowerCase().equals("mycit.ie")
						|| email.split("@")[1].toLowerCase().equals("cit.ie")) {
					emailValid = true;
					return email;
				} else {
					System.out
							.println("\tEMAIL MUST BE A CIT VALID EMAIL | ENTER (cancel) TO RETURN TO PREVIOUS MENU. ");
				}
			}
		} while (!emailValid);
		return email;
	}

}
