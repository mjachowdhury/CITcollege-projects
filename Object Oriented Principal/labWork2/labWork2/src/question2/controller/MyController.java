package question2.controller;

import question2.model.Actor;
import question2.model.Film;

public class MyController {

	private Actor[] actor;
	private int numberOfActor = 0;

	private Film[] myFilm;
	private int numberOfFilm = 0;

	public MyController() {
		actor = new Actor[2];
		myFilm = new Film[3];
	}

	// We expect the view / user interface to gather the name,address
	// and age of a actor which we wish to add to the system.

	public void addActor(String nameFromView, String addressFromView, int ageFromView) {
		// Create a actor object using the data received from the view
		Actor a = new Actor(nameFromView, addressFromView, ageFromView);
		// Put this new object into the array
		actor[numberOfActor] = a;
		numberOfActor++;

	}

	// If the user interface wants to display all actor. It calls this method
	// which will return the array of people to the user interface. It is then
	// the
	// user interface's job to display that data.

	public Actor[] displayAllActor() {
		return this.actor;
	}

	/*
	 * This method will return total number of actor in the system
	 */
	public int getNumberOfActorInArray() {
		return this.numberOfActor;
	}

	public void addActorFilmDetails(String filmNameFromView, String codeFormView) {
		Film f = new Film(filmNameFromView, codeFormView);
		myFilm[numberOfFilm] = f;
		numberOfFilm++;
		// once all films are entered(3) we assign the film array to each actor,
		// shallow copy
		if (numberOfFilm == 3) {
			actor[0].setMyFilms(myFilm);
			actor[1].setMyFilms(myFilm);
		}

	}

	public Film[] displayAllFilm() {
		return this.myFilm;
	}

	public int getNumberOfFilmInArray() {
		return this.numberOfFilm;
	}
}
