/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame;

import text.Actors.*;
import text.Utility.*;
import java.util.*;
import text.Frame.TextDisplay;
import text.Images.BackGround;
import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import text.Actors.Messages.InspectMenu;
/**
 *
 * The world class houses a set of actors and also the game loop!
 * How exciting!
 * 
 * @author FF6EB4
 */
public class Room extends Thread implements Serializable{
    public World owner;
    private ArrayList<Actor> actors;
    private ArrayList<Actor> toAddActors = new ArrayList<>();
    
    public int id;
    
    public BackGround BG;
    
    public long start; //TO BE SET TO System.currentTimeMillis();
    public long lastWorldCheck = -1;
    
    private boolean pause;
    
    private final int FRAMES_PER_SECOND=30;
    private final long TIME_BETWEEN_FRAMES = 1000 / FRAMES_PER_SECOND;
    
    public HitScanner HitScan;
    
    //North, South, East, West
    //Is there a wall there or something?
    private boolean cBlock = false; //Are any of them blocked? I.e. nBlocked || sBlocked ||...
    private int nBlocked = 0;
    private int sBlocked = 0;
    private int eBlocked = 0;
    private int wBlocked = 0;
    
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    
    public Lock actorListLock = new ReentrantLock();
    public Lock updateLock = new ReentrantLock();
    
    public Room(int ID){
        this.id = ID;
        pause = true; //The world starts out paused!
        actors = new ArrayList<Actor>();
        HitScan = new HitScanner();//Each room has its own hitscan :D
        
        addActor(Player.The);
        
        BG = new BackGround(new ColorTuple(Color.GRAY,Color.GRAY,' '));
    }
    
    public Room(int ID, World W){
        this.owner = W;
        this.id = ID;
        
        actors = new ArrayList<Actor>();

        HitScan = new HitScanner();//Each world has its own hitscan :D
        
        BG = W.E.makeBack();
        //System.out.println("Adding player");
        addActor(Player.The);
        
        pause = true; //The world starts out paused!
        this.start();
    }
    
    //The main game loop.
    public void run(){
        
        while( !pause ) {
            while( !ready() ) {
                try{
                    //System.out.print("z");
                    Thread.sleep(10);
                } catch(Exception e){
                    //System.err.println("Something has gone terribly wrong!");
                    //System.exit(-1);
                }
            }
            //System.out.println("TICKTOCK");
            update();
            display();
        }
        
        while(pause){
            try{
                    //System.out.println("THREAD"+this.getId());
                    //System.out.println("TIMEOUT: "+(100+times_slept));
                    Thread.sleep(10000);
                    //times_slept+=(int)times_slept+1;
                    this.setPriority(MIN_PRIORITY);
                    Thread.yield();
                    if(!pause || Player.The.current == this){
                        this.setPriority(NORM_PRIORITY);
                        pause = false;
                    }
                } catch(Exception e){
                    //System.err.println("Something has gone terribly wrong!");
                    //System.exit(-1);
                }
        }
        
        this.run();
    }
    
    //Is it ready to tick???
    private boolean ready(){
        long a = GetTickCount();
        if(a > TIME_BETWEEN_FRAMES){
            start = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }
    
    
    private long GetTickCount(){
        return System.currentTimeMillis() - start;
    }
    
    //Update the game world
    private void update(){
        while(updateLock.tryLock() == false){};
        //System.out.println("UPDATING ROOM");
        combineActorLists();
        
        HitScan.clear();
        
        //Remove the bodies!
        try{
            ArrayList<Actor> done = new ArrayList<>();
            for(int i = actors.size()-1; i>=0 ;--i){
                Actor A = actors.get(i);
                //
                if(done.contains(A)){
                    actors.remove(i);
                    //System.out.println("REMOVING " + A);
                    
                } else if(A.dead){
                    //System.out.println("REMOVING " + A);
                    try{
                    this.dropActor(A);
                    //System.out.println(A);
                    TextDisplay.drawables.remove(A);
                    } catch (Exception E){}
                }
                done.add(A);
            }
        
        
            //Set up the hitscan!
            for(int i = 0; i<actors.size();++i){
                Actor A = actors.get(i);

                try{
                    boolean inSide = HitScan.registerLocation(A,A.x,A.y,A.height);

                    if(!inSide){
                            A.outSideRoom();
                    }
                } catch (NullPointerException E){

                }
            }

            //Make them act!s
            //System.out.println("ACTING");
            HashSet<Actor> HS = new HashSet<>(actors);
            
            for(Actor A : HS){
                    //System.out.println(A);
                    A.act();
                    

                    checkBlock(A);
            }
        } catch (IndexOutOfBoundsException IOOBE){
            System.err.println(IOOBE);
        } catch (ConcurrentModificationException CME){
            System.err.println("CME!"+CME);
        }
        updateLock.unlock();
    }
    
    //Display the game world
    private void display(){
        TextDisplay.Display.repaint();
    }
    
    //Pause the world!
    public void pause(){
        this.pause = true;
    }
    
    //Called when this world is switched away from.
    public synchronized void switchFrom(){
        for(Actor A : actors){
            A.roomSwitch();
        }
    }
    
    //Make the world go (again)!
    public void play(){
        //System.out.println("PLAY");
        pause = false;
        //Player.The.current = this;
        
        //while(actorListLock.tryLock() == false){}
        TextDisplay.swapDrawables(this.actors);
        //actorListLock.unlock();
        
        //System.out.println("INTERUPT + PRIORITY");
        this.interrupt();
        this.setPriority(NORM_PRIORITY);
        
        //System.out.println("WORLDACTORS....skip");
        
        for(int i = this.owner.worldActors.size()-1; i>=0; --i){
            Actor A = this.owner.worldActors.get(i);
            //System.out.println("CHECKING ACTORS..."+"ID = "+this.id+" VS " +i);
            if(this.id == this.owner.worldActorLocations.get(A)){
                //System.out.println("ADDING WORLDACTOR TO ROM");
                this.addActor(A);
                //System.out.println("REMVING WORLDACTOR FROM WORLD");
                this.owner.worldActors.remove(A);
            }
        }
        
    }
    
    //Is this world paused?
    public boolean check_pause(){
        return pause;
    }
    
    //Add an actor to this world.
    public synchronized void addActor(Actor A){
        if(actorListLock.tryLock() == false){return;}
        if(this.actors.contains(A)){
            actorListLock.unlock();
            return;
        }
        A.current = this;
        this.toAddActors.add(A);
        HitScan.registerActor(A);
        if(!pause){
            TextDisplay.add(A);
        }
        actorListLock.unlock();
    }
    
    private void combineActorLists(){
        this.actors.addAll(toAddActors);
        toAddActors = new ArrayList<>();
    }
    
    public synchronized void dropActor(Actor A){
        if(actorListLock.tryLock() == false){return;}
        //System.out.println(A);
        this.actors.remove(A);
        
        HitScan.unregisterActor(A);
        
        if(owner.currentRoom() == this){
            TextDisplay.drawables.remove(A);
        }
        
        A.onDeath();
        
        actorListLock.unlock();
    }
    
    /**
     * returns a list of all actors the same type as A in this room.
     * @param A
     * @return 
     */
    public synchronized ArrayList<Actor> getOfType(Actor A){
        ArrayList<Actor> ret = new ArrayList<>();
        
        for(Actor mine : actors){
            if( mine.getClass().equals(A.getClass()) ){
                ret.add(mine);
            }
        }
        
        return ret;
    }
    
    //Does this world contain an Actor A?
    public synchronized boolean contains(Actor A){
        return actors.contains(A);
    }
    
    /**
     * counts the number of actors of the same type as A
     * @param A
     * The type of actor to use.
     * @return 
     * How many of that type there are.
     */
    public synchronized int countActors(Actor A){
        int ret = 0;
        if(actorListLock.tryLock() == false){
            return 0;
        }
        
        for(Actor mine : actors){
            if( mine.getClass().equals(A.getClass()) ){
                ret++;
            }
        }
        
        actorListLock.unlock();
        
        return ret;
    }
    
    //direction = NORTH,SOUTH,EAST,WEST
    //amt = number of pixels to block by
    public void block(int direction, int amt){
        switch(direction){
            case NORTH:
                nBlocked = amt;
                break;
            case SOUTH:
                sBlocked = amt;
                break;
            case EAST:
                eBlocked = amt;
                break;
            case WEST:
                wBlocked = amt;
                break;
            default:
                System.err.println("Please use Room.NORTH/SOUTH/EAST/WEST for direction");
                break;
        }
        
        cBlock = nBlocked!=0 || sBlocked!=0 || wBlocked!=0 || eBlocked!=0;
    }
    
    //See block but the opposite of that.
    public void unblock(int direction){
       switch(direction){
            case NORTH:
                nBlocked = 0;
                break;
            case SOUTH:
                sBlocked = 0;
                break;
            case EAST:
                eBlocked = 0;
                break;
            case WEST:
                wBlocked = 0;
                break;
            default:
                System.err.println("Please use Room.NORTH/SOUTH/EAST/WEST for direction");
                break;
        } 
       
        cBlock = nBlocked!=0 || sBlocked!=0 || wBlocked!=0 || eBlocked!=0;
    }
    
    //Check if blockages are in order.
    public void checkBlock(Actor A){
        if(!cBlock || !A.blockable){ //All of them are false, return.
            //Alternatively, the actor just isn't blockable.
            return;
        }
        
        if(nBlocked!=0 && A.y<nBlocked){
            A.y = nBlocked;
        }
        if(sBlocked!=0 && A.y > TextDisplay.SCREEN_SIZE_Y - sBlocked){
            A.y = TextDisplay.SCREEN_SIZE_Y - sBlocked;
        }
        if(wBlocked!=0 && A.x<wBlocked){
            A.x = wBlocked;
        }
        if(eBlocked!=0 && A.x > TextDisplay.SCREEN_SIZE_X - eBlocked){
            A.x = TextDisplay.SCREEN_SIZE_X - eBlocked;
        }
    }
    
    /**
     * Seeks an actor of type A and returns it.
     * 
     * @param A
     * An actor similar to the one being returned
     * 
     * @return 
     * The first actor of type A to be found.
     */
    public synchronized Actor seekActor(Object A){
        
        /*if(actorListLock.tryLock() == false){
            //System.out.println("DERPPPPP");
            return null;
        }*/
        
        try{
        for(Actor B : actors){
            if(B.getClass().equals(A.getClass())){
                //actorListLock.unlock();
                return B;
            }
        }
        
        //actorListLock.unlock();
        return null;
        } catch (ConcurrentModificationException E){
            
            //actorListLock.unlock();
            return null;
        }
        
        
    }
    
    public ArrayList<Actor> getActors(){
            try{
                Thread.sleep(100);
            } catch (Exception E){
                //...
            }
        return this.actors;
    }
}

//             