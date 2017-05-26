/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;

import text.WorldFrame.World;
import text.Images.TextImage;
import text.Utility.*;
import java.util.*;
import text.WorldFrame.*;

/**
 * This class represents something that can move or "act", and gives it the option to do so every time its act is called.
 * @author FF6EB4
 */
public class Actor extends Drawable {
    //public int type;
    public Room current;
    public String name = "Actor";
    public boolean blockable = true; //Can this actor be blocked by walls??? Or is it like an option, or a menu???
    public boolean dead= false; // If dead rooms will remove it from existence
    public boolean held = false; //Being held changes the options it gives
    public boolean world = false;
    public boolean paused = false;
    
    //The "usual" constructor- give it an image an x and a y.
    public Actor(int x, int y, TextImage TI){
        super(x,y,TI);
    }
    
    public Actor(int x, int y, TextImage TI, String name){
        super(x,y,TI);
        this.name = name;
    }
    
    //Default
    public Actor(){
        this(0,0);
    }
    
    //An actor without an image.
    public Actor(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }
    
    //Turn a drawable into an actor.
    public Actor(Drawable D){
        super(D.x, D.y, D.image);
    }
    
    //This is ran by the game loop every step
    /**
     * Makes the actor act. 
     * 
     * @return
     * False if nothing happened, true otherwise.
     */
    public boolean act(){
        if(paused){
            return true;
        }
        System.out.println("Write an act function please!");
        //This class is designed to be overridden by classes that do something here!
        return true;
    }
    
    /**
     * Called every time the actor switches rooms
     */
    public void roomSwitch(){}
    
    /**
     * Called every time the actor switches rooms
     */
    public void worldSwitch(){}
    
    //Every set amount of time, worlds will run this on room-less actors
    //Basically, lets say it's gathering resources. It goes to room X and then returns.
    //This is the method that would facilitate that.
    public boolean worldStep(World W){
        return false; //If this returns false, it doesn't need to worldstep at all.
    }
    
    //What does this actor do when its outside of the room?
    public void outSideRoom(){
        //By default, actors are totally fine outside the room because why not
    }
    
    public String toString(){
        return name;
    }
    
    public ArrayList<Actor> pollOptions(){
        return new ArrayList<>();
    }
    
    public Actor clone(){
        return null;
    }
    
    //Used to signify if the actor is moving or stopped.
    public void move(){}
    public void stop(){}
    
    public Room getCurrent(){
        return this.current;
    }
    
    
    public void onDeath(){
        
    }
}
