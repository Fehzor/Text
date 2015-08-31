/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics.actors;

import mechanics.*;
import mechanics.colliders.collider_rectangle;
/**
 *
 * @author Awesomesauce
 */
public class actor_doorway extends actor {
    
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;
    
    private int whereTo;
    
    collider collide;
    
    public actor_doorway(int xStart, int xEnd, int yStart, int yEnd, world mine, int location){
        super(0,0);
        collide = new collider_rectangle(xStart, xEnd, yStart, yEnd, this);
        this.whereTo = location;
        this.ID = actor.DOOR_ID;
    }
    
    public void act(){   
    }
    
    public int getLocation (){
        return whereTo;
    }
    
    public collider getCollider(){
        return this.collide;
    }
}
