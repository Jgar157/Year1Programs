/**
 * CDCollection contains an array of CDs and corresponding methods to gather information from them.
 * It also contains multiple arrayLists which keep track of albums, names, creators, and genres.
 * @author Jairo Garciga
 */


import java.util.*;
import java.io.*;

public class CDCollection {
	
	private CD[] cdCollection;
	Scanner scanner = new Scanner(System.in);
	
	private String tempInput;
	private int tempNumber;
	private int totalSeconds = 0;
	private int amountOfCD;
	private boolean hasRun = false;
	private boolean hasInitialized = false;
	
	//These lists add each unique string input.
	public ArrayList<String> albumList = new ArrayList<String>();
	public ArrayList<String> nameList = new ArrayList<String>();
	public ArrayList<String> creatorList = new ArrayList<String>();
	public ArrayList<String> genreList = new ArrayList<String>();

	public CDCollection(int amountInCollection) {
		//Save the total amount int he collection for saving to file later
		this.amountOfCD = amountInCollection;
		
		
		System.out.println("I will now begin asking for your input for each CD.");
		System.out.println("If you do not know the answer to one of the questions, please"
				+ " type in -1.");
		this.cdCollection = new CD[amountInCollection];
		
		for(int i = 0; i < amountInCollection; i++) {
				
				if(!hasInitialized) {
					System.out.println("Please press enter!");
					this.hasInitialized = true;
				}
				scanner.nextLine();
			System.out.println("Is CD #" + (i+1) + " a regularCD or a SongCD?");
			
			String tempInput = scanner.nextLine();
			
			if (tempInput.equalsIgnoreCase("regular") || tempInput.equalsIgnoreCase("CD")
					|| tempInput.equalsIgnoreCase("RegularCD")) {
				
				this.cdCollection[i] = this.createCD();
				
			} else {
				//Use of polymorphism to add new CD or SongCD
				this.cdCollection[i] = this.createSongCD();
			}
		}
	}
	
	//This constructor is used solely for file input
	public CDCollection(int amountInCollection, SongCD[] songArray) {
		this.cdCollection = new CD[amountInCollection];
		this.amountOfCD = amountInCollection;
		
		for (int i = 0; i < amountInCollection; i++) {
			this.cdCollection[i] = songArray[i];
		}
	}
	
	//Grabs the array for use above
	public CD[] getCDCollection() {
		return this.cdCollection;
	}
	
	//Creates CD or SongCD
	public CD createCD() {
		String name = "None specified";
		String creator = "None specified";
		String genre = "None specified";
		int minutes = -1;
		int seconds = -1;
		
		//Calls of the methods
		name = this.askName();
		creator = this.askCreator();
		genre = this.askGenre();
		minutes = this.askMinutes();
		seconds = this.askSeconds();
		
		
		return new CD(name, creator, genre, minutes, seconds);
		
	}
	
	public CD createSongCD() {
		String album = "None specified";
		String name = "None specified";
		String creator = "None specified";
		String genre = "None specified";
		int minutes = -1;
		int seconds = -1;
		
		//Calls of the methods
		album = this.askAlbum();
		name = this.askName();
		creator = this.askCreator();
		genre = this.askGenre();
		minutes = this.askMinutes();
		seconds = this.askSeconds();
		
		SongCD tempSong = new SongCD(album, name, creator, genre, minutes, seconds);
		
		return tempSong;
	}
	
	public void getCDInfo(int cdNum) {
		this.cdCollection[cdNum].getAllOutputs();
	}
	
	// Since the only difference between SongCD and CD is a potential album name,
	// I made these generic methods so that both can use them
	public String askName() {
		//Question for name
		String name = "None specified";
		System.out.println("What is the name of the CD?");
		this.tempInput = scanner.nextLine();
		
		if(!tempInput.equalsIgnoreCase("-1")) {
			name = tempInput;
		}
		
		this.addToNameList(name);
		return name;
	}

	public String askAlbum() {
		//Question for name
		String album = "None specified";
		System.out.println("What is the name of the album?");
		this.tempInput = scanner.nextLine();
		
		if(!tempInput.equalsIgnoreCase("-1")) {
			album = tempInput;
		}
		
		this.addToAlbumList(album);
		return album;
	}
	
	public String askCreator() {
		//Question for creator
		String creator = "None specified";
		System.out.println("Who made the CD?");
		tempInput = scanner.nextLine();
		
		if(!tempInput.equalsIgnoreCase("-1")) {
			creator = tempInput;
		}
		
		this.addToCreatorList(creator);
		return creator;
	}
	
	public String askGenre() {
		//Question for Genre
		String genre = "None specified";
		System.out.println("What is the genre?");
		tempInput = scanner.nextLine();
		
		if(!tempInput.equalsIgnoreCase("-1")) {
			genre = tempInput;
		}
		
		this.addToGenreList(genre);
		return genre;
	}
	
	public int askMinutes() {
		int minutes = -1;
		//Question for time
		System.out.println("How many minutes long is the CD?");
		tempNumber = scanner.nextInt();
		
		if(tempNumber > 0) {
			minutes = tempNumber;
		}
		return minutes;
	}
	
	public int askSeconds() {
		int seconds = -1;
		System.out.println("After removing the minutes, how many seconds remain on the CD? "
				+ "(CD is 5minutes and 20 seconds, put 20)");
		tempNumber = scanner.nextInt();
		
		if(tempNumber > 0) {
			seconds = tempNumber;
		}
		return seconds;
	}
	
	
	//Grabs the total time, excluding any cd with time less than 1
	public void getCollectionTotalTime() {
		for(CD cd: cdCollection) {
			if (cd.getMinutes() > 0 && cd.getSeconds() > 0) {
				this.totalSeconds += (cd.getMinutes() * 60);
				this.totalSeconds += (cd.getSeconds());
			}
		}
		int tempMinutes = this.totalSeconds / 60;
		int tempSeconds = this.totalSeconds % 60;
		System.out.println("Total Time: " + tempMinutes + " minutes and " + 
		tempSeconds + " seconds");
	}
	
	
	
	// Adds these input tot he arrayLists so that they can be accessed above
	public void addToAlbumList(String album) {
		this.albumList.add(album);
	}
	
	public void addToNameList(String name) {
		this.nameList.add(name);
	}

	public void addToCreatorList(String creator) {
		this.creatorList.add(creator);
	}
	
	public void addToGenreList(String genre) {
		this.genreList.add(genre);
	}
	
	
	
	// Gets each album, name, creator, or Genre
	public void fullListReport() {
		System.out.println();
		this.albumListReport();
		this.nameListReport();
		this.creatorListReport();
		this.genreListReport();
	}
	
	public void albumListReport() {
		System.out.print("Albums: ");
		for (int i = 0; i < this.albumList.size(); i++) {
			System.out.print(this.albumList.get(i) + " ");
		}
		System.out.println();
	}
	
	public void nameListReport() {
		System.out.print("Titles: ");
		for (int i = 0; i < this.nameList.size(); i++) {
			System.out.print(this.nameList.get(i) + " ");
		}
		System.out.println();
	}
	
	public void creatorListReport() {
		System.out.print("Creators: ");
		for (int i = 0; i < this.creatorList.size(); i++) {
			System.out.print(this.creatorList.get(i) + " ");
		}
		System.out.println();
	}
	
	public void genreListReport() {
		System.out.print("Genres: ");
		for (int i = 0; i < this.genreList.size(); i++) {
			System.out.print(this.genreList.get(i) + " ");
		}
		System.out.println();
	}
	
	public void saveToFile(File file) {
	ObjectOutputStream oStream = null;
		try {
			oStream = new ObjectOutputStream(new FileOutputStream(file));
			oStream.writeInt(this.amountOfCD);
			
			for(int i = 0; i < this.amountOfCD; i++) {
				CD tempCD = this.cdCollection[i];
				oStream.writeUTF(tempCD.getAlbumName());
				oStream.writeUTF(tempCD.getName());
				oStream.writeUTF(tempCD.getCreator());
				oStream.writeUTF(tempCD.getGenre());
				oStream.writeInt(tempCD.getMinutes());
				oStream.writeInt(tempCD.getSeconds());
			}
			oStream.close();
		} catch(IOException e) {
			System.out.println("There seems to have been an error writing the info to a file.");
			System.exit(1);
		}
		System.out.println("Save Complete!");
		
	}
	
}
