
public class StandardSingle extends Room{		//standard single room, rate = RM980
	
	private static final double RATE = 980;
	private static final int CAPACITY = 6;
	private static final String TYPE = "Standard Single";


	public StandardSingle(String roomName) {
		super(roomName, CAPACITY, RATE);
	}
	
	@Override
	public String toString() {				//displays info of the room
		return String.format("%-25s %-25s %-25s %-25s %-25s %-25s" , this.getRoomName(), this.getCapacity() ,this.getNumberOfStudents(), this.getVacancies(), TYPE, "RM" + RATE + "/month" );
	}
	
}