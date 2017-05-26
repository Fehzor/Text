/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import text.Actors.Actor;
import text.Actors.Instances.Food;
import text.Actors.Player;
import text.Environment.Animals.Animal;
import text.Environment.Animals.Nest;
import text.Frame.TextDisplay;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class BehaviourFeral extends Behaviour{
    int warning = 0;
    
    public void act(Actor actUpon){
        actUpon.stop();
        if(warning > 100){
            moveAwayFrom(1,Player.The.x,Player.The.y,actUpon);
        } else {
            int dist = distanceToPlayer(actUpon);
            if(dist > 65){
                warning = warning-1;
                if(warning<0)warning = 0;
            }
            if(dist < 45){
                warning = warning+1;
            }
            if(dist < 20){
                warning = warning+3;
            }
            if(dist < 15){
                warning = warning+6;
            }
            if(dist < 10){
                warning = warning+20;
            }
            if(dist < 5){
                warning = warning+50;
            }
            
            if(oRan.nextInt(100) < warning){
                int a = oRan.nextInt(100);
                if(a<25)moveX(oRan.nextInt(2),true,actUpon);
                else if(a>75)moveX(oRan.nextInt(2),false,actUpon);
                else if(a<50)moveY(oRan.nextInt(2),true,actUpon);
                else if(a>50)moveY(oRan.nextInt(2),false,actUpon);
                
                try{
                    Animal A = (Animal)actUpon;
                    Nest home = A.home;
                    moveTowards(home.x,home.y,actUpon);
                    
                    if(A.x == home.x && A.y == home.y){
                        home.add(A);
                        A.dead = true;
                    }
                    
                } catch (Exception E){
                    moveAwayFrom(1,Player.The.x,Player.The.y,actUpon);
                }
            } 
            
            if(warning == 0){
                
                //System.out.println("FOOD");
                Actor A = Player.The.current.seekActor(new Food());
                //System.out.println("FOODEND");
                if(A==null){
                    return;
                } else {
                    //System.out.println("SUCCESS!");
                    moveTowards(A.x,A.y,actUpon);
                    if(distanceToActor(A,actUpon) < 5){
                        A.dead = true;
                        ((Animal)actUpon).behav = new BehaviourFollow();
                    }
                }
                
            }
        }
    }
    
    public int priority(Actor actUpon, int where){
        return 1;
    }
    
    public boolean worldStep(Actor actUpon, World W){
        int addTo = W.getRandomRoomNumber();
        Room R = W.getRoom(addTo);
        R.addActor(actUpon);
        W.worldActors.remove(actUpon);
        
        actUpon.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        actUpon.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        
        return true;
    }
    
    public void outSideRoom(Actor actUpon){
        Player.The.current.owner.addActorToWorld(0,actUpon);
        Player.The.current.dropActor(actUpon);
        this.warning = 0;
        actUpon.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        actUpon.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
    }
}
