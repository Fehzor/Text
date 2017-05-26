/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC;

import java.awt.Point;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import static text.Behaviour.Behaviour.oRan;
import text.Frame.TextDisplay;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class NPCBehaviourWalk extends Behaviour{
    
    public HashMap<Actor,Boolean> walk = new HashMap<>();
    public HashMap<Actor,Actor> to = new HashMap<>();
    
    public void act(Actor actUpon){
        if(!walk.containsKey(actUpon)){
            walk.put(actUpon,false);
        }
        
        if(oRan.nextInt(1000) == 23){
            walk.put(actUpon,true);
            to.put(actUpon,new Actor(oRan.nextInt(TextDisplay.SCREEN_SIZE_X),oRan.nextInt(TextDisplay.SCREEN_SIZE_Y)));
        }
        
        if(walk.get(actUpon) == false){
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
        } else {
            int x = (int)to.get(actUpon).x;
            int y = (int)to.get(actUpon).y;
            moveTowardsSlowly(x,y,actUpon);
            if(distanceToActor(to.get(actUpon),actUpon) < 10){
                walk.put(actUpon,false);
            }
        }
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
