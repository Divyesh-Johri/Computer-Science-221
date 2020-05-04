
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author divye
 */
public class Dates {
    
    private static Connection connection;
    private static ArrayList<String> dates = new ArrayList<String>();
    private static PreparedStatement addDates;
    private static PreparedStatement getDatesList;
    private static ResultSet resultSet;
    
    public static void addDates(String date)
    {        
    }
    
    public static ArrayList<String> getDatesList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> dates = new ArrayList<String>();
        try
        {
            getDatesList = connection.prepareStatement("SELECT Date FROM Dates");
            resultSet = getDatesList.executeQuery();
            
            while(resultSet.next())
            {
                dates.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return dates;
        
    }
    
}
