/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Instances.Food;
import text.Actors.Player;
import static text.Behaviour.Behaviour.oRan;
import text.Environment.Animals.Animal;
import text.Frame.TextDisplay;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class BehaviourWander extends Behaviour{
    
    private HashMap<Actor,Integer> direction = new HashMap<>();
    private HashMap<Actor,Integer> distance = new HashMap<>();
    
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    public void act(Actor actUpon){
        if(direction.get(actUpon) == null){
            setDirection(actUpon);
        }
        
        actUpon.move();
        
        switch(direction.get(actUpon)){
            case NORTH:
                actUpon.y+= -1;
                actUpon.x = distance.get(actUpon);
                return;
            case SOUTH:
                actUpon.x = distance.get(actUpon);
                actUpon.y+= 1;
                return;
            case EAST:
                actUpon.image.flip(true);
                actUpon.y = distance.get(actUpon);
                actUpon.x+= -1;
                return;
            case WEST:
                actUpon.image.flip(false);
                actUpon.y = distance.get(actUpon);
                actUpon.x+= 1;
                return;
        }
        
    }
    
    public boolean worldStep(Actor actUpon, World W){
        //System.out.println(actUpon.current.id);
        W.moveActor(actUpon, actUpon.current.id, W.getRandomRoomNumber());
        
        if(actUpon.current.id == Player.The.current.id){
            if(direction.get(actUpon) == null){
                direction.put(actUpon,oRan.nextInt(100) % 4);
            }   
            switch(direction.get(actUpon)){
                case NORTH:
                    actUpon.y = TextDisplay.SCREEN_SIZE_Y-3;
                    break;
                case SOUTH:
                    actUpon.y = 3;
                    break;
                case EAST:
                    actUpon.x = TextDisplay.SCREEN_SIZE_X-3;
                    break;
                case WEST:
                    actUpon.x = 3;
                    break;
            }
            
            Player.The.current.addActor(actUpon);
            W.removeActorFromWorld(actUpon);
        }
        return true;
    }
    
    
    public int priority(Actor actUpon, int where){
        return 1;
    }
    
    public void outSideRoom(Actor actUpon){
        Player.The.current.owner.addActorToWorld(0,actUpon);
        Player.The.current.dropActor(actUpon);
        actUpon.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        actUpon.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        //System.out.println("OUT OF BOUNDS");
    }
    
    private void setDirection(Actor actUpon){
        direction.put(actUpon,oRan.nextInt(100) % 4);
        switch(direction.get(actUpon)){
                case NORTH:
                case SOUTH:
                    distance.put(actUpon, oRan.nextInt(TextDisplay.SCREEN_SIZE_X));
                    break;
                case EAST:
                case WEST:
                    distance.put(actUpon, oRan.nextInt(TextDisplay.SCREEN_SIZE_Y));
                    break;
            }
    }
}
