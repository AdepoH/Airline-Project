import java.util.Scanner;
import java.util.*;
import java.io.*;

public class AirlineManagementSystem{

	static Scanner reader = new Scanner(System.in);

	Aircraft aircraft = new Aircraft();
	static Aircraftmanager aircraftmanager= new Aircraftmanager();

	static Flightmanager flightmanager = new Flightmanager();
	/*static Flightmanager flightmanager = new Flightmanager(aircraftmanager);*/

	/*Passenger passenger = new Passenger();*/
    static Passengermanager passengermanager = new Passengermanager();
    /*static Passengermanager passengermanager = new Passengermanager(bookingmanager);*/

    /*static Bookingmanager bookingmanager = new Bookingmanager();*/
    Booking booking = new Booking();
    static Bookingmanager bookingmanager = new Bookingmanager();

	public static void main(String [] args){
       
		boolean flag = true;
		while(flag){
			showMainMenu();
			String option = reader.nextLine();
			if(option.equals("0")) {
				flag=false;
			}else {
               showSubMenu(option);
			}
		}

	}

	public static void showMainMenu(){

		System.out.println("Enter 0 to exit");
		System.out.println("Enter 1 to Manage Aircrafts");
		System.out.println("Enter 2 to Manage Flights");
		System.out.println("Enter 3 to Manage Bookings");
		System.out.println("Enter 4 to Manage Passenger");
	}

	public static void showSubMenu(String option){

		if(option.equals("1")){
            showManageAirCraftsMenu();
            String subOption = reader.nextLine();
            handleManageAirCraftsAction(subOption);
		}
		else if (option.equals("2")) {
            showManageFlightMenu();
            String subOption = reader.nextLine();
            handleManageFlightAction(subOption);
        }
        else if (option.equals("3")) {
            showManagePassengerMenu();
            String subOption = reader.nextLine();
            handleManagePassengerAction(subOption);
        }
        else if (option.equals("4")) {
            showManageBookingMenu();
            String subOption = reader.nextLine();
            handleManageBookingsAction(subOption);
        }
	
	}

	public static void showManageAirCraftsMenu(){

		System.out.println("Enter 0 to return to Main Menu");
		System.out.println("Enter 1 to create Aircrafts");
		System.out.println("Enter 2 to list Aircraft");
		System.out.println("Enter 3 to Remove Aircraft");
		System.out.println("Enter 4 to Find an Aircraft");
		System.out.println("Enter 5 to Upstring an Aircraft");
	}

	public static void handleManageAirCraftsAction(String action){
		try{
			if(action.equals("0")){
				return;
				//showMainMenu();
			}
			else if(action.equals("2")){
				aircraftmanager.list();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Registration Number of Aircraft to remove: ");
				String regNum = reader.nextLine();
				aircraftmanager.removeAir(regNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Registration Number of Aircraft to Find: ");
				String regNum = reader.nextLine();
				aircraftmanager.findAir(regNum);
			}
			else if (action.equals("5")){
				System.out.println("Enter the Registration Number of Aircraft to upstring: ");
				String regNum = reader.nextLine();

				System.out.println("Enter the Aircraft Name?");
				String name = reader.nextLine();
				System.out.println("Enter the Aircraft Type?");
				String type = reader.nextLine();
				System.out.println("Enter the Aircraft Capacity?");
				int capacity = reader.nextInt();
				reader.nextLine();

				aircraftmanager.create(capacity, regNum, name, type);
				
			}
			else if (action.equals("1")){

				File file = new File("aircraft.txt");
				if(!file.exists()) {
					System.out.printf("File not %s does not exist!", file.getName());
				}
				System.out.println("Enter the Aircraft No: ");
				int capacity = reader.nextInt();
				System.out.println("Enter the Aircraft Registration Number: ");
				String regNum = reader.nextLine();
				System.out.println("Enter the Aircraft Name: ");
				String name = reader.nextLine();
				System.out.println("Enter the Aircraft Type: ");
				String type = reader.nextLine();
				reader.nextLine();
				aircraftmanager.create(capacity, regNum, name, type);
			}
		}
		catch(Exception ex){
          System.out.println(ex.getMessage());      
		}
	}
	public static void showManageFlightMenu() {

		System.out.println("Enter 0 to return to Main Menu");
		System.out.println("Enter 1 to Create Flight");
		System.out.println("Enter 2 to List Flights");
		System.out.println("Enter 3 to Remove Flight");
		System.out.println("Enter 4 to Find a Flight");
		System.out.println("Enter 5 to Upstring a Flight");
    }
    public static void handleManageFlightAction(String action) {
		try{
			if(action.equals("0")){
				showMainMenu();
			}
			else if(action.equals("2")){
				flightmanager.list();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Flight Number to remove?");
				String flightNum = reader.nextLine();
				flightmanager.delete(flightNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Flight Number of Flight to find?");
				String flightNum = reader.nextLine();
				flightmanager.find(flightNum);
			}
			else if(action.equals("5")){
				System.out.println("Enter the flight Number of Flight to upstring: ");
				String flightNum = reader.nextLine();

				System.out.println("Enter the Amount ");
	        	double amount = reader.nextDouble();

	        	System.out.println("Enter TakeOff Point ");
	        	String takeOffPoint = reader.nextLine();

				System.out.println("Enter String and Time (dd/MM/yyyy hh:mm:ss):");
				String departureTime = reader.nextLine();

				System.out.println("Enter the Destination");
	        	String destination = reader.nextLine();

				System.out.println("Enter String and Time (dd/MM/yyyy hh:mm:ss):");
				String arrivalTime = reader.nextLine();
				flightmanager.create(flightNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
			}
			else if (action.equals("2")){
				File file = new File("flight.txt");
				if(!file.exists()) {
				System.out.printf("File %s does not exist! ", file.getName());
				}
				Scanner reader = new Scanner(file);
				String flightNum = reader.nextLine();
				double amount = reader.nextDouble();
				String takeOffPoint = reader.nextLine();
				String departureTime = reader.nextLine();
				String destination = reader.nextLine();
				String arrivalTime = reader.nextLine();
				flightmanager.create(flightNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
				reader.close();
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
    }

    public static void showManagePassengerMenu() {

    	System.out.println("Enter 0 to return to Main Menu");
        System.out.println("Enter 1 to List Passengers");
        System.out.println("Enter 2 to Create Passengers");
        System.out.println("Enter 3 to Remove Passengers");
        System.out.println("Enter 4 to Find an Passengers");
        System.out.println("Enter 5 to Upstring an Passengers");
    }

	public static void handleManagePassengerAction(String action) {

        if (action.equals("0")) {
            showMainMenu();
        }
        else if (action.equals("1")) {
           passengermanager.list();
        }
        else if(action.equals("3")){
            System.out.println("Enter the Passenger ID of Passenger to remove: ");
            String passengerID = reader.nextLine();
            passengermanager.removePass(passengerID);
        }
        else if(action.equals("4")){
            System.out.println("Enter the Passenger ID of Passenger to find: ");
            String passengerID = reader.nextLine();
            passengermanager.findPass(passengerID);
        }
        else if(action.equals("5")){
            System.out.println("Enter the Passenger ID of Passenger to upstring: ");
			String passengerID = reader.nextLine();

            System.out.println("Enter the Passenger Name: ");
            String name = reader.nextLine();
            System.out.println("Enter the Passenger Address: ");
            String address = reader.nextLine();
            System.out.println("Enter the Passenger Phone: ");
            String phoneNum = reader.nextLine();
            reader.nextLine();
            passengermanager.update(passengerID, name, address, phoneNum);
        } 
        else if (action.equals("2")) {
            File file = new File("passenger.txt");
            if(!file.exists()) {
                System.out.printf("File not %s does not exist!", file.getName());    
            }   
            System.out.println("Enter the Passenger Passenger_ID: ");
            String passengerID = reader.nextLine();
            System.out.println("Enter the Passenger Name: ");
            String name = reader.nextLine();
            System.out.println("Enter the Passenger Address: ");
            String address = reader.nextLine();
            System.out.println("Enter the Passenger Phone Number: ");
            String phoneNum = reader.nextLine();
            reader.nextLine();
            passengermanager.create(passengerID, name, address, phoneNum);
        }
    }

   /* public static int getAvailableSeat(){
    	int getAvailableSeat = o;


    	return getAvailableSeat;
    }*/

    public static void showManageBookingMenu(){

		System.out.println("Enter 0 to return to Main Menu");
		System.out.println("Enter 1 to List Booking");
		System.out.println("Enter 2 to Create Booking");
	}

	public static void handleManageBookingsAction(String action){
		try{
			if(action.equals("0")){
				showMainMenu();
			}else if(action.equals("2")){
				bookingmanager.list();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Booking Number to remove?");
				String bookingNum = reader.nextLine();
				bookingmanager.removeBook(bookingNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Id of Booking to find?");
				String passengerID = reader.nextLine();
				bookingmanager.findBook(passengerID);
			}
			else if(action.equals("5")){
				System.out.println("Enter the passenger ID :");
				String passengerID = reader.nextLine();
				System.out.println("Enter the Flight Number: ");
				String flightNum = reader.nextLine();
				System.out.println("Enter  Booking Date and Time (dd/MM/yyyy hh:mm:ss): ");
				String bookingDate = reader.nextLine();
				System.out.println("Enter the Seat_No: ");
				int seatNum = reader.nextInt();
				reader.nextLine();
				bookingmanager.update(passengerID , flightNum, bookingDate, seatNum);
			}
			else if (action.equals("1")){
				File file = new File("booking.txt");
				if(!file.exists()) {
					System.out.printf("File %s does not exist! ", file.getName());
				}
				System.out.println("Enter the Booking Number:");
				int bookingNum = reader.nextInt();
				System.out.println("Enter the passenger ID to update: ");
				String passengerID = reader.nextLine();
				System.out.println("Enter the Flight Number: ");
				String flightNum = reader.nextLine();
				System.out.println("Enter Booking Date and Time (dd/MM/yyyy hh:mm:ss): ");
				String bookingDate = reader.nextLine();
				System.out.println("Enter the Seat Number: ");
				int seatNum = reader.nextInt();
				reader.nextLine();
	
				bookingmanager.create(bookingNum, passengerID, flightNum, bookingDate, seatNum);
			}
		}
        catch(Exception ex){
            System.out.println(ex.getMessage());      
		}
	}
}