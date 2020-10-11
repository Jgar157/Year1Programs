/* Author: Jairo Garciga
 * Date: 3/22/20
 * The Car class is used to make unique vehicles each time
 * It also uses static variables as accumulators for totals.
 */


public class Car {
	
	//These variables are declared static so that they can exist out of a specific class.
	private static double totalCashCost = 0;
	private static double totalCardCost = 0;
	private static double totalElectronicCost = 0;
	
	//Each of these methods are used to get their respective total.
	static double getTotalCashCost() {
		return totalCashCost;
	}
	
	static double getTotalCardCost() {
		return totalCardCost;
	}
	
	static double getTotalElectronicCost() {
		return totalElectronicCost;
	}
	
	//Declaration and instantiation of all variables for future use.
	private int axleCount = 0;
	private String vehicleType = "";
	private String paymentType = "";
	
	//Variables for cost and payment
	//currentCost keeps track of the cost and transforms it with discounts.
	private double finalPayment = 0;
	private double currentCost = 0;
	
	//[0]=1 Axle, [1]=<3 Axles, [2]=3+ Axles
	private double[] axleCost = new double[3];
	//[0]=EV discount, [1]=Hybrid discount, [2]=Electronic discount, [3]=Convenience fee

	private double evDiscount;
	private double hybridDiscount;
	private double electronicDiscount;
	private double convenienceFee;
	
	
	//Constructor that takes three inputs
	public Car(String carType, int axles, double[] axleCost, double[] priceModifiers, String paymentType) {
		
		this.vehicleType = carType;
		
		//Calculates all the discounts
		evDiscount = 1 - priceModifiers[0];
		hybridDiscount = 1 - priceModifiers[1];
		electronicDiscount = 1 - priceModifiers[2];
		convenienceFee = 1 + priceModifiers[3];
		
		this.axleCost = axleCost;
		this.axleCount = axles;
		//This variable is a backwards counter, and it's used to check how many axles a
		//vehicle has and at the very end if greater than 3, it's just multiplied by the cost per axle
		//for every axle after 3.
		int currentAxleCounter = axles;
		
		
		//Every car will have at least one axle
		currentCost = this.axleCost[0];
		currentAxleCounter -= 1;
		
		
		if (axleCount > 1){
			//2 Axles
			if (axleCount == 2) {
				currentCost += this.axleCost[1];
				currentAxleCounter -= 1;
			}
			//3 Axles
			else {
				currentCost += this.axleCost[1] * 2;
				currentAxleCounter -= 2;
				
				//Adds the cost for more than 3 axles for each axle
				if (axleCount > 3){
					currentCost += this.axleCost[2] * currentAxleCounter;
				}
			}
		}
		
		//Implements the discount for EV
		if (this.vehicleType.equalsIgnoreCase("EV")) {
			currentCost = currentCost * evDiscount;
		}
		
		//Implements the discount for Hybrid
		if (this.vehicleType.equalsIgnoreCase("Hybrid")) {
			currentCost = currentCost * hybridDiscount;
		}
		
		this.paymentMethod(paymentType);
		
	}
	
	//Checks to see which payment type the user input and then sets the appropriate cost to the final Cost.
	//Also introduces the discount amount based on the payment type
	public void paymentMethod(String paymentType) {
		if (paymentType.equalsIgnoreCase("Cash")) {
			finalPayment = currentCost;
			totalCashCost += finalPayment;
		}
		
		else if(paymentType.equalsIgnoreCase("Card")) {
			finalPayment = currentCost * convenienceFee;
			totalCardCost += finalPayment;
		}
		
		//Automatically chooses electronic if the input for the payment is bad
		else {
			finalPayment = currentCost * electronicDiscount;
			totalElectronicCost += finalPayment;
		}
	}
	
	//A getter for the tollRate so that the main class can access it.
	public double getTollRate() {
		return finalPayment;
	}
}