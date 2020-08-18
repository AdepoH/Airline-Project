public class Passenger{

	String passengerID;
	String name;
	String address;
	String phoneNum;

	public Passenger(String passengerID, String name, String address, String phoneNum){

		this.passengerID = passengerID;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
	}

	public Passenger(String passengerID){
		this.passengerID = passengerID;
	}
	public String getPassengerID() {
		return passengerID;
	}
	public String getName() {
		return name;
	}
	public String getAdress() {
		return address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	@Override
	public String toString(){
		return passengerID + "\t" + name + "\t" + address + "\t" + phoneNum + "\n" ;
	}

	public static Passenger parse(String line){
	String[] props = line.split("\t");
    return new Passenger(props[0], props[1], props[2], props[3]);	
	}

}