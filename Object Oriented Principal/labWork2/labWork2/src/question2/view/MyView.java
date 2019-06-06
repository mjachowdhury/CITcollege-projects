package question2.view;

import java.util.Scanner;

import question2.controller.MyController;
import question2.model.Actor;
import question2.model.Film;

public class MyView {
	// Final static variables
	private static final int ADD_A_ACTOR_DETAILS = 1;
	private static final int ADD_A_FILM_DETAILS = 2;
	private static final int DISPLAY_ALL_ACTOR_DETAILS = 3;
	private static final int DISPLAY_ALL_FILM_DETAILS = 4;
	private static final int EXIT = 5;

	private int menuChoice;
	private MyController theController;
	private Scanner keyboard;

	// This is the default constructor. I have added some of our own code in
	// here to create an instance of the controller which this view class will
	// use.
	public MyView() {
		this.theController = new MyController();
	}

	public void startUserInterface() {
		keyboard = new Scanner(System.in);
		displayMenu();
		// will keep looping until user put integer number between (1-5)
		while (!keyboard.hasNextInt()) {
			keyboard.next();
			System.out.println("Wrong Input!!! Please Enter Integer Number (1 - 5):");
			displayMenu();
		}
		menuChoice = keyboard.nextInt();

		// Keep looping while the user has not chosen to exit.
		while (menuChoice != EXIT) {

			switch (menuChoice) {

			case ADD_A_ACTOR_DETAILS: {
				// if user wanted to add more 2 actors then it will not allow to
				// add anymore.
				if (theController.getNumberOfActorInArray() == 2) {
					System.out.println("CANT NOT ADD ANY MORE ACTOR'S INTO THE SYSTEM.");
					break;
				}

				String actorName;
				String actorAddress;
				int actorAge;
				System.out.println("\tWE HAVE SELECTED TO ADD ACTOR DETAILS.");
				System.out.println("\t--------------------------------------");
				System.out.println("Please enter actor's name :");
				while (!keyboard.hasNext("[A-Za-z]+")) {
					System.out.println("Wrong Input!! Please Enter Character.");
					keyboard.next();
					System.out.println("Please enter actor's name :");
				}
				actorName = keyboard.next();

				System.out.println("Please enter actor's address :");
				while (!keyboard.hasNext("[A-Za-z]+")) {
					System.out.println("Wrong Input!! Please Enter Character.");
					keyboard.next();
					System.out.println("Please enter actor's address :");
				}
				actorAddress = keyboard.next();

				System.out.println("Please enter actor's age :");
				while (!keyboard.hasNextInt()) {
					System.out.println("Wrong Input!! Please Enter interger value.");
					keyboard.next();
					System.out.println("Please enter actor's age :");
				}
				actorAge = keyboard.nextInt();

				// The user interface has gathered the name,address and age. We
				// now send them to the controller for "processing".

				theController.addActor(actorName, actorAddress, actorAge);
				System.out.println("SUCCESSFULLY ADDED ACTOR DETAILS.");

				System.out.println();
				break;
			}
			case ADD_A_FILM_DETAILS: {

				if (theController.getNumberOfFilmInArray() == 3) {
					System.out.println("CANT NOT ADD ANY MORE FILM'S INTO THE SYSTEM.");
					break;
				}
				String filmName;
				String code;
				System.out.println("\tWE HAVE SELECTED TO ADD FILM DETAILS.");
				System.out.println("\t-------------------------------------");
				System.out.println("Please enter film name :");
				filmName = keyboard.next();

				System.out.println("Please enter film code :");
				code = keyboard.next();

				// The user interface has gathered the name and code. We now
				// send them to the controller for "processing".

				theController.addActorFilmDetails(filmName, code);
				System.out.println("SUCCESSFULLY ADDED FILM DETAILS.");
				System.out.println();
				break;
			}
			case DISPLAY_ALL_ACTOR_DETAILS: {
				// Do whatever needs to be done to lookup a actor
				// If the user wants to display all actor we ask the controller
				// to give us all the actor which it has stored in its array of
				// theActor.

				System.out.println("\tWE HAVE SELECTED TO SEE ALL ACTOR'S DETAILS.");
				System.out.println("\t--------------------------------------------");

				Actor[] theActor = theController.displayAllActor();
				int numberOfActorInArray = theController.getNumberOfActorInArray();

				if (numberOfActorInArray != 2) {
					System.out.println("YOU NEED TO ADD 2 ACTORS  AND 3 FILMS BEFORE YOU CAN SEE THE DETAILS.");
				} else {
					for (int i = 0; i < numberOfActorInArray; i++) {
						Actor currentActor = theActor[i];
						currentActor.print();
					}
				}

				break;
			}
			case DISPLAY_ALL_FILM_DETAILS: {
				// Do whatever needs to be done to lookup a film
				// If the user wants to display all film we ask the controller
				// to give us
				// all the film which it has stored in its array of theFilm.

				System.out.println("\tWE HAVE SELECTED TO SEE ALL FILM'S DETAILS.");
				System.out.println("\t-------------------------------------------");

				Film[] theFilm = theController.displayAllFilm();
				int numberOfFilmInArray = theController.getNumberOfFilmInArray();

				if (numberOfFilmInArray != 3) {
					System.out.println(
							"YOU HAVE TO ADD 2 ACTORS AND 3 FILMS ALL TOGATHER BEFORE YOU CAN SEE THE FILM DETAILS.");
				} else {
					for (int i = 0; i < numberOfFilmInArray; i++) {
						Film currentFilm = theFilm[i];
						currentFilm.print();
					}
				}
				break;
			}

			default:
				System.out.println("Choice must be between (1 - 5) integer number.");
			}

			displayMenu();
			menuChoice = keyboard.nextInt();

		}
		System.out.println("\tWE HAVE SELECTED TO EXIT THE PROGRAM.");
		System.out.println("\t-------------------------------------");
		System.out.println("\tTHANK FOR USING THE PROGRAM. GOODBYE!");
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("\tPLEASE CHOOSE THE FOLLOWING OPTIONS");
		System.out.println("\tChoice must be between (1 - 5) integer number.");
		System.out.println();
		System.out.println("\t---------------------------------------------");
		System.out.println("\t|(1).Add Actor Details.(maximum two actors) |");
		System.out.println("\t|(2).Add Film Details.(maximum three films) |");
		System.out.println("\t|(3).Display Actor Details.                 |");
		System.out.println("\t|(4).Display Film Details.                  |");
		System.out.println("\t|(5).Exit.                                  |");
		System.out.println("\t|-------------------------------------------|");
		System.out.println();

	}
}
