/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import java.awt.Point;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Convertable;
import text.Actors.Interactable;
import text.Actors.Player;
import static text.Behaviour.BehaviourFollow.FOLLOW_DISTANCE;
import text.Environment.Inanimate.Rock;
import text.Inventory.Resource;
import text.Robits.Robit;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class BehaviourConvertRocks extends Behaviour{

    
    public void act(Actor actUpon){
        if(priority(actUpon, WHERE_ROOM) == -1){
            return;
        }
        
        Actor next = Player.The.current.seekActor(new Rock());
        
        if(actUpon.x < next.x +3 && actUpon.x > next.x-3 &&
                actUpon.y  < next.y +3 && actUpon.y > next.y - 3){
            
            ArrayList<Resource> give = ((Convertable)next).convert();
            next.dead = true;
            for(Resource R : give){
                ((Robit)actUpon).inv.put(R);
            }
            
        } else {
            moveTowards(next.x,next.y,actUpon);
        }
                
    }
    
    public int priority(Actor actUpon, int where){
        if(where != WHERE_ACT){
            return 0;
        }
        
        if(Player.The.current.seekActor(new Rock()) != null){
            return 2;
        } else {
            //System.out.println("DERP");
            return -1;
        }
    }
    
    //Follow the player into another room.
    public boolean worldStep(Actor actUpon, World W){

        return true;
    }
    
    public void worldSwitch(Actor actUpon){

    }
    
    public void roomSwitch(Actor actUpon){

    }
}
