
public class PremiumSingle extends Room{	//premium single room rate: RM1650
	
	private static final double RATE = 1650;
	private static final int CAPACITY = 3;
	private static final String TYPE = "Premium Single";


	public PremiumSingle(String roomName) {
		super(roomName, CAPACITY, RATE);
	}
	
	@Override
	public String toString() {				//displays info of the room
		return String.format("%-25s %-25s %-25s %-25s %-25s %-25s" , this.getRoomName(), this.getCapacity() ,this.getNumberOfStudents(), this.getVacancies(), TYPE, "RM" + RATE + "/month" );
	}
	
}