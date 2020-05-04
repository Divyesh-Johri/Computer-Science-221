
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author divye
 */
public class ReservationQueries {
    
    private static Connection connection;
    private static PreparedStatement addReservation;    
    private static PreparedStatement getReservationByDate;
    
    public static void addReservationEntry(ReservationEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {           
            if(entry.getRoom() == null){
                WaitlistQueries.addWaitlistEntry(entry.getFaculty(), entry.getDate(), entry.getSeats());
            } else {
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            
                addReservation = connection.prepareStatement("INSERT INTO Reservations (Faculty, Room, Date, Seats, Timestamp) VALUES (?,?,?,?,?)");
                addReservation.setString(1, entry.getFaculty());
                addReservation.setString(2, entry.getRoom());
                addReservation.setString(3, entry.getDate());
                addReservation.setInt(4, entry.getSeats());
                addReservation.setTimestamp(5, currentTimestamp);
                addReservation.executeUpdate();
            }          
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(String date){
        ResultSet rs;
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        try{
                       
            getReservationByDate = connection.prepareStatement("SELECT Faculty, Room, Date, Seats, Timestamp FROM Reservations");
            rs = getReservationByDate.executeQuery();
            
            while(rs.next()){
                if(rs.getString(3).equals(date)){
                    reservations.add(new ReservationEntry(rs.getString(1), rs.getString(3), rs.getInt(4), rs.getString(2), rs.getTimestamp(5)));
                }
            }                      
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return reservations;        
    }
    
}
