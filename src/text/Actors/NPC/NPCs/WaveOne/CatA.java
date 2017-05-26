/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.WaveOne;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.NPC.NPCActorFetchQuest;
import text.Actors.Player;
import text.Environment.Animals.Animal;

/**
 *
 * @author FF6EB4
 */
public class CatA extends NPCActorFetchQuest{
    /*
    public String[] text = {"I'm a default actor!"};
    public String[] textFetch = {"Thanks so much, Kappa!"};
    public String[] textFetched = {"Want to come to the afterparty?"};
    */
    
    public CatA(Actor mouse){
        super("Fatcat","GREYSCALE_LIGHT",mouse);
        
        this.name = "Fat Cat";
        
        text = new String[]{"There's a naughty mouse on the loose!",
            "He hasn't paid his taxes!",
            "Bring him to me!"};
        
        
        textFetched = new String[]{"Ah the delicious taste of justice! Thanks amigo!"};
    }
    

}
