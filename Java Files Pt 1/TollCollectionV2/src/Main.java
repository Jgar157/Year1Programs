/* Author: Jairo Garciga
 * Date: 3/22/20
 * This main class takes input for a cars axles, type, payment type, 
 * and then computes the cost of the toll using the Car class.
 */

import java.util.Scanner;
import java.text.DecimalFormat;


public class Main {
	public static void main(String[] args) {
		
		//Create a scanner to use for inputs
		Scanner scanner = new Scanner(System.in);
		
		//Declare all necessary variables now
		//integers are used for whole numbers and for prices doubles
		int carAmount = 0;
		int numAxles = 0;
		int currentTollBooth = 0;
		int totalTollBooths = 0;
		double allBoothsTotal = 0;
		//More organized inputs when passed as an argument
		double[] axlePrices = new double[3];
		double[] costModifier = new double[4];
		//Nicer formatting to two decimal places
		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		String carType = "";
		String paymentType = "";
		
		//Gets input for toll booths
		System.out.println("How many toll booths are there?");
		totalTollBooths = scanner.nextInt();
		
		//Gets price for each set of axle counts
		System.out.println("Please input the price per axle for 1 axle, 2-3 axles, and 4+ axles, separating each input by a space");
		axlePrices[0] = scanner.nextDouble();
		axlePrices[1] = scanner.nextDouble();
		axlePrices[2] = scanner.nextDouble();
		
		//Gets the discounts
		System.out.println("Now, please input the discount for EV, Hybrid, and for Electronic payment, all with a space between them.");
		costModifier[0] = scanner.nextDouble();
		costModifier[1] = scanner.nextDouble();
		costModifier[2] = scanner.nextDouble();
		
		//Gets the fee
		System.out.println("Please input the convenience fee for using card/credit.");
		costModifier[3] = scanner.nextDouble();
		
		//Introductory Statements
		System.out.println("Welcome to this toll calculator for an unspecified Toll Booth.");
		System.out.println("This toll has " + totalTollBooths + " booths.");
		System.out.println("How many cars are visiting the toll?");
		
		carAmount = scanner.nextInt();
		//Creation of the array of vehicles
		Car[] vehicles = new Car[carAmount];
		//Creation of the array of booths
		double[] tollBooths = new double[totalTollBooths];
		
		System.out.println("We will now ask you to input information for each of the vehicles.");
		scanner.nextLine();
		
		//This for loop runs the entire program multiple times in order to make each car.
		for (int i = 0; i < carAmount; i++) {
			
			//Question and input for the type of car.
			System.out.println("For car " + (i+1) + ", what type of car is it? (EV, Hybrid, or Gasoline)");
			carType = scanner.nextLine();
			
			//The booth
			System.out.println("Which booth is this car visiting?");
			currentTollBooth = scanner.nextInt() - 1;
			
			//Input for the amount of axles.
			boolean goodAxles = false;
			
			//This loop checks to see if the input is a valid positive integer input.
			while (!goodAxles) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
				System.out.println("Please input the amount of axles in the vehicle. (Positive Integers only)");
				if (!scanner.hasNextInt()) {
					if (scanner.hasNextDouble()) {
						//This takes the bad input and prevents the loop from reusing it.
						double badInput = scanner.nextDouble();
						System.out.println("Bad Input");
					}
					else {
						//Had to take the bad input if a String otherwise the loop becomes an infinite loop.
						String badInput = scanner.nextLine();
						System.out.println("Bad Input");
					}
				}
				else {
					//Successful
					if ((numAxles = scanner.nextInt()) >= 1) {
					goodAxles = true;
					}
					//Negative input
					else {
						System.out.println("Bad Input");
					}
				}
			}
			
			
			//Payment Type
			scanner.nextLine();
			System.out.println("How will this vehicle pay? (Cash, Card, or Electronic?)");
			paymentType = scanner.nextLine();
			
			//Creation of a car object which is then added to the vehicles array for easy storage
			//Also assigns the toll rate of each car to the toll booth for a total later
			Car car = new Car(carType, numAxles, axlePrices, costModifier, paymentType);
			tollBooths[currentTollBooth] += car.getTollRate();
			allBoothsTotal += car.getTollRate();
			
			vehicles[i] = car;
			
			//Printing of the toll cost of each car for debugging and overall user info
			System.out.println("Car " + (i+1) + " has a toll cost of: $" + twoPlaces.format(car.getTollRate()));
			System.out.println("\n\n");
		}
		
		//This for loop runs for each toll booth that there is and prints out the total
		//amount of money that the toll booth has made
		for (int i = 0; i < tollBooths.length; i++) {
			System.out.println("Toll #" + (i+1) + " made a total of: $" + twoPlaces.format(tollBooths[i]));
		}
		System.out.println("All booths made a total of: $" +  twoPlaces.format(allBoothsTotal));
		
		System.out.println("\n");
		
		//Uses the static accumulators in the Car class to print out the
		//overall totals for each payment type
		System.out.println("Cash Amount Paid: $" +  twoPlaces.format(Car.getTotalCashCost()));
		System.out.println("Card Amount Paid: $" +  twoPlaces.format(Car.getTotalCardCost()));
		System.out.println("Electronic Amount Paid: $" +  twoPlaces.format(Car.getTotalElectronicCost()));
		scanner.close();
	}
}
