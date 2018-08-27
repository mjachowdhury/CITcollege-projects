package model;

public class Single implements RoomDetails {

	@Override
	public void displayDetail() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("ROOM NUMBER                 >>>1");
		System.out.println("ADVANCED                    >>>75.00\n");
		System.out.println("                     FEATURES OF THIS ROOM");
		System.out.println("--------------------------------------------------------------");
		System.out.println("ROOM TYPE                   >>> SINGLE");
		System.out.println("ROOM CHARGES                >>> \u20ac75.00 PER DAY PER PERSON.");
		System.out.println("1. NO OF BEDS               >>>      1");
		System.out.println("2. ROOM MAX CAPACITY        >>>      1");
		System.out.println("---------------------------------------------------------------");
		System.out.println("                    ADDITIONAL FEATURES                        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("1. HEATING AVAILABLE");
		System.out.println("2. WIFI AVAILABLE");
		System.out.println("3. TV AVAILABLE");
		System.out.println("---------------------------------------------------------------");

	}

}
