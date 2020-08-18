import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Aircraftmanager{
	List<Aircraft> aircrafts;
	File file;
	Writer writer;

	    public Aircraftmanager() {
        aircrafts = new ArrayList<Aircraft>();
        file = new File("aircraft.txt");
        try{
            if (!file.createNewFile())
            {
                loadAircrafts();
            }
            writer = new FileWriter(file, true);
        }
        catch(IOException exception)
        {
            System.out.println("An error occurred while creating the aircraft.txt file");
            System.out.println(exception);
        }
    }


    private void loadAircrafts() throws FileNotFoundException {
        // file already exists
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            Aircraft aircraft = Aircraft.parse(line);
            aircrafts.add(aircraft);
        }
        scanner.close();
    }

	public void show(Aircraft a){

		System.out.printf("%d %s %s %s \n", a.capacity, a.regNum, a.type, a.name);

	}

	public void list(){
			for (Aircraft a: aircrafts){
				show(a);
			}
	}

	public void create(int capacity, String regNum, String type, String name) {
		try {
			Aircraft a = new Aircraft(capacity, regNum, type, name);
			aircrafts.add(a);
			if (writer == null) {
				throw new Exception("The writer could not be Initialized");		
			}

			writer.append(a.toString() + "\n");
			writer.flush();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public void delete(String regNum){
		Aircraft a = new Aircraft(regNum);
		aircrafts.remove(a);
	}

	public Aircraft find(String regNum){
			for (Aircraft a: aircrafts){
				if (a.regNum.equals(regNum)) {					
					return a;
				}		
			}
			return null;
	}

	 public void findAir(String regNum){
        Aircraft aircraft = find(regNum);
        if(aircraft == null){
            System.out.printf("There is no Aircfratf %s  amaong the Aircrafts...\n", regNum); 
            return;
        }
       System.out.printf("%d %s %s %s \n", aircraft.capacity, aircraft.regNum, aircraft.type, aircraft.name);
    }

    public void updatedOuput() {
        try{
            writer = new PrintWriter(file);
            
            for(Aircraft a: aircrafts){
                writer.append(a.toString() + "\n");
            }
            writer.flush();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());      
		}
    }

    public void update(int capacity, String regNum, String type, String name) {
        Aircraft aircr = find(regNum);
        if(aircr == null){
            System.out.printf("There is no Aircfratf %s  amaong the Aircrafts...\n", regNum); 
            return;
        }
        aircr.capacity = capacity;
        aircr.regNum = regNum;
        aircr.type = type;
        aircr.name = name;
        updatedOuput();
    }

    public void removeAir(String regNum){
        Aircraft aircr = find(regNum);
        if(aircr == null){
            System.out.printf("There is no Aircfratf %s  amaong the Aircrafts...\n",regNum); 
            return;
        }
        aircrafts.remove(aircr);
        updatedOuput();
    }



}