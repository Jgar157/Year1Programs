/**
 * This class is the blueprint for CD. It is a parent class for different future CDs, but it is fully
 * functional all on its own. It is also used for polymorphism in CDCollection.
 * @author Jairo
 *
 */


public class CD {
	
	private String name;
	private String creator = "None specified";
	private String genre = "None specified";
	private int minutes = 0;
	private int seconds = 0;

	//Constructor with everything included
	public CD(String name, String creator, String genre, int minutes, int seconds) {
		this.name = name;
		this.creator = creator;
		this.genre = genre;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	//Constructor with no time
	public CD(String name, String creator, String genre) { 
		this.name = name;
		this.creator = creator;
		this.genre = genre;
	}

	
	//Outputs all info cleanly
	public void getAllOutputs() {
		System.out.println("CD: " + this.getName());
		System.out.println("Creator: " + this.getCreator());
		System.out.println("Genre: " + this.getGenre());
		System.out.println("Time: " + this.getMinutes() + " minute(s) and " + this.getSeconds() +
				" second(s)");
	}
	
	//Getters for each variable
	public String getAlbumName() {
		return "None Specified";
	}
	
	public String getName() {
		return this.name;
	}
		
	public String getCreator() {
		return this.creator;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public int getMinutes() {
		return this.minutes;
	}
	
	public int getSeconds() {
		return this.seconds;
	}
}
