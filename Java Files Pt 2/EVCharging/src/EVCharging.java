/* Author: Jairo Garciga
 * Description: Takes input for several aspects of an electric car, and returns
 * Charging time, cost, and cost.
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class EVCharging {
	public static void main(String[] args) {
		
		
		//All numeric values are doubles for consistency and just in case a decimal value is inputed.
		//This program is limited because it cannot account for inputs of strings for double inputs.
		String vehicleName;
		double batteryCapacity;
		double avgMileEfficiency;
		
		String socketName;
		double socketVolts;
		double socketAmps;
		Socket socketType;
		double intakeRate;
		
		double energyEfficiency;
		double ppKwh;
		
		double batteryInitial;
		double batteryFinal;
		double batteryNeeded;
		
		double chargingTime;
		double chargingSpeed;
		double chargingCost;
		
		DecimalFormat twoPlaces = new DecimalFormat("#.##");
		
		Scanner mainScanner = new Scanner(System.in);
		
		System.out.println("Hello and welcome to this calculator for the charging of Electric Vehicles.");
		System.out.println("Please input your vehicle name.");
		
		vehicleName = mainScanner.nextLine();
		
		System.out.println("Wonderful, it seems that you have a " + vehicleName + ".");
		System.out.println("Now, please input the battery capacity of the vehicle in Kwh.");
		
		//In order to not get the "enter" after the double is inputed, nextLine is called right after.
		//This way it will not use the space after the double as input.
		batteryCapacity = mainScanner.nextDouble();
		mainScanner.nextLine();
		
		//Getting the Socket
		//Uses the Socket class to make a socket object.
		System.out.println("So far we have a " + vehicleName + 
				" with a capacity of " + batteryCapacity + " Kilowatts Hours.\n");
		System.out.println("Now for the socket. Please input the socket name.");
		socketName = mainScanner.nextLine();
		
		
		System.out.println("Now the amount of volts the socket outputs.");
		socketVolts = mainScanner.nextInt();
		
		
		System.out.println("And finally the output of amps for the socket.");
		socketAmps = mainScanner.nextInt();
		System.out.println("Thank you!");
		socketType = new Socket(socketName, socketVolts, socketAmps);
		//Done getting the socket information
		//Also automatically calculates the socket's output.
		
		
		System.out.println("What is the energy efficiency of the vehicle?");
		energyEfficiency = mainScanner.nextDouble();
		
		System.out.println("Finally, what is the price per Kilowattt Hour?");
		ppKwh = mainScanner.nextDouble();
		
		//Removes the clutter by creating space for the new text.
		System.out.println("\n\n\n\n\n");
		
		System.out.println("\nNow we'll need the current battery percentage of the car.");
		System.out.println("Please input the percentage as a decimal");
		
		batteryInitial = mainScanner.nextDouble();
		
		//This should be greater than batteryInitial
		System.out.println("Now please input the target battery amount.");
		batteryFinal = mainScanner.nextDouble();
		
		batteryNeeded = batteryFinal - batteryInitial;
		
		System.out.println("How many Kilowatt hours does your vehicle travel per 100 miles?");
		
		avgMileEfficiency = mainScanner.nextDouble();
		
		intakeRate = (socketType.getSocketOutput() * energyEfficiency)/1000;
		
		
		//Calculates the charging time by finding the total battery
		//needed then dividing it by the hourly rate of intake.
		chargingTime = (batteryCapacity * batteryNeeded)/intakeRate;
		//Rounds the chargingTime to 2 decimal places.
		chargingTime = Math.floor(chargingTime*100)/100;
		
		//For the sake of consistency, both chargingTime and chargingSpeed will be floored.
		
		//Calculates chargingSpeed by multiplying it by 100 miles then dividing by the KilowattHours.
		chargingSpeed = (intakeRate*100)/avgMileEfficiency;
		//Rounds speed to 2 decimal places. 
		chargingSpeed = Math.floor(chargingSpeed*100)/100;
		
		//Assigns the cost to the KilowattHours times cost per kwh all divided by the energy efficiency.
		chargingCost = ((batteryCapacity * batteryNeeded) * ppKwh)/energyEfficiency;
		
		//Gets the hours for the charging time.
		double chargingTimeHours = Math.floor(chargingTime);
		//Gets the minutes for the charging time.
		double chargingTimeMinutes = Math.ceil(((chargingTime * 100) % 100)/100 * 60);
		
		System.out.println("\n\n\n\n\n\n");
		//Now the final prints.
		System.out.println("Charging Time: " + chargingTime + " hours or " + chargingTimeHours + 
				"h " + chargingTimeMinutes + "m"  );
		System.out.println("Charging Speed: " + chargingSpeed + " mph");
		
		//Uses decimal format to add an extra decimal for the cents.
		System.out.println("Charging Cost: $" + twoPlaces.format(chargingCost));
		
		
		System.out.println("Thank you for using this program!");
	}
}
