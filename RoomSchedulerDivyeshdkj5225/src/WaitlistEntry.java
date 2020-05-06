/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author divye
 */
public class WaitlistEntry {
    
    private String faculty;
    private String date;
    private int seats;
    private Object timestamp;
    
    public WaitlistEntry(String name, String dt, int size, Object tmp){
        faculty = name;
        date = dt;
        seats = size;
        timestamp = tmp;
    }
    
    public String getFaculty(){
        return faculty;
    }
    
    public String getDate(){
        return date;
    }
    
    public int getSeats(){
        return seats;
    }
    
    @Override
    public String toString(){
        return("".format("%s \t %s \t %s", faculty, date, seats));
    }
}
