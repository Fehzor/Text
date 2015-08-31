/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import java.util.*;
import mechanics.actors.*;
import display.displayFrame;

/**
 *
 * @author Awesomesauce
 */
public abstract class worldGenerator {
    
    public abstract void generateWorld(worldHub WH);
    
    /*
    "where" corresponds to one of the direction given in actor_doorway.
    
    This makes a door by the edge if one of those is given, or does nothing otherwise.
    */
    public static void addEdgeDoor(int where, world W){
        if (where == actor_doorway.NORTH){
            actor a = new actor_doorway(0, displayFrame.DEFAULT_MAIN_WIDTH,
                    0,2, W, actor_doorway.NORTH);
            W.addActor(a);
            W.addCollider(((actor_doorway)a).getCollider());
            
        } else if (where == actor_doorway.SOUTH){
            actor a = new actor_doorway(0, displayFrame.DEFAULT_MAIN_WIDTH,
                    displayFrame.DEFAULT_MAIN_HEIGHT-2,displayFrame.DEFAULT_MAIN_HEIGHT, W, where);
            W.addActor(a);
            W.addCollider(((actor_doorway)a).getCollider());
            
        } else if (where == actor_doorway.EAST){
            actor a = new actor_doorway(displayFrame.DEFAULT_MAIN_WIDTH-2, displayFrame.DEFAULT_MAIN_WIDTH,
                    0,displayFrame.DEFAULT_MAIN_HEIGHT, W, where);
            W.addActor(a);
            W.addCollider(((actor_doorway)a).getCollider());
            
        } else if (where == actor_doorway.WEST){
            actor a = new actor_doorway(0, 2,
                    0,displayFrame.DEFAULT_MAIN_HEIGHT, W, where);
            W.addActor(a);
            W.addCollider(((actor_doorway)a).getCollider());
            
        } else return;
        
        
    }
}
