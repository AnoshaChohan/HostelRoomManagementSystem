import java.io.Serializable;

public class Booking implements Serializable{
	private int stayDuration;
	private Room room;
	private double amountDue;
	
	public Booking(int stayDuration, Student student, Room room) {
		this.stayDuration = stayDuration;
		this.room = room;
		amountDue = room.getRate() * stayDuration;
		
		student.setBooking(this);		//assigns this booking to the student
		student.setRoom(this.room);		//assigns the room to the student
		room.addStudent(student);		//adds student into room
		
	}
	
	public double getAmountDue() {
		return amountDue;
	}
	
	public int getStayDuration() {
		return stayDuration;
	}
}