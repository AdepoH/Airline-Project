public class Aircraft {

	int capacity;
	String regNum;
	String type;
	String name;

	public Aircraft(int capacity, String regNum, String type, String name){
		this.capacity = capacity;
		this.regNum = regNum;
		this.type = type;
		this.name = name;
	}

	public Aircraft(){
		
	}

	public Aircraft(String regNum){
		this.regNum = regNum;
	}

	public static Aircraft parse(String line) {
		String[] props = line.split("\t");
        int capacity = Integer.parseInt(props[0]);
        return new Aircraft(capacity, props[1], props[2], props[3]);
	}

	@Override
	public String toString() {
		return capacity + "\t" + regNum + "\t" + type + "\t" + name;
	}
}