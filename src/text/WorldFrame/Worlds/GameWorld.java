/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import java.util.ArrayList;
import text.Actors.Player;
import text.Environment.Environment;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class GameWorld extends World{
    public static int GAME_ROOM = 0;
    public static int GAME_POOL = 1;
    public static int PRIZE_POOL = 2;
    
    int room = 0;
    boolean locked = true;
    
    public GameWorld(){
        super();
        
        this.E = new Environment(Environment.TYPE_NONE);
    }
    
    public int addRooom(Room Rom){
        int ret = super.addRoom(Rom);
        
        return ret;
    }
    
    public void playerOutOfBounds(int dir){
        
        if(dir == Player.EAST){
           switchRoom(1); 
        }
        
        if(dir == Player.WEST){
           switchRoom(0); 
        }
        
        if(dir == Player.NORTH){
           switchRoom(0); 
        }
        
        if(dir == Player.SOUTH){
           switchRoom(2); 
        }
        
    }
    
    public void openTreasure(){
        this.getRoom(0).unblock(Room.SOUTH);
        locked = false;
    }
    
    public int getLinkRoom(){
        return -1;
    }
    
    public boolean needsRoom(){
        if(this.roomCount() < 3) return true;
        return false;
    }
    
    public boolean adjacent(int a, int b){
        if(a == 1 && b == 2) return false;
        return true;
    }
    
    public ArrayList<Integer> adjacentList(int a){
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(0);
        ret.add(1);
        ret.add(2);
        return ret;
    }
    
    public void displayMap(){
        String[]ret=new String[6];
        ret[0] = "" + name;
        ret[1] = "[Board]---[Pieces]";
        ret[2] = "  |";
        if(locked){
            ret[3] = "  |-Locked";
        } else {
            ret[3] = "  |";
        }
        ret[4] = "  |";
        ret[5] = "[Prizes]";
        this.display(ret);
    }
}
