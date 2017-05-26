/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.TutorialB;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Instances.Food;
import text.Actors.Messages.Option;
import text.Actors.NPC.NPCs.TutorialA.*;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;
import text.Actors.Player;

/**
 *
 * @author FF6EB4
 */
public class TutorialAnimalF extends NPCActor{
    
    Food check;
    public boolean done = false;
    public TutorialAnimalF(Food hotcake){
        super("duck","ANIMAL_DEMO");
        this.name = "Angsty Duck";
        super.text = new String[4];
        super.text[0] = "Duck: I can't afford to eat....";
        super.text[1] = "Wanna buy me a cake??? I'm begging you.";
        super.text[2] = " ";
        super.text[3] = "There should be a vending machine somewhere in this area...";
;
        check = hotcake;
    }
    
        public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        if(Player.The.inv.inspectStuff().contains(check)){
            Player.The.inv.removeStuff(check);
            ret.add(Option.display(this, "Talk", "THANK YOU SO MUCH! OM NOM NOM NOM"));
            done = true;
        } else if(!done){
            ret.add(Option.display(this, "Talk", text));
            if(!Player.The.current.owner.storyRooms.contains(11)){
                Player.The.current.owner.storyRooms.add(11);
            }
        } else {
            ret.add(Option.display(this, "Talk", "It's crazy, you know? I farmed the wheat to make that cake, yet I have to buy it from the pigs.."));
        }
        
        return ret;
    }
}
