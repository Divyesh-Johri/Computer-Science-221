/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author divye
 */
public class RoomEntry {
    
    private String roomName;
    private int roomSize;
    
    public RoomEntry(String room, int seats){
        roomName = room;
        roomSize = seats;
    }
    
    public String getName(){
        return roomName;
    }
    
    public int getSize(){
        return roomSize;
    }
    
    @Override
    public String toString(){
        return roomName + " (" + roomSize + " seats)";
    }
    
}
