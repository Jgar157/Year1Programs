/**
 * This is the Runner class, it is the skeleton for the entire project.
 * @author Jairo Garciga
 */

import java.util.*;
import java.io.*;

public class CDCollectionRunner {

	private CDCollection cdCollection;
	private int amountOfCD;
	File file;
	Scanner scanner = new Scanner(System.in);
	
	public CDCollectionRunner() {
		
		//Creating the File so that it is not written over
		this.file = new File("CDCollectionFile");
		
		
		
		boolean endProgram = false;
		System.out.println("Welcome to CD Collection!");
		//Ask for load or input
		
		System.out.println("Would you like to take input from a file? (Yes or no)");
		String tempAnswer = scanner.nextLine();
		
		//Checks if the user wants to read from a file or z
		if(tempAnswer.equalsIgnoreCase("Yes")) {
			this.retrieveFromFile();
		} else {
		System.out.println("How many CDs will you be inputting?");
		amountOfCD = scanner.nextInt();
		
		cdCollection = new CDCollection(amountOfCD);
		}
		
		System.out.println("You have finished inputting CDs");
		//Player is given options on what to do
		String tempAnswer1 = "";
		while (!endProgram) {
			tempAnswer1 = "";
			
			//Hoping this clears up all weird skips with scanner
			System.out.println("Please press enter, maybe twice!");
			if(this.scanner.hasNextLine()) {
				this.scanner.nextLine();
				this.scanner.nextLine();
			}
			
			//Questions
			System.out.println("What would you like to do now?");
			System.out.println("1. See stats for one of the CDs");
			System.out.println("2. See stats for entire collection");
			System.out.println("3. Save collection to file");
			System.out.println("4. Exit program");
			
			tempAnswer1 = this.scanner.nextLine();
			
			
			
			//Checking for what the answer is.
			if (tempAnswer1.equalsIgnoreCase("1")) {
				this.getCDInfo();
			} else if (tempAnswer1.equalsIgnoreCase("2")) {
				this.getCollectionInfo();
			} else if (tempAnswer1.equalsIgnoreCase("3")) {
				this.saveToFile();
			} else if (tempAnswer1.equalsIgnoreCase("4")) {
				System.out.println("Now exiting, thank you!");
				scanner.close();
				endProgram = true;
			} else {
				System.out.println("The value input is not acceptable.");
			}
		}
	}
	
	//Gets info from a specific CD using CDCollection getCDInfo
	public void getCDInfo() {
		System.out.println("Which CD # would you like to access?");
		int tempNum = scanner.nextInt()-1;
		
		if (tempNum < this.amountOfCD) {
			this.cdCollection.getCDInfo(tempNum);
		}
	}
	
	//Same as prior method
	public void getCollectionInfo() {
		this.cdCollection.fullListReport();
		this.cdCollection.getCollectionTotalTime();
	}
	
	//Calls the method in CDCollection
	public void saveToFile() {
		this.cdCollection.saveToFile(this.file);
	}

	//Retrieves infro from the CDCollectionFile
	public void retrieveFromFile() {
		ObjectInputStream iStream = null;
		int tempAmt;
		
		
		///Variable setup
		String tempAlbum;
		String tempName;
		String tempCreator;
		String tempGenre;
		int tempMinutes;
		int tempSeconds;
		
		SongCD[] cdList;
		try {
			iStream= new ObjectInputStream(new FileInputStream(this.file));
			tempAmt = iStream.readInt();
			this.amountOfCD = tempAmt;
			cdList = new SongCD[tempAmt];
			
			for (int i = 0; i < tempAmt; i++) {
				
				//Sets all of the variables up
				tempAlbum = iStream.readUTF();
				tempName = iStream.readUTF();
				tempCreator = iStream.readUTF();
				tempGenre = iStream.readUTF();
				tempMinutes = iStream.readInt();
				tempSeconds = iStream.readInt();
				
				//Creates the songCD, it gets cast to CD later anyways
				cdList[i] = new SongCD(tempAlbum, tempName, tempCreator, tempGenre,
						tempMinutes, tempSeconds);
			}
			iStream.close();
			this.cdCollection = new CDCollection(tempAmt, cdList);
			
			//Adds each variable for each CD into the proper arrayLists
			//Solves issue of nothing appearing when checking for overall stats
			for (int j = 0; j < tempAmt; j++) {
				this.cdCollection.addToAlbumList(cdList[j].getAlbumName());
				this.cdCollection.addToNameList(cdList[j].getName());
				this.cdCollection.addToCreatorList(cdList[j].getCreator());
				this.cdCollection.addToGenreList(cdList[j].getGenre());
			}
			
			
			
		} catch(IOException e) {
			System.out.println("There was an error loading the information from the file.");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
