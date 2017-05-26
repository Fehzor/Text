/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.TutorialA;

import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;

/**
 *
 * @author FF6EB4
 */
public class TutorialAnimalExtraA extends NPCActor{
    public TutorialAnimalExtraA(){
        super("pig","HUMAN");
        super.text = new String[3];
        super.text[0] = "Pigs think they're smarter than ducks!";
        super.text[1] = "While that may be true....";
        super.text[2] = "...forgot where I was going with that.";


        super.name = "Friendly Pig";
    }
}
