import java.io.Serializable;

public class Student implements Serializable{

	private String fulName;			
	private String id;
	private Room room;
	private Booking booking;
	
	public Student(String pfulname, String pid)//declaring a constructor 
	{	
		this.fulName = pfulname;// this.variablename = parametername;
		this.id = pid;
	}
	
	
	public static String tableHeading() {  	//displays table heading when displaying lists
		return (String.format("%-25s %-25s %-25s %-25s %-25s", "Name:", "Stay duration:", "ID:", "Room name:", "Amount due:"));
	}
	
	@Override
	public String toString() {	//displays student info
		return (String.format("%-25s %-25s %-25s %-25s %-25s", fulName, booking.getStayDuration()+" months", id, room.getRoomName(), "RM" + booking.getAmountDue()));
	}
	

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public Booking getBooking() {
		return booking;
	}
	public String getFulName()	{
		return fulName;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Room getRoom()	{
		return room;
	}
	public String getID()	{
		return id;
	}
	
}

