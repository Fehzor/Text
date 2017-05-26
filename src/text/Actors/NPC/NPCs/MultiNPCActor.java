/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs;

import text.Actors.NPC.*;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Messages.Option;
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

/**
 *
 * @author FF6EB4
 */
public class MultiNPCActor extends NPCActor{
    public Behaviour behav;
    
    public String[] text = {"I'm a default actor!"};
    int mynum = 0;
    
    public MultiNPCActor(String modelName, String map){
        super(modelName, map);
    }

    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ret.add(Option.display(this, "Talk", text[mynum]));
        ret.add(Option.cancel(this));
        
        return ret;
    }
    
    public void randomize(){
        this.mynum = oRan.nextInt(text.length);
    }
}
