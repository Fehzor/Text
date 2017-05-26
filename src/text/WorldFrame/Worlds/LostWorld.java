/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import text.WorldFrame.World;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import text.Frame.TextDisplay;
import java.util.Stack;
import java.util.Random;
import text.Actors.Player;
import text.Environment.*;
import text.WorldFrame.Room;

import static text.WorldFrame.Worlds.GridWorld.oRan;

/**
 *
 * @author FF6EB4
 */
public class LostWorld extends World implements Serializable{
    int size;
    
    //If you walk bakwards, you'll end up where you went in from.
    Stack<Integer> chosen;
    Stack<Integer> direction;
    
    //If you walk forwards then backwards....
    Stack<Integer> metaChosen;
    Stack<Integer> metaDirection;
    
    //Where will the player go when they walk to the next?
    int next;
    int falseNext;
    
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    public static Random oRan = new Random();
    
    public LostWorld(int size, Environment E){
        super();
        this.size = size;
        this.E = E;
        
        //A history of where the player has goned.
        chosen = new Stack<>();
        direction = new Stack<>();
        
        metaChosen = new Stack<>();
        metaDirection = new Stack<>();
        
        //Prevent the stack from ever being empty.
        chosen.push(-1);
        direction.push(-1);
        
        metaChosen.push(-1);
        metaDirection.push(-1);
        
        //Set up the next ones...
        next = oRan.nextInt(this.size);
        falseNext = oRan.nextInt(this.size);
    }
    
    public boolean needsRooms(){
        if(this.roomCount() < size){
            return true;
        }
        return false;
    }
    
    public LostWorld (int size){
        super();
        this.size = size;
        E = new Environment(Environment.TYPE_ERRATIC);
        
        //A history of where the player has goned.
        chosen = new Stack<>();
        direction = new Stack<>();
        
        metaChosen = new Stack<>();
        metaDirection = new Stack<>();
        
        //Prevent the stack from ever being empty.
        chosen.push(-1);
        direction.push(-1);
        
        metaChosen.push(-1);
        metaDirection.push(-1);
        
        //Set up the next ones...
        next = oRan.nextInt(this.size);
        falseNext = oRan.nextInt(this.size);
        
        
        for(int i = 0; i<size; ++i){
            Room temp = new Room(i,this);
            addRoom(temp);
        }
        
        //this.addPuzzle(new WumpusPuzzle(this.size));
        
    }
    
    public void playerOutOfBounds(int dir){
        
        //System.out.println(dir);

        //GET THE OPPOSITE DIRECTION
        int lastCheck = dir + 2;
        lastCheck = lastCheck % 4;
        
        //System.out.println("check= "+lastCheck);
        //System.out.println("direction= "+direction.peek());
        //System.out.println("meta= "+metaDirection.peek());
        
        //If you go back a room, go back a room.
        if(lastCheck == direction.peek()){
            metaDirection.push(dir);
            metaChosen.push(this.roomNumber());
            
            this.direction.pop();
            this.switchRoom(this.chosen.pop());

            return;
        }
        
        //Forwards then back or otherwise?
        if(lastCheck == metaDirection.peek()){
            direction.push(dir);
            chosen.push(this.roomNumber());
            
            this.metaDirection.pop();
            this.switchRoom(this.metaChosen.pop());

            return;
        }
        
        //OTHERWISE....
        
        direction.push(dir);
        chosen.push(this.roomNumber());
        
        this.switchRoom(this.next);
        
    }
    
    public void switchRoom(int next){
        super.switchRoom(next);
        
        this.next = oRan.nextInt(this.size);
        this.falseNext = oRan.nextInt(this.size);
    }
    
    public boolean adjacent(int a, int b){
        if(a == roomNumber()){
            return adjacent(b);
        } else if(b == roomNumber()){
            return adjacent(a);
        }
        
        return false; //without observation, rooms cannot touch.
    }
    
    //Check if the player is adjacent to b.
    public boolean adjacent(int b){
        ArrayList<Integer>aList = adjacentList();
        return aList.contains(b);
    }
    
    public ArrayList<Integer>adjacentList(){
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(this.next);
        ret.add(this.falseNext);
        ret.add(this.chosen.peek());
        ret.add(this.metaChosen.peek());
        return ret;
    }
    
    public ArrayList<Integer>adjacentList(int square){
        ArrayList<Integer> aList = adjacentList();
        ArrayList<Integer> ret = new ArrayList<>();
        if(aList.contains(roomNumber())){
            ret.add(roomNumber());
        }
        
        return ret;
    }
    
    public void displayMap(){
        String[] ret = new String[3];
        
        ret[0] = ""+name;
        ret[1] = "";
        ret[2] = "Choose your own adventure!";
        
        this.display(ret);
    }
}
