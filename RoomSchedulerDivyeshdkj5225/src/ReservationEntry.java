
import java.util.ArrayList;

/*
 * The faculty member will be assigned a room for the requested Date, if there are 
   seats available. The rooms will be assigned in a best fit manner. The faculty
   member should be assigned the smallest room that has enough seats for their 
   request. If there are no rooms available or no rooms with enough seats 
   available, the faculty member will be put on the wait list for that Date. 
   The waiting list must be maintained in the order the faculty members tried to
   reserve their rooms. 
 */

/**
 *
 * @author divye
 */
public class ReservationEntry {
    
    // If no reservation made, place on waitlist for closest room size
    private String faculty;
    private String room;
    private String date;
    private int seats;
    private Object timestamp;
    
    // When making new reservation entry
    public ReservationEntry(String name, String dt, int size){
        faculty = name;       
        date = dt;
        seats = size;
        room = getPossibleRoom();
        
        ReservationQueries.addReservationEntry(this);
    }
    
    // When inputting existing reservation entry
    public ReservationEntry(String name, String dt, int size, String rm, Object tmp){
        faculty = name;       
        date = dt;
        seats = size;
        room = rm;
        timestamp = tmp;
    }
    
    public String getPossibleRoom(){
                    
        // Obtain possible rooms. If list empty, add to waitlist
        ArrayList <String> rooms = RoomQueries.getAllPossibleRooms(seats, date);  

        if(rooms.isEmpty()){
            return null;
        } else {
            room = rooms.get(0);
            rooms.clear();
        }
        
        return room;
    }   
    
    public String getFaculty(){
        return faculty;
    }
    
    public String getRoom(){
        return room;
    }
    
    public String getDate(){
        return date;
    }
    
    public int getSeats(){
        return seats;
    }

    @Override
    public String toString(){
        return("".format("%s \t %s \t %s", faculty, room, date));
    }
}
