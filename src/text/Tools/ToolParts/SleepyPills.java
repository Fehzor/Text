/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.ToolParts;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Convertable;
import text.Actors.Pickup;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import text.Behaviour.BehaviourAnd;
import text.Behaviour.BehaviourSleep;
import text.Combat.Hero.Instrument;
import text.Environment.Animals.Animal;
import text.Images.TextImageBasic;
import static text.Inventory.Inventory.oRan;
import text.Inventory.Resource;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import static text.Tools.ToolPart.TYPE_END;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class SleepyPills extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public SleepyPills(){
        super(myImage);
        super.name = "Sleepy Pills";
        this.name = "Sleepy Pills";
        this.type = TYPE_END;
        this.info = "Tool end piece. Puts animals to sleep.";
    }
    
    public Tool compile(Tool T){
        if(T == null){
            return T;
        }
        ToolEffect myEffect = new ToolEffect(){
            public void applyEffect(ArrayList<Actor> list, double power){
                for(Actor A : list){
                    try{

                        Behaviour oldBehav = ((Animal)A).behav;
                        BehaviourAnd newBehav = new BehaviourAnd();

                        if(oldBehav instanceof BehaviourAnd ){
                            newBehav = ((BehaviourAnd)oldBehav);

                            boolean doneit = false;
                            for(Behaviour B : newBehav.bList){
                                if(B instanceof BehaviourSleep){
                                    ((BehaviourSleep)B).steps =(int) (500 * power);
                                    doneit = true;
                                }
                                if(!doneit){
                                    BehaviourSleep BS = new BehaviourSleep();
                                    BS.steps = (int) (500 * power);
                                    newBehav.addBehaviour(BS);
                                }
                            }

                        } else {

                            newBehav.addBehaviour(oldBehav);
                            BehaviourSleep BS = new BehaviourSleep();
                            BS.steps = (int) (500 * power);
                            newBehav.addBehaviour(BS);
                        }

                        ((Animal)A).behav = newBehav;
                    } catch(Exception E){}
                }
            }
        };
        T.name = "Snoozy " + T.name;
        
        T.effects.add(myEffect);
        
        T.animationTime += 10;
        
        if(T instanceof Instrument){
            ((Instrument)T).left += "zzz";
            ((Instrument)T).difficulty += .03;
        }
        
        return T;
    }
    
    public String toString(){
        return "Sleepy Pills";
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("pills.txt");
    }   
}
