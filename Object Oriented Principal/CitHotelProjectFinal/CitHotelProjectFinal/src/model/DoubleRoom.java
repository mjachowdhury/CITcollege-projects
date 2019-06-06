package model;

/**
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/29
 */

public class DoubleRoom implements RoomDetails {

	@Override
	public void displayDetail() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("ROOM NUMBER                 >>>1");
		System.out.println("ADVANCED                    >>>100.00\n");
		System.out.println("                     FEATURES OF THIS ROOM");
		System.out.println("--------------------------------------------------------------");
		System.out.println("ROOM TYPE                   >>> DOUBLE");
		System.out.println("ROOM CHARGES                >>> \u20ac100.00 PER DAY PER PERSON.");
		System.out.println("1. NO OF BEDS               >>>      2");
		System.out.println("2. ROOM MAX CAPACITY        >>>      2");
		System.out.println("---------------------------------------------------------------");
		System.out.println("                    ADDITIONAL FEATURES                        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("1. HEATING AVAILABLE");
		System.out.println("2. WIFI AVAILABLE");
		System.out.println("3. TV AVAILABLE");
		System.out.println("---------------------------------------------------------------");

	}

}
