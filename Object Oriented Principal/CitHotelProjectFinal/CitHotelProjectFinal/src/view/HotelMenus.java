package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */
public class HotelMenus {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void mainMenu() {
		System.out.println();
		System.out.println("\tWELCOME TO CIT HOTEL MANAGEMENT SYSTEM.");
		System.out.println("\t======================================");
		System.out.println("\t[1] DISPLAY ROOM FEATURES.");
		System.out.println("\t[2] DISPLAY AVAILABLE ROOMS.");
		System.out.println("\t[3] DISPLAY GUESTS.");
		System.out.println("\t[4] PROCESS RESERVATION.");
		System.out.println("\t[5] PROCESS PAYMENT.");
		System.out.println("\t[6] EXIT.\n");		 
		System.out.println("\tENTER YOUR CHOICE: ");
	}

	public void errorDisplay() {
		System.out.println("\nTHAT IS NOT A VALID CHOICE. | PLEASE CHOOSE AGAIN.\n");
	}

	public void displayGuests() {
		System.out.println("\tYOU HAVE CHOOSE TO SEE CURRENT OCCUPANCY.");
		System.out.println("\t=========================================");
		System.out.println("\t[1] ALL GUESTS.");
		System.out.println("\t[2] STUDENT GURSTS.");
		System.out.println("\t[3] LECTURER GUESTS.");
		System.out.println("\t[4] RETURN TO MAIN MENU.");
		System.out.println("\tENTER YOUR CHOICE: ");
	}

	public void reservationMenu() {
		System.out.println("\tWELCOME TO RESERVATION.");
		System.out.println("\t=======================");
		System.out.println("\t[1] TO MAKE NEW RESERVATION.");
		System.out.println("\t[2] TO VIEW EXISTING RESERVATION.");
		System.out.println("\t[3] TO CANCEL EXISTING RESERVATION.");
		System.out.println("\t[4] RETURN TO MAIN MENU.");
		System.out.println("\tENTER YOUR CHOICE: ");
	}

	public void paymentsMenu() {
		System.out.println("\tPAYMENT PROCESSING.");
		System.out.println("\t===================");
		System.out.println("\t[1] TO MAKE A PAYMENT.");
		System.out.println("\t[2] TO VIEW PAYMENT DUE.");
		System.out.println("\t[3] RETURN TO MAIN MENU.");
		System.out.println("\tENTER YOUR CHOICE: ");
	}

	// This Method will Display the Hotel Name along with a Welcome Note.
	public void screenHeader() // This Function will be display to customer one
								// time When the User Start the Program.
	{
		System.out.println("		****************************************");
		System.out.println("		*				       *");
		System.out.println("		*		WELCOME TO	       *");
		System.out.println("		*				       *");
		System.out.println("		*		HOTEL  CIT	       *");
		System.out.println("		*		  		       *");
		System.out.println("		****************************************");

	}

	// This Method Will Display the Hotel Details.
	public void introHotel() throws Exception // the Address of Hotel will be
												// printed and Show the Extra
												// Feature of Hotel.
	{
		System.out.println("\n\t         BISHOPTOWN, MODEL FARM ROAD,CORK CITY\n\t\t\t\t IRELAND");
		System.out.println("                          PH. NO: 00353-2100000");
		System.out.println("\n                         WELCOMES YOU..............");
		System.out.println(
				"\n\tHotel CIT Inn is one of the newest Hotel in Cork City. The Hotel is \n\tequipped with with all the general amenities and facilities that go \n\talong with memorable stay. Set amidst beautifully landscaped gardens, \n\tit proves to be a ideal dream destination for perceptive traveller.");
		System.out.println(
				"\n\tThe Hotel have well furnished rooms along with rooms providing pleasent \n\tviews of the city. The hotel satisfies the needs of business as well \n\tas the leisure traveller. All the rooms at the hotel are furnished \n\tbeautifully. All the rooms are fitted with amenities.");
		System.out.println("\n                            HOTEL AMENITIES .......\n");
		System.out.println("\t\t\t1. 100% POWER BACKUP.\n");
		System.out.println("\t\t\t2. AUTOMATIC LIFT.\n");
		System.out.println("\t\t\t3. AMPLE PARKING SPACE.\n");
		System.out.println("\t\t\t4. ROUND THE CLOCK SECURITY.\n");
		System.out.println("\t\t\t5. RUNNING HOT AND COLD WATER.\n");
		System.out.println("\t\t\t6. FREE INTERNET SERVICE\n");
		System.out.println("\t\t\t7. 24*7 ROOM SERVICE.\n");
		System.out.println("\t\t\t8. LAUNDRY SERVICE.\n");
		System.out.println("\nPRESS ENTER TO CONTINUE....");
		br.readLine();
	}

	public void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

	public void cls() {
		try {
			Runtime.getRuntime().exec("cmd /c cls");
		} catch (final Exception e) {
			System.out.print(e);
		}

	}

	public void clearScreen() {
		for (int i = 0; i < 5; i++) {
			System.out.println("\n");
		}
	}
}
