/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import text.Actors.Player;
import text.Actors.*;
import text.WorldFrame.*;

/**
 *
 * @author FF6EB4
 */
public class BehaviourFlight extends Behaviour{
    public void act(Actor actUpon){
        actUpon.move();
            if(actUpon.x > Player.The.x){
                moveX(2,true,actUpon);
            } else {
                moveX(2,false,actUpon);
            }
            
            if(actUpon.y > Player.The.y){
                moveY(1,true,actUpon);
            } else {
                moveY(1,false,actUpon);
            }
    }
    
    public int priority(Actor actUpon, int where){
        return 1;
    }
    
    public void outSideRoom(Actor actUpon){
        Player.The.current.owner.addActorToWorld(0,actUpon);
    }
}
