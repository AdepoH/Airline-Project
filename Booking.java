import java.util.*;

public class Booking{

	int bookingNum;
	String passengerID;
	String flightNum;
	String bookingDate;
	int seatNum;

	public Booking(int bookingNum, String passengerID, String flightNum , String bookingDate, int seatNum){

		this.bookingNum = bookingNum;
		this.passengerID = passengerID;
		this.flightNum = flightNum;
		this.bookingDate = bookingDate;
		this.seatNum = seatNum;
	}
	public Booking(){
		
	}


	public int getBookingNum() {
		return bookingNum;
	}
	public String getPassengerID() {
		return passengerID;
	}
	public String getFlightNum() {
		return flightNum;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public int getSeatNum(){
		return seatNum;
	}
	@Override
	public String toString(){
		return bookingNum + "\t" + passengerID + "\t" + flightNum + "\t" + bookingDate + "\n" + seatNum ;
	}

	public static Booking parse(String line){
	String[] props = line.split("\t");
	int bookingNum = Integer.parseInt(props[0]);
	int seatNum = Integer.parseInt(props[4]);
    return new Booking(bookingNum, props[1], props[2], props[3], seatNum);	
	}

}