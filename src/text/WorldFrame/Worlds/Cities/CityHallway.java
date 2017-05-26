/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds.Cities;

import java.awt.Point;
import java.util.ArrayList;
import text.Actors.Player;
import text.Environment.Environment;
import text.Frame.TextDisplay;
import text.WorldFrame.Room;
import text.WorldFrame.World;


/**
 *
 * @author FF6EB4
 */
public class CityHallway extends World{
    ArrayList<Integer> tops;
    ArrayList<Integer> bottoms;
    int a,b,size;
    int roomCount = 0;
    int totalRooms;
    int pos = 0;
    boolean up = false;
    
    public CityHallway(int size){
        tops = new ArrayList<>();
        bottoms = new ArrayList<>();
        
        this.E = new Environment(Environment.TYPE_NONE);
        
        for(int i = 0; i < size; ++i){
            tops.add(i);
            bottoms.add(i+size);
        }
        
        a = size*2;
        b = size*2+1;
        this.size = size;
    
        totalRooms = size * 2 + 2;
    }
    
    public boolean needsRooms(){
        return roomCount < totalRooms;
    }
    
    public int addRoom(Room R){
        int ret = super.addRoom(R);
        
        roomCount++;
        
        return ret;
    }
    
    public void playerOutOfBounds(int dir){
        int room = pos;
        boolean left = false;
        boolean right = false;

        if(dir == Player.NORTH){
            room = room + size;
            room = room % (size*2);
        }

        if(dir == Player.SOUTH){
            room = room + size;
            room = room % (size*2);
        }

        if(dir == Player.WEST){
            if(room < size){
                room = room - 1;
                left = true;
            } else {
                room = room + 1;
                right = true;
            }
        }
        
        

        if(dir == Player.EAST){
            if(room < size){
                room = room + 1;
                right = true;
            } else {
                room = room - 1;
                left = true;
            }
        }
        
        if((left && room == -1) || 
                (left && room == size-1 && pos != b && pos != a)){
            room = a;
        }
        
        if((right && room == size*2) || 
                (right && room == size && pos != a && pos != b)){
            room = b;
        }
        
        if(pos == a){
            room = 0;
        }
        if(pos == b){
            room = size*2-1;
        }
        
        pos = room;
        //System.out.println(pos+" "+a+" "+b);
        
        try{
            switchRoom(room);
        } catch (Exception E){}
    }
    
    public void positionPlayer(int dir){
        super.positionPlayer(dir);
        //System.out.println("SOMEONE CARES");
        if((dir == Player.SOUTH || dir == Player.NORTH)){
            Player.The.y = TextDisplay.SCREEN_SIZE_Y - Player.The.y;
            Player.The.x = TextDisplay.SCREEN_SIZE_X - Player.The.x;
        }
    }
    
    public boolean adjacent(int a, int b){
        if(a - size == b || b - size == a){
            return true;
        }
        
        if(a+1 == b || b+1 == a){
            return true;
        }
        
        return false;
    }
    
    public ArrayList<Integer>adjacentList(int square){
        ArrayList<Integer> ret = new ArrayList<>();
        
        if(square !=0 && square != size){
            ret.add(square - 1);
        }
        
        if(square !=size-1 && square != size*2 -1){
            ret.add(square + 1);
        }
        
        if(up){
            ret.add(square+size);
        } else {
            ret.add(square-size);
        }
        
        return ret;
    }
    
    public void displayMap(){
        String[] ret = new String[5];
        ret[0] = "" + name;
        ret[1] = "";
         if (pos == a){
            ret[1] = "+PLAYER";
        } else if(!checkDoor(a)){
            ret[1] = "/SLIDE\\";
        } else{
            ret[1] = "/WORLD\\";
        }
        
        ret[2] = "\\--A--/";
        ret[3] = " ";
        if(pos == a || pos == b){
            ret[4] = "+SLIDE";
        } else {
            ret[4] = "Flipped = "+(pos > size-1);
        }
        
        for(int i = 0; i < size; ++i){

            if(pos == i ){
                ret[1]+= "[P]";
                ret[2]+= "["+i+"]"; 
            } else if(pos == size + i) {
                ret[1]+= "["+i+"]";
                ret[2]+= "[P]"; 
            } else {
                ret[1]+= "["+i+"]";
                ret[2]+= "["+i+"]";
            }
        }
        
         if (pos == b){
            ret[1] += "+PLAYER";
        } else if(!checkDoor(b)){
            ret[1] += "/SLIDE\\";
        } else {
            ret[1] += "/WORLD\\";
        }
        ret[2] += "\\--B--/";
        
        this.display(ret);
    }
    
    public int getLinkRoom(){
        if (!checkDoor(b)){
            //System.out.println("B"+b);
            return b;
        } else if(!checkDoor(a)){
            //System.out.println("A"+a);
            return a;
        } else {
            return -1;
        }
    }
}
