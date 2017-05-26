/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame;

import text.WorldFrame.Templates.WildTemplate;
import text.WorldFrame.Puzzles.Puzzle;
import text.Actors.Messages.Message;
import java.io.Serializable;
import text.Environment.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import text.Actors.*;
import text.Actors.Instances.Door;
import text.Frame.TextDisplay;
import text.Utility.MenuBuilder;
import text.WorldFrame.Room;
import text.WorldFrame.Templates.WorldTemplate;
import text.WorldFrame.WorldStepper;


/**
 *
 * @author FF6EB4
 */
public class World extends Observable implements Serializable{
    public WorldTemplate source;
    public Room currentRoom;
    public int currentRoomNum;
    
    public static Random oRan = new Random();
    private ArrayList<Room> roomList;
    private HashMap<Integer,Room> roomMap;
    
    public ArrayList<Door> worldMap = new ArrayList<>();
    public ArrayList<Integer> storyRooms = new ArrayList<>();
    //public ArrayList<Door> doors = new ArrayList<>();
    private int roomNum = 0;
    
    public Environment E;
    
    public ArrayList<Actor> worldActors;
    public HashMap<Actor,Integer> worldActorLocations;
    public HashMap<Integer,ArrayList<Actor>> heldWorldActors;
    
    private WorldStepper myStepper;
    
    private final Lock switchLock = new ReentrantLock();
    private final Lock roomLock = new ReentrantLock();
    private final Lock displayLock = new ReentrantLock();
    private final Lock worldActorLock = new ReentrantLock();
    
    public String name = "World";

    public World(){
        worldActors = new ArrayList<>();
        roomList = new ArrayList<>();
        roomMap = new HashMap<>();
        worldActorLocations = new HashMap<>();
        heldWorldActors = new HashMap<>();
        this.addObserver(GameCoordinator.The);
    }
    
    //Adds room R to the list of rooms, assigning it a number.
    public int addRoom(Room R){
        R.owner = this;
        roomList.add(R);
        roomMap.put(roomNum,R);
        roomNum+=1;
        heldWorldActors.put(R.id, new ArrayList<>());
        return roomNum - 1;
    }
    
    //How many rooms are there currently..?
    public int roomCount(){
        return roomNum;
    }
    
    //Does this world need rooms to function or is it ready?
    public boolean needsRooms(){
        return false;
    }
    
    //returns room number i.
    public Room getRoom(int i){
        return roomMap.get(i);
    }
    
    //Used whenever the player switches rooms.
    public synchronized void switchRoom(int i){
        Room oldRoom = currentRoom;
        currentRoom = getRoom(i);
        currentRoomNum = i;
        
        TextDisplay.BG = currentRoom.BG;
        Player.The.current = currentRoom;
        
        setChanged();
        notifyObservers();
        
        Player.The.current = currentRoom;
        oldRoom.switchFrom();
        oldRoom.pause();
        
        currentRoom.play(); 
    }
    
    public synchronized void switchWorld(World next, int room){
        if(switchLock.tryLock() == false){
            return;
        }
        
        //pause the current room.
        this.currentRoom.pause();
        

        Room R = next.getRoom(next.currentRoomNum);
        next.currentRoom = R;
        Player.The.current = R;
        
        //Finish updating the old world's stuff
        if(this.myStepper != null){
            myStepper.run();
            myStepper.kill();
        }
        
        for(Actor A : worldActors){
            A.worldSwitch();
        }
        
        this.currentRoom.switchFrom();
        
        setChanged();
        notifyObservers();
        
        //Call the gc to clean up the room
        System.gc();
        
        //TextDisplay.BG = R.BG;
        
        //if(next.worldActors.size() > 0){
        next.runWorldStep();
        
        
        //Player.The.outSideRoom();
        next.switchRoom(room);
        
        switchLock.unlock();
    }
    
    
    public synchronized void switchWorld(Room R){
        //System.out.println(R.owner);
        this.switchWorld(R.owner, R.id);
        //R.owner.switchRoom(R.id);
    }
    
    //Starts the current room.
    public void startCurrentRoom(){
        currentRoom.play();
    }
    
    //Called whenever the player leaves a room
    public void playerOutOfBounds(int dir){
        //Over-ride this if wanted.
        //Mostly for switching rooms..
    }
    
    public void positionPlayer(int dir){
            if(dir == Player.WEST){
                Player.The.x = TextDisplay.SCREEN_SIZE_X - 10;
            }
            
            if(dir == Player.EAST){
                Player.The.x = 10;
            }
            
            if(dir == Player.NORTH){
                Player.The.y = TextDisplay.SCREEN_SIZE_Y - 10;
            }

            if(dir == Player.SOUTH){
                Player.The.y = 10;
            }
    }
    
    //Is A next to B?
    public boolean adjacent(int a, int b){
        return false; //Clasically they just never are and the world is in a void.
    }
    
    //Check if the player is adjacent to b.
    public boolean adjacent(int b){
        return adjacent(currentRoomNum, b);
    }
    
    //Returns all rooms adjacent to the room in square.
    public ArrayList<Integer>adjacentList(int square){
        return new ArrayList<>();
    }
    
    //Returns all rooms adjacent to the one the player is in
    public ArrayList<Integer>adjacentList(){
        return adjacentList(currentRoomNum);
    }
    
    //Adds a puzzle to this world! The puzzle does its thing.
    public void addPuzzle(Puzzle P){
        addObserver(P);
        setChanged();
        //notifyObservers();
    }
    
    //Used by stories
    public void clearStory(){
        this.storyRooms = new ArrayList<>();
    }
    
    //The current room number
    public int roomNumber(){
        return currentRoomNum;
    }
    
    public Room currentRoom(){
        return currentRoom;
    }
    
    //Display a message into the current room.
    //The message is an array of strings, the player presses enter, done.
    public void display(String[] disp){
        while(displayLock.tryLock() == false){
            try{
                Thread.sleep(100);
            } catch (Exception E){
                //...
            }
        }
        Drawable D = MenuBuilder.buildDisplayFull(disp);
        Actor add = new Message(this,D);
        if(currentRoom != null){
            currentRoom.addActor(add);
        }
        
        displayLock.unlock();
    }
    
    ///
    /// Methods that quickly move actors between rooms.
    /// Shouldn't usually be overridden.
    ///
    
    //Moves an actor to a different room.
    public void moveActor(Actor A, int prev, int next){
        if(this.getRoom(prev).contains(A)){
            this.getRoom(prev).dropActor(A);
        }
        this.worldActors.add(A);
        this.worldActorLocations.put(A,next);
        this.heldWorldActors.get(prev).remove(A);
        this.heldWorldActors.get(next).add(A);
        A.current = getRoom(next);
    }
    
    public void updatePuzzles(){
        this.setChanged();
        this.notifyObservers();
    }
    
    
    public void runWorldStep(){
        //System.out.println(worldActors.size());
        //The world stepper is a threaded class that runs all of the world steps
        new WorldStepper(this);
    }
    
    
    
    //Adds actors to the world for world stepping
    public void addActorToWorld(int room, Actor A){
        if(this.worldActorLock.tryLock() == false){
            return;
        }
        if(this.worldActors.contains(A)){
            return;
        }
        worldActors.add(A);
        worldActorLocations.put(A,room);
        heldWorldActors.get(room).add(A);
        A.world = true;
        A.current = getRoom(room);
        
        if(this.myStepper == null){
            runWorldStep();
        }
        
        this.worldActorLock.unlock();
    }
    
    public void removeActorFromWorld(Actor A){
        worldActors.remove(A);
        A.world = false;
    }
    
    public int getRandomRoomNumber(){
        return oRan.nextInt(roomNum);
    }
    
    public int getLinkRoom(){
        return oRan.nextInt(this.roomNum);
    }

    public void displayMap(){
        //Display a map! Different per world!
    }
    
    public void registerDoor( Door D ){
        this.worldMap.add(D);
    }
    
    public boolean checkDoor(int roomNum){
        for(Door D : worldMap){
            if(D.current != null){
                if(D.current.id == roomNum){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkStory(int roomNum){
        return storyRooms.contains(roomNum);
    }
    
    public void connect(World W){
        try{
            int a = this.getLinkRoom();
            int b = W.getLinkRoom();
            
            Room A = this.getRoom(a);
            Room B = W.getRoom(b);

            Door alpha = new Door("Door");
            Door beta = new Door(alpha,"Door");

            alpha.x = TextDisplay.SCREEN_SIZE_X / 2;
            alpha.y = TextDisplay.SCREEN_SIZE_Y / 2 - 10;
            alpha.depth = alpha.y;
            

            beta.x = TextDisplay.SCREEN_SIZE_X / 2;
            beta.y = TextDisplay.SCREEN_SIZE_Y / 2 - 10;
            beta.depth = beta.y;
            
            
            for(Actor act : A.getActors()){
                if(act.x == alpha.x && act.y == alpha.y){
                    act.dead = true;
                }
            }
            
            for(Actor act : B.getActors()){
                if(act.x == beta.x && act.y == beta.y){
                    act.dead = true;
                }
            }
            
            alpha.dead = false;
            beta.dead = false;
            
            A.addActor(alpha);
            B.addActor(beta);
            
            this.registerDoor(alpha);
            W.registerDoor(beta);
            
        } catch (Exception E){
            System.err.println(E+"\nCannot connect worlds!");
            return;
        }
        
        
        
    }
        
}
