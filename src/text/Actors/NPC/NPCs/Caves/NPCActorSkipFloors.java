/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves;

import text.Actors.NPC.*;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import static text.Environment.Animals.AnimalTemplate.animalImages;
import text.Frame.TextDisplay;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Utility.SuperRandom;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Worlds.CaveWorld;

/**
 *
 * @author FF6EB4
 */
public class NPCActorSkipFloors extends NPCActor{
    public Behaviour behav;
    
    public String[] text = {"I'm a default actor!"};
    
    public NPCActorSkipFloors(String modelName, String map){
        super(modelName,map);
    }
    
    public boolean act(){
        if(this.paused)return true;
        behav.act(this);
        this.depth = y;
        return true;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ret.add(Option.display(this, "Talk", text));
        ret.add(new Option(this){
            public int floors = 10+oRan.nextInt(25);
            public boolean act(){
                
                if(Player.The.inv.checkResources("all", floors*100) == false){
                    String[] disp = new String[1];
                    disp[0] = "You can't afford that!";
                    Player.The.current.owner.display(disp);
                    return true;
                } else {
                    try{
                        CaveWorld CW = (CaveWorld)Player.The.current.owner;
                        CW.depth += floors;
                        owner.dead = true;
                        
                        String[] disp = new String[1];
                        disp[0] = "You feel the floor shift beneath your feet...";
                        Player.The.current.owner.display(disp);
                        
                        Player.The.inv.drainResources("all",floors*100);
                    } catch (Exception E){
                        String[] disp = new String[1];
                        disp[0] = "You have to be underground to skip floors underground...";
                        Player.The.current.owner.display(disp);
                        return true;
                    }
                }
                
                return true;
            }
    
            public String toString(){
                return "Descend "+floors+" floors: "+100*floors +" | all";
            }
            
        });
        ret.add(Option.cancel(this));
        
        return ret;
    }
}
