import java.util.*;	

public class Flight{

	String flightNum;
	double amount;
	String takeOffPoint;
	String departureTime;
	String destination;
	String arrivalTime;

	public Flight(String flightNum, double amount, String takeOffPoint, String departureTime, String destination, String arrivalTime){
		this.flightNum = flightNum;
		this.amount = amount;
		this.takeOffPoint = takeOffPoint;
		this.departureTime = departureTime;
		this.destination = destination;
		this.arrivalTime = arrivalTime;
	}

	public String getFlightNum() {
        return flightNum;
    }

    public double amount() {
        return amount;
    
    }

    public String takeOffPoint() {
        return takeOffPoint;
    }

    public String destination() {
        return destination;
    }
    public String departureTime() {
        return departureTime;
    }
    public String arrivalTime() {
        return arrivalTime;
    }
    @Override
    public String toString() {
        return flightNum + "\t" + amount + "\t" + takeOffPoint + "\t" + departureTime + "/t" + destination + "/t" + arrivalTime + "\n";
    }
    public static Flight parse(String line)
    {
        String[] props = line.split("\t");
        double amount = Double.parseDouble(props[1]);
        return new Flight(props[0], amount, props[2], props[3], props[4], props[5]);
    }

	public Flight(String flightNum){
		this.flightNum = flightNum;
	}

	public Flight(String flightNum, String departureTime, String arrivalTime){
		this.flightNum = flightNum;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;

	}
}