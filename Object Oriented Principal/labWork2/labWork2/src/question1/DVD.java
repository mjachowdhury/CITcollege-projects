package question1;

/**
 * This class hold the details of the DVD.
 *
 * @author (Mohammed J Alom)
 * @version (01/10/2017)
 */
public class DVD {
	// instance variables - replace the example below with your own
	private String title;
	private String genre;
	private String artist;

	public DVD() {
	}

	/**
	 * Constructor for objects of class DVD
	 */
	public DVD(String title, String artist) {
		// Initialize instance variables
		this.title = title;
		this.artist = artist;
	}

	// Getter and setter

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String toString() {
		return "DVD Details : " + title + " and Artist is " + artist;
	}

	public void print() {
		System.out.println(toString());
	}
}
