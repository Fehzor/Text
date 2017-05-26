/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame;

import java.io.Serializable;
import text.Actors.*;
import text.Environment.*;
import text.Utility.Tiles.TileBuilder;
import text.Frame.*;
import java.awt.Point;
import text.Images.*;
import text.WorldFrame.Room;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 * The main hub!
 * 
 * @author FF6EB4
 */
public class MainWorld extends World implements Serializable{
    public static Room center;
    public static World main;
    
    public static MainWorld MW;
    
    public MainWorld(){
        super();
        this.E = new Environment(Environment.TYPE_NONE);
    }
    
    public int addRoom(Room R){
        int ret = super.addRoom(R);
        center = R;
        R.block(Room.NORTH,23);
        R.block(Room.EAST,4);
        R.block(Room.WEST,4);
        
        TextImageBasic wall = TileBuilder.buildBricked();
        
        R.BG.addTextImageBasic(new Point(0,0), wall);
        return ret;
    }
    
    public void playerOutOfBounds(int dir){
        this.switchWorld(main,0);
    }
    
    public void playFirst(){
        currentRoomNum = 0;
        currentRoom = getRoom(0);
        TextDisplay.BG = currentRoom.BG;
        currentRoom.play();
        Player.The.current = currentRoom;
        
        setChanged();
        notifyObservers();
    }
    

}
