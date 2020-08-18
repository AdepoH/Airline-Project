import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Flightmanager{
	List<Flight> flights = new ArrayList<Flight>();
	Aircraftmanager aircraftmanager = new Aircraftmanager();
	File file;
	Writer writer;

	public Flightmanager(){
		file = new File("flight.txt");
        try{
            if (!file.createNewFile())
            {
                loadFlights();
            }
            writer = new FileWriter(file, true);
        }
        catch(IOException exception)
        {
            System.out.println("An error occurred while creating the flight.txt file");
            System.out.println(exception);
        }
    }


    private void loadFlights() throws FileNotFoundException {
        // file already exists
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            if (line.trim().isEmpty())
            {
                continue;
            }
            Flight flight = Flight.parse(line);
            flights.add(flight);
        }
        scanner.close();
	}

	public void show(Flight f){

		System.out.println(f.flightNum + " " + f.amount + " " + f.takeOffPoint + " " + f.departureTime + " " + f.destination + " " + f.arrivalTime + " ");
	}

	public void list(){

		for (Flight f: flights){

			show(f);	
		}

	}

	
	public void create(String flightNum, double amount, String takeOffPoint, String departureTime, String destination, String arrivalTime) {
		Aircraft a = aircraftmanager.find(" flightNum");
		if (a == null) {
			System.out.println( "Aircraft " +  a + " can not be found");
			return;
		}

		Flight f = new Flight(flightNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
		flights.add(f);
		try {
            if (writer == null)
            {
                throw new IOException("The writer could not be initialized");
            }
            writer.append(f.toString() + System.lineSeparator());
            writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
	}

	 public Flight find(String flightNum){
      for(Flight f: flights){
          if(f.flightNum.equals (flightNum)){
              return f;
          }
      }
        return null;
    }
    
      public void findAir(String flightNum){
        Flight flig  = find(flightNum);
        if(flig == null){
            System.out.printf("There is no Flight %s  among the Aircrafts...\n",flightNum); 
            return;
        }
        System.out.printf("%s %d %s %s %s %s  \n", flig.flightNum, flig.amount, flig.takeOffPoint,flig.departureTime, flig.destination, flig.arrivalTime);
    }

    public void upstringdOuput() {
        try{
            writer = new PrintWriter(file);
            
            for(Flight f: flights){
                writer.append(f.toString() + System.lineSeparator());
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());      
        }
    }

    public void upstring (String flightNum, double amount, String takeOffPoint, String departureTime, String destination, String arrivalTime ){
        Flight flig = find(flightNum);
        if(flig == null){
            System.out.printf("There is no Flight %s  among the Flight...\n", flightNum);
            return;
        }
        flig.flightNum = flightNum;
        flig.amount = amount;
        flig.takeOffPoint = takeOffPoint;
        flig.departureTime = departureTime;
        flig.destination = destination;
        flig.arrivalTime = arrivalTime;
        upstringdOuput();
    }

	public void delete(String flightNum){
		Flight flight = find(flightNum);
        if(flight == null){
            System.out.printf("There is no Flight %s  among the Flight...\n", flightNum);
            return;
        }
        flights.remove(flight);
        upstringdOuput();
	}
	/*Flight.remove(f);*/
}	