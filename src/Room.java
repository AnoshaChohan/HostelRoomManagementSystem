import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{

	private String roomName;
	private int capacity;				
	private double rate;
	private int numberOfStudents;			
	private ArrayList<Student>studList;
	
	public Room(String roomName, int capacity, double rate)//declaring a constructor 
	{
		this.roomName = roomName;
		this.capacity = capacity;//max students in room
		this.rate = rate;
		studList = new ArrayList<Student>();		
		numberOfStudents = 0;				
		
	}
	
	
	public void addStudent(Student student)
	{
		studList.add(student);
		numberOfStudents++;
	}

	public void removeStudent(Student student)
	{
		studList.remove(student);
		numberOfStudents--;
	}
	
	public boolean studentExists(String name) {	//to check if at least one student has a name that matches with the user input.
		String student;
		
		out: for(Student s: studList) {		//checks if the student that the user entered is in the room
			student = s.getFulName();		//returns a true if yes, false otherwise.
			
				if(student.contains(name)) {
					return true;
				}
				continue out;
		}
		return false;
	}
	
	public void findStudent(String name){		//find students by name
		String student;								//this method will keep searching even after it finds one student,
													//because students might have same names.
		out: for(Student s: studList) {
			student = s.getFulName();
			
			if(student.contains(name)) {			
				System.out.println(s.toString());	//display student info
			}
			continue out;		//if the student is not found, restart at "out"
			
		}
		
	}
	
	public Student findStudentId(String id) { //find students by ID
		for(Student s: studList) {				//assuming student ID is unique,
			if(s.getID().equals(id)) {
				return s;
			}
		}
		return null;
	}
	
	
											//static function
	public static String tableHeading() {	//displays table heading when displaying lists
		return (String.format("%-25s %-25s %-25s %-25s %-25s %-25s" , "Rooms:", "Capacity:", "Tenants", "Vacancies", "Type", "Rate"));
	}
	@Override
	public String toString() {				//displays info of the room
		return String.format("%-25s %-25s %-25s %-25s %-25s %-25s" , roomName, capacity ,numberOfStudents, this.getVacancies(), rate);
	}
	
	
	
	//getter methods
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getRate() {
		return rate;
	}
	public String getRoomName()
	{
		return roomName;
	}
	public int getNumberOfStudents()
	{
		return numberOfStudents;
	}
	public ArrayList<Student> getStudList()
	{
		return studList;		
	}
	public int getCapacity()
	{
		return capacity;
	}
	public int getVacancies()
	{
		return capacity-numberOfStudents;
	}
	public boolean isVacant() {
		return getVacancies()>0;
	}
}