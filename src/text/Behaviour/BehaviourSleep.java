package text.Behaviour;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import static text.Behaviour.Behaviour.oRan;
import text.Environment.Animals.Animal;
import text.Frame.TextDisplay;
import text.Images.TextImageBasic;
import text.Inventory.Resource;
import text.Utility.ColorTuple;
import text.Utility.SuperRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FF6EB4
 */
public class BehaviourSleep extends Behaviour{
    public int steps = 1000;
    
    private static ColorTuple ZeeTuple = new ColorTuple(Color.CYAN,Color.MAGENTA,'Z');
    
    private String oldAdj = "";
    
    public void act(Actor actUpon){
        
        steps = steps - 1;
        actUpon.stop();

        if(oRan.nextInt(100) < 20){
            Actor add = makeZee();
            add.x = actUpon.x + oRan.nextInt(30) - 15;
            add.y = actUpon.y - oRan.nextInt(20)+3;

            Player.The.current.addActor(add);
        }
    }
    
    
    public int priority(Actor actUpon, int where){
        if(steps < 1){
            
            try{
                if(((Animal)actUpon).adjective.equals("Sleeping") == false){
                    oldAdj = ((Animal)actUpon).adjective;
                    ((Animal)actUpon).adjective = "Sleeping";
                }
            } catch (Exception E){}
            
            return -1;
        }
        
        try{
            if(((Animal)actUpon).adjective.equals("Sleeping") == true){
                ((Animal)actUpon).adjective = oldAdj;
            }
        } catch (Exception E){}
        return 3;
    }
    
    public static Actor makeZee(){
        //MAKE AN ACTOR FOR THIS CLASS!
        Actor ZeeActor = new Actor(0,0,new TextImageBasic(ZeeTuple,false)){

            
            
            
            int life = oRan.nextInt(50) + 20;
            public boolean act(){
                if(paused){
                    return true;
                }
                if(depth == 0){
                    depth = y + oRan.nextInt(200) - 100;
                }
                if(life > 0){
                    life -= 1;
                    if(oRan.nextInt(100) < 20){
                        x += oRan.nextInt(2) - 1;
                        y += -1;
                    }
                } else {
                    this.dead = true;
                }
                
                return true;
            }
            
            public String toString(){
                return "Zee (or Zed)";                
            }
            
            public ArrayList<Actor> pollOptions(){
                ArrayList<Actor> ret = new ArrayList<>();
                Option A = Option.cancel(this);
                Option B = Option.pickUp(this, ZeeTuple);
                Option C = Option.convert(this,convert());
                ret.add(A);
                if(!held){
                    ret.add(B);
                } else {
                    ret.add(C);
                }
                
                return ret;
            }
            
            public ArrayList<Resource> convert(){
                ArrayList<Resource> rList = new ArrayList<>();
                rList.add(new Resource(ZeeTuple));
                return rList;
            }
            
        };
        
        return ZeeActor;
    }
}
