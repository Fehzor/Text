/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.ToolParts;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import text.Behaviour.BehaviourAnd;
import text.Behaviour.BehaviourBuzzFollow;
import text.Behaviour.BehaviourSleep;
import text.Combat.Hero.Instrument;
import text.Environment.Animals.Animal;
import text.Environment.Animals.Bee;
import text.Images.TextImageBasic;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import static text.Tools.ToolPart.TYPE_END;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class SmokePuff extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public SmokePuff(){
        super(myImage);
        super.name = "Bee Smoker";
        this.name = "Bee Smoker";
        this.type = TYPE_END;
        this.info = "Tool end piece. Change bees' religion!";
    }
    
    public Tool compile(Tool T){
        if(T == null){
            return T;
        }
        ToolEffect myEffect = new ToolEffect(){
            public void applyEffect(ArrayList<Actor> list, double power){
                boolean worked = false;
                for(Actor A : list){
                    try{

                        Behaviour oldBehav = ((Bee)A).behav;
                        BehaviourBuzzFollow newBehav = new BehaviourBuzzFollow();
                        
                        ((Bee)A).behav = newBehav;
                        
                        worked = true;
                        
                    } catch(Exception E){}
                }
                
                if(worked){
                    String[] disp = new String[1];
                    disp[0] = Bee.getDisplay();
                    Player.The.current.owner.display(disp);
                }
            }
        };
        T.name = "Smokey " + T.name;
        
        T.effects.add(myEffect);
        
        T.animationTime += 10;
        
        if(T instanceof Instrument){
            ((Instrument)T).left += "bzzz";
            ((Instrument)T).right += "bzzz";
            ((Instrument)T).difficulty += .03;
        }
        
        return T;
    }
    
    public String toString(){
        return "Bee Smoker";
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("teleporter_held.txt");
    } 
}
