/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.WaveOne;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorFetchQuest;
import text.Actors.Player;
import text.Environment.Animals.Animal;
import text.Utility.ColorTuple;
import text.WorldFrame.Puzzles.Puzzle;

/**
 *
 * @author FF6EB4
 */
public class DuckA extends NPCActor{
    /*
    public String[] text = {"I'm a default actor!"};
    public String[] textFetch = {"Thanks so much, Kappa!"};
    public String[] textFetched = {"Want to come to the afterparty?"};
    */
    
    Puzzle saved;
    
    public DuckA(Puzzle P){
        super("Duck","ROCKY");
        
        this.name = "Lonely Duck";
        
        text = new String[]{"I wish I could move to the city....","*Audible sigh*"};
        
        this.saved = P;
    }
    
    public boolean act(){
        try{
            if(this.current.owner.name.toLowerCase().contains("city")){
                saved.solved = true;
                text = new String[]{"THANK YOU SO MUCH!"};
            }
        } catch(Exception E){
            
        }
        return true;
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
