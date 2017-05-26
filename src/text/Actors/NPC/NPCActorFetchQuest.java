/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC;

import text.Actors.NPC.*;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import text.Frame.TextDisplay;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ImageLoader;
import text.Utility.SuperRandom;

/**
 *
 * @author FF6EB4
 */
public class NPCActorFetchQuest extends NPCActor{
    
    public Actor fetch;
    public boolean done = false;
    
    public String[] text = {"I'm a default actor!"};
    public String[] textFetched = {"Want to come to the afterparty?"};
    
    public NPCActorFetchQuest(String modelName, String map, Actor fetch){
        super(modelName,map);
        this.behav = new NPCBehaviourWalk();
        this.fetch = fetch;
    }
    
    public boolean act(){
        return super.act();
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ArrayList<Actor> aList = Player.The.inv.inspectStuff();
        
        boolean contains = false;
        for(Actor A : aList){
                try{
                    //System.out.println(A.toString()+" "+fetch.toString());
                    if(A.toString().equals(fetch.toString())){
                        contains = true;
                    }
                } catch (Exception E){};
            
        }
        
        //If done is false we check to see if done should be true.
        if(!done && contains){
            Player.The.inv.removeStuff(fetch);
            ret.add(Option.display(this, "Talk", textFetched));
            done = true;
        }
        
        //If done is true it's over and the player doesn't have it so it's time for fetched.
        if(done&&!contains){
            ret.add(Option.display(this, "Talk", textFetched));
            return ret;
        }
        
        if(done&&contains){
            ret.add(Option.display(this, "Talk", textFetched));
            return ret;
        }
        
        //If done is false we check to see if the player doesn't have it.
        if(!done && !contains){
            ret.add(Option.display(this, "Talk", text));
            if(!Player.The.current.owner.storyRooms.contains(11)){
                Player.The.current.owner.storyRooms.add(11);
            }
            return ret;
        }
        
        
        
        return ret;
    }
    

}
