
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
public class WaitlistQueries {
    
    private static Connection connection;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement getWaitlistByDate;
    
    public static void addWaitlistEntry(String name, String date, int seats)
    {
        connection = DBConnection.getConnection();
        try
        {
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            
            addWaitlist = connection.prepareStatement("INSERT INTO Waitlist (Faculty, Date, Seats, Timestamp) VALUES (?,?,?,?)");
            addWaitlist.setString(1, name);
            addWaitlist.setString(2, date);
            addWaitlist.setInt(3, seats);
            addWaitlist.setTimestamp(4, currentTimestamp);
            addWaitlist.executeUpdate();          
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
    }
    
    public static ArrayList<WaitlistEntry> getWaitlist(){
        ResultSet rs;
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try{
                       
            getWaitlistByDate = connection.prepareStatement("SELECT Faculty, Date, Seats, Timestamp FROM Waitlist ORDER BY Date, Timestamp DESC");
            rs = getWaitlistByDate.executeQuery();
            
            while(rs.next()){
                waitlist.add(new WaitlistEntry(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)));                
            }                      
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
}
