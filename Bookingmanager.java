import java.util.*;
import java.io.*;

public class Bookingmanager{

	ArrayList<Booking> yourBookings;
    Flightmanager flightmanager;
    File file;
    Writer writer;
    Aircraftmanager aircraftmanager = new Aircraftmanager();

    public Bookingmanager(){
    	yourBookings = new ArrayList<Booking>();
    	file = new File("booking.txt");
    	try{
    		if (!file.createNewFile()) {
    			loadBookings();
    		}
    		writer = new FileWriter(file, true);
    	}
    	catch (IOException exception) {
    		System.out.println("An error occurred while creating the booking.txt file");
            System.out.println(exception);
    	}
    }
     private void loadBookings() throws FileNotFoundException {
        // file already exists
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            Booking booking = Booking.parse(line);
            yourBookings.add(booking);
        }
        scanner.close();
    }

    public Bookingmanager(Flightmanager flightmanager) {
    	this.flightmanager = flightmanager;
    }

	public void show(Booking b){
		System.out.println(b.bookingNum + " " + b.passengerID + " " + b.flightNum + " " + b.bookingDate + " " + b.seatNum);
	}

	public void list(){
		for (Booking b: yourBookings ) {
			show(b);	
		}
	}	

	public void create(int bookingNum, String passengerID, String flightNum , String bookingDate, int seatNum){
		flightmanager = new Flightmanager();
		Flight flights = flightmanager.find("flightNum");
		if (flights == null) {
			System.out.printf("flight %s cannot be found \n ", flightNum);
			return;		
		}
		Booking b = new Booking( bookingNum, passengerID , flightNum, bookingDate, seatNum);
		yourBookings.add(b);
		try{
			if (writer == null) {
				throw new Exception("The writer could not be initialized");				
			}
			writer.append(b.toString() + System.lineSeparator());
            writer.flush();
		}
		catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
	}

	public void updatedOuput() {
        try{
            writer = new PrintWriter(file);
            
            for(Booking b: yourBookings){
                writer.append(b.toString() + System.lineSeparator());
            }
            writer.flush();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());      
		}
    }

    public Booking find(String passengerID) {
        for(Booking b: yourBookings){
            if(b.passengerID.equals(passengerID)) {
                return b;
            }
        }
        return null;
    }

    public void findBook(String passengerID){
        Booking book = find(passengerID);
        if(book == null){
            System.out.printf("There is no Booking with %s Id in the Bookings...\n",passengerID); 
            return;
        }
        System.out.println(book.passengerID + " " + book.flightNum + " " + book.bookingDate + " " + book.seatNum);
    }

    public void update(String passengerID, String flightNum, String bookingDate, int seatNum) {
        Booking book = find(passengerID);
        if(book == null) {
            System.out.printf("There is no Booking with %s  Id in the Bookingss...\n", passengerID); 
            return;
        }
        book.passengerID = passengerID;
        book.flightNum = flightNum;
        book.bookingDate = bookingDate;
        book.seatNum = seatNum;
        updatedOuput();
    }

    public void removeBook(String passengerID){
        Booking book = find(passengerID);
        if(book == null){
            System.out.printf("There is no Booking with passengerID: %s  in the Bookings...\n", passengerID); 
            return;
        }
        yourBookings.remove(book);
        updatedOuput();
    }
}