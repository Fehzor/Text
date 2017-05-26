/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import java.util.ArrayList;
import text.Actors.Instances.Door;
import text.Environment.Environment;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class RoomWorld extends World{
    public Room R;
    public World outside;
    
    public RoomWorld(){
        super();
        
        this.E = new Environment(Environment.TYPE_NONE);
    }
    
    public int addRoom(Room Rom){
        int ret = super.addRoom(Rom);
        this.R = Rom;
        R.block(Room.SOUTH,4);
        R.block(Room.EAST,4);
        R.block(Room.WEST,4);
        return ret;
    }
    
    public boolean needsRooms(){
        if(R == null){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean adjacent(int a, int b){
        if(a == 1 && b == 1) return true;
        return false;
    }
    
    public ArrayList<Integer> adjacentList(int a){
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(0);
        return ret;
    }
    
    public void playerOutOfBounds(int dir){
        switchRoom(0);
    }
    
    public int getLinkRoom(){
        return -1;
    }
    
    public void displayMap(){
        String[]ret=new String[4];
        ret[0] = "" + name;
        ret[1] = "[Room]--> Doorway --> Outside;";
        ret[2] = "-OR-";
        ret[3] = "Enter--> Return --> Return to Main";
        this.display(ret);
    }
    
}
