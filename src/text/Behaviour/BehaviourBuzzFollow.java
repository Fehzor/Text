/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import text.WorldFrame.World;
import java.awt.Point;
import text.Actors.*;
import text.WorldFrame.*;

/**
 * Follow the player around, being a happy lil pet of sorts.
 * 
 * @author FF6EB4
 */
public class BehaviourBuzzFollow extends Behaviour{
    public static final int FOLLOW_DISTANCE = 12;//Distance to the player before it follows to them.
    private boolean done = false;
    
    public void act(Actor actUpon){
        if(!done){
            moveTowards(Player.The.x+oRan.nextInt(16)-8,Player.The.y+oRan.nextInt(16)-8,actUpon);
        } else {
            actUpon.stop();
        }
            
        Point pointA = new Point(actUpon.x,actUpon.y);
        Point pointB = new Point(Player.The.x+oRan.nextInt(16)-8,Player.The.y+oRan.nextInt(16)-8);
        int dist = (int)pointA.distance(pointB);
        if(dist < FOLLOW_DISTANCE){
            done = true;
        } else {
            done = false;
        }
    }
    
    public int priority(Actor actUpon, int where){
        if(where != WHERE_ACT){
            return 3;
        }
        return 1;
    }
    
    //Follow the player into another room.
    public boolean worldStep(Actor actUpon, World W){
        if(actUpon.current != Player.The.current){
            actUpon.x = Player.The.x;
            actUpon.y = Player.The.y;
            Player.The.current.addActor(actUpon);
            W.worldActors.remove(actUpon);
        }
        
        return true;
    }
    
    public void worldSwitch(Actor actUpon){
        actUpon.x = Player.The.x;
        actUpon.y = Player.The.y;
        Player.The.current.addActor(actUpon);
    }
    
    public void roomSwitch(Actor actUpon){
        actUpon.x = Player.The.x;
        actUpon.y = Player.The.y;
        Player.The.current.addActor(actUpon);
    }
}