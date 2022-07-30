
public class PremiumTwin extends Room{	//premium twin sharing, rate: RM1150
	
	private static final double RATE = 1150;
	private static final int CAPACITY = 4;
	private static final String TYPE = "Premium Twin Sharing";


	public PremiumTwin(String roomName) {
		super(roomName, CAPACITY, RATE);
	}

	@Override
	public String toString() {				//displays info of the room
		return String.format("%-25s %-25s %-25s %-25s %-25s %-25s" , this.getRoomName(), this.getCapacity() ,this.getNumberOfStudents(), this.getVacancies(), TYPE, "RM" + RATE + "/month" );
	}
	
}