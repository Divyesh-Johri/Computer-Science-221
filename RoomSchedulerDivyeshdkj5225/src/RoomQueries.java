
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author divye
 */
public class RoomQueries {
    
    private static Connection connection;
    
    //getAllPossibleRooms
    private static ArrayList<String> rooms = new ArrayList<String>();
    private static PreparedStatement getRoomsList;
    private static PreparedStatement checkRooms;
    private static ResultSet resultSet;
    private static ResultSet dateSet;
    
    //getAllRooms
    private static PreparedStatement getRooms;
    
    //addRoom
    private static PreparedStatement addRooms;
    
    //dropRoom
    private static PreparedStatement drop;
    
    public static ArrayList<String> getAllPossibleRooms(int seats, String date)
    {
        connection = DBConnection.getConnection();
        try
        {
            getRoomsList = connection.prepareStatement("SELECT Name, Seats FROM Rooms ORDER BY Seats ASC");
            resultSet = getRoomsList.executeQuery();
            
            outerloop:
            while(resultSet.next())
            {
                // If the room exists in Reservations for given date, break
                checkRooms = connection.prepareStatement("SELECT Room, Date FROM Reservations");
                dateSet = checkRooms.executeQuery();
                while(dateSet.next()){
                    if(dateSet.getString(1).equals(resultSet.getString(1)) && dateSet.getString(2).equals(date))
                        continue outerloop;                    
                }
                // Otherwise, update rooms list if applicable
                int sts = resultSet.getInt(2);
                if (resultSet.getInt(2) >= seats)
                    rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return rooms;        
    }
    
    public static ArrayList<RoomEntry> getAllRooms(){
        ResultSet rs;
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        try{
                       
            getRooms = connection.prepareStatement("SELECT Name, Seats FROM Rooms ORDER BY Seats ASC");
            rs = getRooms.executeQuery();
            
            while(rs.next()){
                    rooms.add(new RoomEntry(rs.getString(1), rs.getInt(2)));
            }                      
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }       
        
        return rooms;
    }
    
    public static void addRoom(RoomEntry e){
        connection = DBConnection.getConnection();
        try
        {
            addRooms = connection.prepareStatement("INSERT INTO Rooms (Name, Seats) VALUES (?, ?)");
            addRooms.setString(1, e.getName());
            addRooms.setInt(2, e.getSize());
            addRooms.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // Refresh the waitlist to see if any fit the new room.
        WaitlistQueries.refreshWaitlist();
    }
    
    public static void dropRoom(RoomEntry room){
        //Obtain reservations for a room
        ArrayList<ReservationEntry> reservations = ReservationQueries.getReservationsByRoom(room.getName());        
        
        //Delete reservations
        for(ReservationEntry e : reservations){
            ReservationQueries.deleteReservationEntry(e);
        }
        
        //Remove the room
        connection = DBConnection.getConnection();
        try{
            drop = connection.prepareStatement("DELETE FROM Rooms WHERE Name=(?)");
            drop.setString(1,room.getName());
            drop.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        //Add old reservations as new reservations to see if they can fit
        for(ReservationEntry e : reservations){
            new ReservationEntry(e.getFaculty(), e.getDate(), e.getSeats());
        }       
        
        //Refresh waitlist to see if anyone in it fits
        WaitlistQueries.refreshWaitlist();        
    }
    
}
