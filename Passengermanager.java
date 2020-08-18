import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Passengermanager{
	List<Passenger> passengerDetails;
	File file;
	Writer writer;


	 public Passengermanager() {
        passengerDetails = new ArrayList<Passenger>();
        file = new File("passenger.txt");
        try{
            if (!file.createNewFile())
            {
                loadPassengers();
            }
            writer = new FileWriter(file, true);
        }
        catch(IOException exception)
        {
            System.out.println("An error occurred while creating the passenger.txt file");
            System.out.println(exception);
        }
    }


    private void loadPassengers() throws FileNotFoundException {
        // file already exists
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            if (line.trim().isEmpty())
            {
                continue;
            }
            Passenger passenger = Passenger.parse(line);
            passengerDetails.add(passenger);
        }
        scanner.close();
    }



	
	public void show(Passenger p){
		System.out.println(p.passengerID + " " + p.name + " " + p.address + " " + p.phoneNum);
	}
	public void list(){
		for (Passenger p: passengerDetails){
		show(p);			
		}
	}
	public void create(String passengerID, String name, String address, String phoneNum){
		try{
			Passenger p = new Passenger(passengerID, name, address, phoneNum);
			passengerDetails.add(p);
			if (writer == null) {

				throw new IOException("The Writer could not be initialized");				
			}
			writer.append(p.toString() + System.lineSeparator());
            writer.flush();
		}
		catch(IOException ex){
        System.out.println(ex.getMessage());
        }

	}
	public Passenger find(String passengerID){
		for (Passenger p: passengerDetails) {
			if (p.passengerID.equals(passengerID)) {
				return p;
			}	
		}
		return null;
	}
	public void findPass(String passengerID){
       Passenger pass  = find(passengerID);
       if(pass == null){
       	System.out.printf("There is no Passenger %s  among the Passengers...\n",passengerID); 
       	 return;
        }
        System.out.printf("%s %s %s %d \n", pass.passengerID, pass.name, pass.address, pass.phoneNum);
    }

     public void updatedOuput() {
        try{
            writer = new PrintWriter(file);
            
            for(Passenger p: passengerDetails){
                writer.append(p.toString() + "\n");
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());      
        }
    }

    public void update (String passengerID,String name,String address,String phoneNum) {
        Passenger pass = find(passengerID);
        if(pass == null){
            System.out.printf("There is no Passenger %s  among the Passengers...\n",passengerID); 
            return;
        }
        pass.passengerID = passengerID;
        pass.name = name;
        pass.address = address;
        pass.phoneNum = phoneNum;
        updatedOuput();
    }

    public void removePass(String passengerID){
        Passenger pass = find(passengerID);
        if(pass == null){
            System.out.printf("There is no Passenger %s  among the Passengers...\n",passengerID); 
            return;
        }
        passengerDetails.remove(pass);
        updatedOuput();
    }  		
}
