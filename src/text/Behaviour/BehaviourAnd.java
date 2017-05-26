/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import java.util.ArrayList;
import text.Actors.Actor;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class BehaviourAnd extends Behaviour{
    public ArrayList<Behaviour> bList = new ArrayList<>();
    Behaviour behav = null;
    
    public BehaviourAnd(){
        bList.add(new Behaviour());//add the idle behaviour as a zero ground.
    }
    
    public void act(Actor actUpon){
        priority(actUpon, WHERE_ACT);
        
        behav.act(actUpon);
    }
    
    public void addBehaviour(Behaviour B){
        bList.add(B);
    }
    
    public int priority(Actor actUpon, int where){
        int high = 0;
        for(Behaviour B : bList){
            if(behav == null){
                behav = B;
                high = behav.priority(actUpon, where);
            } else {
                int temp = B.priority(actUpon, where);
                if(temp > high){
                    behav = B;
                    high = high;
                }
            }
        }
        return high;
    }
   
    public boolean worldStep(Actor actUpon, World W){
        this.priority(actUpon, WHERE_STEP);
        behav.worldStep(actUpon,W);
        return true;
    }
    
    public void worldSwitch(Actor actUpon){
        priority(actUpon, WHERE_WORLD);
        behav.worldSwitch(actUpon);
    }
    
    public void roomSwitch(Actor actUpon){
        priority(actUpon, WHERE_ROOM);
        behav.roomSwitch(actUpon);
    }
}
