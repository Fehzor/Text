/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC;

import java.awt.Color;
import text.Actors.NPC.*;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Behaviour.Behaviour;
import text.Frame.TextDisplay;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;
import text.Utility.SuperRandom;

/**
 *
 * @author FF6EB4
 */
public class NPCActorPickupable extends NPCActor{
    Behaviour behav;
    
    
    public NPCActorPickupable(String modelName, String map){
        super(modelName,map);
    }

        public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        Option C = Option.pickUp(this, new ColorTuple(Color.BLACK,Color.WHITE,'A'));
        
        Option D = Option.setDown(this);
        
        if(!held){
            ret.add(C);
        }
        if(held){
            ret.add(D);
        }
        
        return ret;
    }

}
