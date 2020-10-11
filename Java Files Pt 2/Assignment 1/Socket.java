/* Author: Jairo Garciga
 * Description: This creates a socket object which will store the name and power of the socket.
 */
public class Socket {

	private String socketName;
	private double socketVoltage;
	private double socketAmps;
	private double socketOutput;
	
	//Constructor
	//Takes input for the name, voltage, and amp of the socket.
	//Uses the getters in order to protect encapsulation.
	public Socket(String name, double voltage, double amps) {
		this.setSocketName(name);
		this.setSocketVoltage(voltage);
		this.setSocketAmps(amps);
		this.socketOutput = voltage * amps;
	}
	
	//The setter are only used to set the original values for the Socket in the constructor.
	//The getters help keep encapsulation and allow the main class to access the Socket's information.
	public void setSocketName(String name) {
		socketName = name;
	}
	
	public String getSocketName() {
		return socketName;
	}
	
	public void setSocketVoltage(double voltage) {
		socketVoltage = voltage;
	}
	
	public double getSocketVoltage() {
		return socketVoltage;
	}
	
	
	public void setSocketAmps(double amps) {
		socketAmps = amps;
	}
	
	public double getSocketAmps() {
		return socketAmps;
	}
	
	public double getSocketOutput() {
		return socketOutput;
	}
	
}
