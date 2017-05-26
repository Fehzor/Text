/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import java.awt.Point;
import text.Actors.*;
import text.WorldFrame.World;
import text.PartBuilder.*;
import java.io.*;
import text.Environment.Animals.Animal;
import java.util.*;

/**
 * A behaviour is something an actor exhibits as its AI.
 * 
 * Each behaviour does one thing.
 * 
 * @author FF6EB4
 */
public class Behaviour implements Serializable{
    public static Random oRan = new Random();
    public static final int WHERE_ACT = 0;
    public static final int WHERE_ROOM = 1;
    public static final int WHERE_WORLD = 2;
    public static final int WHERE_STEP = 3;
    int step = 0;
    
    //Decides what this behaviour does.
    //The default, here, is the idle behaviour.
    public void act(Actor actUpon){
        if(oRan.nextInt(100)>97){
                actUpon.move();
                int dir = oRan.nextInt(100);
                if(dir < 40){
                    moveX(2,true,actUpon);
                    return;
                }
                if(dir > 60){
                    moveX(2,false,actUpon);
                    return;
                }
                if(dir > 40 && dir < 50){
                    moveY(1,true,actUpon);
                    return;
                }
                if(dir > 50 && dir < 60){
                    moveY(1,false,actUpon);
                    return;
                }
            } else {
                actUpon.stop();
            }
    }
    
    /**
     * The priority which this behaviour goes with. 0 = default
     * 
     * Higher = do it more
     * 
     * Generally-
     * 0 = idle
     * 1 = new idle (like follow)
     * >2 = the preffered action
     * 
     * So if you had-
     * 
     * BAnd
     * BFollow = 1
     * BConvert = 2
     * 
     * It would convert first, and convert when possible
     * 
     * @return how high an urgency is it that this bot do the thing.
     */
    public int priority(Actor actUpon, int where){
        return 0;
    }
    
    public void moveAwayFrom(int amt, int x, int y, Actor actUpon){
        actUpon.move();
        if(actUpon.x > x){
            moveX(2,true,actUpon);
        } else {
            moveX(2,false,actUpon);
        }

        if(actUpon.y > y){
            moveY(1,true,actUpon);
        } else {
            moveY(1,false,actUpon);
        }
    }
    
    //Moves the actor in question forward/backwards.
    public void moveX(int amt, boolean forward, Actor A){
        A.move();
        if(!forward){
            amt = amt * -1;
            A.image.flip(true);
        } else {
            A.image.flip(false);
        }
        
        A.x += amt;
    }

    
    //Moves the actor up/down
    public void moveY(int amt, boolean up, Actor A){
        A.move();
        if(!up){
            amt = amt * -1;
        }
        
        A.y += amt;
    }
    
    public void moveTowardsSlowly(int x, int y, Actor actUpon){
        actUpon.move();
        
        if(step < 1){
            step++;
            return;
        } else {
            step = 0;
        }
            if(Math.abs(actUpon.x-x) > 2){
                if(actUpon.x < x){
                    moveX(2,true,actUpon);
                } else {
                    moveX(2,false,actUpon);
                }
            }
            
            if(Math.abs(actUpon.y-y) > 1){
                if(actUpon.y < y){
                    moveY(1,true,actUpon);
                } else {
                    moveY(1,false,actUpon);
                }
            }
    }
    
    public void moveTowards(int x, int y, Actor actUpon){
        actUpon.move();
            if(Math.abs(actUpon.x-x) > 2){
                if(actUpon.x < x){
                    moveX(2,true,actUpon);
                } else {
                    moveX(2,false,actUpon);
                }
            }
            
            if(Math.abs(actUpon.y-y) > 1){
                if(actUpon.y < y){
                    moveY(1,true,actUpon);
                } else {
                    moveY(1,false,actUpon);
                }
            }
    }
    
    public int distanceToActor(Actor A,Actor actUpon){
        int difX = actUpon.x - A.x;
        int difY = actUpon.y - A.y;
        int pyth = difX*difX+difY*difY;
        return (int)Math.sqrt(pyth);
    }
    
    public int distanceToPlayer(Actor actUpon){
        return distanceToActor(Player.The,actUpon);
    }
    
    
    
    //Actors with behaviour may require the use of the worldStep function.
    public boolean worldStep(Actor actUpon, World W){
        return true;
    }
    
    public void outSideRoom(Actor actUpon){
        //doesn't do anything for this one.
    }
    
    public void roomSwitch(Actor actUpon){}
    
    public void worldSwitch(Actor actUpon){}
}
