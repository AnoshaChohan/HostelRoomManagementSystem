import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects; //to check if null
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UniversityHostelManagementSystem {//class declaration
	
public static void main(String [] args){//main method declaration
		@SuppressWarnings("resource")
		
		ArrayList<Room> roomList = readFromFile();		
		Scanner scan = new Scanner (System.in);//Scanner declaration
		boolean invalid = false;
		 
		 out: while(true){
			 
				System.out.println("Choose an option - ");
				System.out.println("1 - Add rooms to list of rooms");
				System.out.println("2 - Show list of rooms");
				System.out.println("3 - Show list of students in a room");
				System.out.println("4 - Enter new students into a room");
				System.out.println("5 - Transfer student to a different room");
				System.out.println("6 - Remove student");
				System.out.println("7 - Find student");
				System.out.println("8 - Find vacancies");
				System.out.println("9 - Exit application");
				if(invalid) {
					invalid = false;
					System.out.println("\nInvalid input.");
				}
				System.out.println("\nEnter your option: ");
				
			String features = scan.nextLine();
			
	        switch (features) {
	            case "1": //add new rooms to roomList
	    				addRoomsToFile(roomList);
	    				break;
	    					    				 
	            case "2": //read to roomList from file, show list of rooms//
			    		displayRoomList(roomList);
	                    break;
			    		  
	            case "3": //showing list of students in a room;
	            		displayRoomList(roomList);
			    		showStudentsInARoom(roomList);
	                    break;
			    		
	            case "4": //Enter students into a room
			    		addStudentsToARoom(roomList);
	                    break;
	            case "5":
	            		changeRoom(roomList);
	            		break;
	                    
	            case "6": //Remove students
		            	removeStudents(roomList);
		            	break;
	                    
	            case "7"://Find student by name
		            	findStudent(roomList,false);
		            	break;
		            	
	            case "8"://find room with >=1 vacancies
		            	findVacancy(roomList);
		            	break;
	            case "9":
	            	System.out.println("Thank you!");
	            	break out;
		        default:
		        	invalid = true;
					continue out;
	        	}
	        
				System.out.println("\nPress enter to continue");
				scan.nextLine();
				System.out.println();
		 }
		
}//main method end bracket	
	


///OTHER METHODS//////////OTHER METHODS///////OTHER METHODS///////OTHER METHODS/////OTHER METHODS/////OTHER METHODS/////////OTHER METHODS/////////////////////////////////////////////////////////////	      
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Room> readFromFile() {//reads an arrayList from a file and returns the read arrayList from file
		ArrayList<Room> parameterRoomList = new ArrayList<Room>();
		try {
			FileInputStream fileis = new FileInputStream("file");
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fileis);
			parameterRoomList = (ArrayList<Room>) ois.readObject();//read from file to studList
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (EOFException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		return parameterRoomList;
	}
	
	public static void writeToFile(ArrayList<Room> parameterRoomList) {//receives an arrayList and writes the arrayList to file

		try {	
			   FileOutputStream fileOut = new FileOutputStream("file",false);
		       ObjectOutputStream out = new ObjectOutputStream(fileOut);
		       out.writeObject(parameterRoomList);//write roomList to file
		       out.close();
		       fileOut.close();
		     }catch (FileNotFoundException e) {
		       e.printStackTrace();
		     }catch (IOException e) {
		       e.printStackTrace();
		     }
		
	}

	public static void displayRoomList(ArrayList<Room> pRoomList) {//to display room list
			System.out.println();		
			System.out.println(Room.tableHeading());	//prints table heading, static method from the Room class
			
		    for(Room o:pRoomList) {
		    	System.out.println(o.toString());
			}
	
	}
	
	public static void addRoomsToFile(ArrayList<Room> pRoomList) {//add new rooms
		@SuppressWarnings("resource")
		Scanner scan = new Scanner (System.in);//Scanner declaration
		
		displayRoomList(pRoomList);
		
		System.out.println("\nEnter \"exit\" to stop adding rooms.");
		
			while(true) {
				String rName = null, rCapString = null; int rCap = 0;
				//   room Name, room Capacity in String, room Capacity in integer

				
				out: do {									 	//keeps asking for room name if user enters a room name which already exists
					System.out.println("\nInsert room name: 	(Type \"exit\" to quit)");	//enter a new room name
					rName = scan.nextLine().toUpperCase();
					if (rName.equalsIgnoreCase("exit"))
						return;
					for(Room r:pRoomList) {			//checks if the room name entered already exists
						if(rName.equalsIgnoreCase(r.getRoomName())) {	
							System.out.println("This room already exists.\n");
							continue out;	//restarts at "out" if room already exists
						}
					}
					break;					//if the room name is valid, break.
					
				}while(true);
				
				System.out.println("\n\nChoose the type of room - \n"
						+ "1 - Premium single (RM1650/month)\n"
						+ "2 - Premium twin sharing (RM1150/month)\n"
						+ "3 - Standard single (RM980/month)\n"
						+ "4 - Standard twin sharing (RM560/month)\n"
						+ "5 - Cancel\n\n"
						+ "Choose an option: \n");
				Room room;

				out2: while(true) {
					String option = scan.nextLine();
					switch(option) {
					case "1":
						room = new PremiumSingle(rName);
						break out2;
					case "2":
						room = new PremiumTwin(rName);
						break out2;
					case "3":
						room = new StandardSingle(rName);
						break out2;
					case "4":
						room = new StandardTwin(rName);
						break out2;
					case "5":
						return;
					default:
						System.out.println("Invalid option, please re enter input.");
						continue out2;
					}
				}
			   				
				pRoomList.add(room);//add the created room to Room list
				System.out.println("New room successfully registed: " + room.getRoomName()+"\n");
				writeToFile(pRoomList);
				}//end while loop
			
	}

	
    //showing list of students in a room
	public static void showStudentsInARoom(ArrayList<Room> pRoomList) {
		
			@SuppressWarnings("resource")
			Scanner scan = new Scanner (System.in);	
			
			int roomMatched = 0;//count no. of times user input matches a room name
			System.out.println("Type \"exit\" to quit.");
			while(true) {
				roomMatched=0;
			    System.out.println("\nEnter room name: 		(Type \"exit\" to quit.)"); 
				String roomName = scan.nextLine().toUpperCase();
				if (roomName.equalsIgnoreCase("exit"))
					return;
				
				for(Room o:pRoomList) {//searching through all the rooms in roomlist to find the requested room
					if((roomName).equals(o.getRoomName())) //if user-entered room name == a room name in roomList
					{
						roomMatched=1;
						if (o.getNumberOfStudents() != 0) //if number of students not zero,	
						{
							System.out.println();
							System.out.println(Student.tableHeading());
	
							for (Student s: o.getStudList()) 	
								{//print out student details (stored in room slot)
									System.out.println(s.toString());
									System.out.println();
								}
						}															
						else {System.out.println("There are no students in this room");}
					} 																		//no students inside room
				}
				if (roomMatched==0) 
					System.out.println("Room does not exist.");
				
				} 
	}


	//Enter students into a room
	@SuppressWarnings("resource")
	public static void addStudentsToARoom(ArrayList<Room> pRoomList){
			
			Scanner scan = new Scanner (System.in);		
			System.out.println("\nType \"exit\" to quit. ");

			out: while(true){
				findVacancy(pRoomList);
				System.out.println("Enter room name to add new students into room: \t(type \"exit\" to quit)"); 
				String roomName = scan.nextLine().toUpperCase();
				
				if (roomName.equalsIgnoreCase("exit")) 
					return;		
	
				for(Room r: pRoomList) { //looking through the roomList for the requested room 
						if (r.getRoomName().equals(roomName) && r.getVacancies() > 0){	//if room name exists and room is not full,
																						// register new student
							System.out.println("\n\nRoom " + r.getRoomName() + " is available.");
							System.out.print("\nInsert student's full name: ");
							String fulName = scan.nextLine().toUpperCase();				//student names will be in all caps
							if (fulName.equalsIgnoreCase("EXIT"))
								return;
							
							System.out.print("Insert student's id: ");
							String id = scan.nextLine();
							if (id.equalsIgnoreCase("exit"))
								return;
							
							int stay=0;
							boolean flag=true;
							System.out.print("Insert student's stay duration:(no. of months) ");
							while(flag) {								
								String stayDur = scan.nextLine();
								if (stayDur.equalsIgnoreCase("exit"))
									return;
								
								try {									//catching error in case user enters words
								stay = Integer.parseInt(stayDur);
								}
								catch(java.lang.NumberFormatException e){
									System.out.println("Please enter a number only: ");
									continue;
								}
								flag=false;
							}
							
							
							Student student = new Student(fulName,id);
							new Booking(stay, student, r );	//instantiates a booking object and passing in stay duration, student and room.
															//details explained in booking class.
							writeToFile(pRoomList);
							
							System.out.println("\n\nSuccessfully registered " + fulName + " into room " + r.getRoomName());
							//delete
							System.out.println("Stay duration: " + student.getBooking().getStayDuration()+ " months");
							System.out.println("Amount due: RM" + student.getBooking().getAmountDue()+ "\n\n");
							System.out.println("Press enter to continue");
							scan.nextLine();
							continue out;										//restart at "out"
						}
						
						else if(r.getRoomName().equals(roomName) && r.getVacancies() == 0){	// if room name exists but room is full
							System.out.println("\nRoom " + roomName + " is full!");
							continue out;														//restart at "out"
						}
				}
					System.out.println("\nRoom does not exist");

			}//while
	}///addStudentsToARoom() END BRACKET
	
	public static void changeRoom(ArrayList<Room> roomList) {
		
		Scanner scan = new Scanner(System.in);
		String id;
		String originalRoomName = null;
		Student student = null;
		boolean found;
		
		System.out.println("\n\nEnter the details of the student to be transfered:");
		if(findStudent(roomList,true)) {		//finds the student to be transfered using the find student method
			while(true) {
				found = false;
				System.out.println("Enter student ID to confirm: 	(Type \"exit\" to quit.)"); //asks the user to confirm by entering the
				id = scan.nextLine();								 //id of the student
				if(id.equals("exit"))
					return;
				for(Room r:roomList) {
					if(!Objects.isNull(r.findStudentId(id))) {
						student = r.findStudentId(id);
						originalRoomName = student.getRoom().getRoomName();
						found = true;
						break;
					}
				}
				if(!found) {
					System.out.println("Invalid student ID");
					continue;
				}
				out: while(true) {					//asks the user to choose a new room 
					findVacancy(roomList);			//for the student to be transfered in
					System.out.println("Select a new room to transfer: 		(Type \"exit\" to quit.)");
					String roomName = scan.nextLine();
					if(roomName.equalsIgnoreCase("Exit")) {
						return;
					}
					
					for(Room r:roomList) {
						if(r.getRoomName().equalsIgnoreCase(roomName) && r.isVacant() ) {	//if the room exists and is vacant,
							student.getRoom().removeStudent(student);
							new Booking(student.getBooking().getStayDuration(), student, r);
							writeToFile(roomList);
							System.out.println("Successfully transfered " + student.getFulName() 
							+ " from room " + originalRoomName + " to " + r.getRoomName());
							return;
						}
						else if(r.getRoomName().equalsIgnoreCase(roomName) && !r.isVacant()) { //if the room exists but not vacant,
							System.out.println("The room is full.");
							continue out;														//restart at "out"
						}
						
					}
					System.out.println("Room does not exist.");
					continue;
				}
			}
		}
		else
			return;
				
	}
	
	
	
	public static void removeStudents(ArrayList<Room> roomList){
		Scanner scan = new Scanner(System.in);
		String id;
		System.out.println("Enter details of student to remove:");
		if(findStudent(roomList,true)) {			//find the student to be removed by name or id
			while(true) {					//ask the user to enter student id to confirm removal
				System.out.println("Enter student ID to confirm removal: 		(type \"exit\" to quit)");
				id = scan.nextLine();
				
				if(id.equalsIgnoreCase("exit")) {
					return;
				}
				for(Room r:roomList) {			//this part checks if the student id entered exists
					if(!Objects.isNull(r.findStudentId(id))) {
						Student student = r.findStudentId(id);
						System.out.println("Succesfully removed "+ student.getFulName() 
						+ " from room " + student.getRoom().getRoomName());
						r.removeStudent(student);
						writeToFile(roomList);
						return;
					}
				}
				System.out.println("Invalid student ID.");
			}	
		}
		else 
			return;
	}
	
	public static boolean findStudent(ArrayList<Room> roomList, boolean runOnce){	//find student by name or by id
		Scanner scan = new Scanner(System.in);				//this function is also being used by the remove student method and change room method.
		boolean found;										//if the function is being used by the other two methods, runOnce is set to true.
		String temp;											//the boolean return type is to be used by the other two methods.
		int choice;												//when being used by the other two methods, this method will only run once, 
		while(true) {											//instead of repeating until the user types exit
			System.out.println("Type \"exit\" to quit");
			System.out.println();
			System.out.println("Choose an option - \n"
					+ "1 - Search student by name \n"
					+ "2 - Search student by ID\n\n"
					+ "Enter your option:");
			temp = scan.nextLine();
			if(temp.equalsIgnoreCase("exit")) {
				return false;
			}
			if(temp.equals("1")||temp.equals("2"))
				break;
			
			System.out.println("Invalid input, please try again: ");
		}
		choice = Integer.parseInt(temp);
		if(choice==1) {				//choice 1, search student by name
			while(true){			//keep looping until user types exit
				found = false;
				System.out.println("Enter student name: 	(type \"exit\" to quit)");
				String name ;
				name = scan.nextLine().toUpperCase();
				if(name.equalsIgnoreCase("EXIT")) {
					return false;
				}
				
				//first see if there is at least one student with the same name as the user entered
				for(Room r: roomList) {		//search through every room
					if(r.studentExists(name)) {	//the method findStudent displays the students who matches the name entered and
						found = true;		//returns a true if at least one student matches the name
						break;
					}
				}
				
				if(found) {					//if there is at least one student with the name entered by the user,
					System.out.println();	//then find all students with the same name.
					System.out.println("Student found: ");
					System.out.println(Student.tableHeading());
					for(Room r: roomList) {
						r.findStudent(name);
					}
					if(runOnce)		//if the function is being used by removeStudent method, it not restart the loop
						return true;		//it will return true if the student is found.
				}
				else {						//if there are no students with the name, display: student not found
					System.out.println("Student not found.");
					System.out.println();
				}
				System.out.println();
			}
		}
		else {			//choice 2, search student by id
			out: while(true) {
				System.out.println("Enter student ID: 	(type \"exit\" to quit)");
				String id;
				id = scan.nextLine();
				
				if(id.equalsIgnoreCase("exit")) {
					return false;
				}
				for(Room r:roomList) {
					if(!Objects.isNull(r.findStudentId(id))) {		//using the findstudentid instance method in the room class
						System.out.println();						//the instance method will return null if no student with the id is found
						System.out.println("Student found: ");
						System.out.println(Student.tableHeading());
						System.out.println(r.findStudentId(id).toString());
						System.out.println("\n");
						if(runOnce) {
							return true;
						}
						else
							continue out;
					}
				}
				System.out.println("Student not found.");
			}
		}
	}
	
	
	public static void findVacancy(ArrayList<Room>pRoomList) {
		System.out.println("List of available rooms are: ");
		System.out.println();
		System.out.println(Room.tableHeading());
		     
		for(Room r:pRoomList) {//searching through all the rooms in roomlist to find the requested room
			if(r.isVacant()) {	//if there is at least one vacancy, show the room name.
		    	System.out.println(r.toString());
			}
		}
	
	}
}
