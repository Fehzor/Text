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
public class TutorialAnimalA extends NPCActorImmediate{
    public TutorialAnimalA(){
        super("pig","HUMAN");
        super.text = new String[3];
        super.text[0] = "The pigs are seemingly smart but more verbose,";
        super.text[1] = "they definitely hold themselves above other animals and consider their thoughts and opinions to be greater. ";
        super.text[2] = "However, while they are snooty about it they're rather happy about all of it.";
;
        super.immediate = new String[1];
        super.immediate[0] = "Pig: You can interact with things via the 'a' key!";
        super.name = "Friendly Pig";
    }
}
