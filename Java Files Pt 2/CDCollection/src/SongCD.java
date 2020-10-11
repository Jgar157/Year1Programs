/**
 * SongCD is the child of CD and adds on albumName.
 * @author Jairo
 *
 */

public class SongCD extends CD{

	private String albumName = "None specified";
	private int secondsTotal = 0;
	
	//Constructor if everything is given
	public SongCD(String albumName, String name, String artist, 
			String genre, int minutes, int seconds) {
		super(name, artist, genre, minutes, seconds);
		this.albumName = albumName;
		this.secondsTotal = this.getMinutes()*60 + this.getSeconds();
	}
	
	//Constructor if no time is given
	public SongCD(String albumName, String name, String artist, String genre) {
		super(name, artist, genre);
		this.albumName = albumName;
	}
	
	//Constructor if no album
	public SongCD(String name, String artist, String genre, int minutes, int seconds) {
		super(name, artist, genre, minutes, seconds);
		this.secondsTotal = this.getMinutes()*60 + this.getSeconds();
	}

	//Kind of a testing method, not essential but does exist.
	public void getAllOutputs() {
		System.out.println("Album: " + this.getAlbumName());
		System.out.println("CD: " + this.getName());
		System.out.println("Artist: " + this.getCreator());
		System.out.println("Genre: " + this.getGenre());
		System.out.println("Time: " + this.getMinutes() + " minutes and " + this.getSeconds() +
				" seconds");
	}
	
	
	//Getters for each variable
	public String getAlbumName() {
		return this.albumName;
	}
	
	public int getSecondsTotal() {
		return this.secondsTotal;
	}
}
