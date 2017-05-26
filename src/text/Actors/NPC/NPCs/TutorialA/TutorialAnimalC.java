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
public class TutorialAnimalC extends NPCActorImmediate{
    public TutorialAnimalC(){
        super("pig","HUMAN");
        super.text = new String[3];
        super.text[0] = "Pig: Pressing enter brings up a prompt..";
        super.text[1] = "Type inv to bring up your inventory of letters/colors";
        super.text[2] = "Type obj to view held physical objects";
        super.immediate = new String[3];
        super.immediate[0] = "Pig: Pressing enter brings up a prompt..";
        super.immediate[1] = "Type inv to bring up your inventory of letters/colors";
        super.immediate[2] = "Type obj to view held physical objects";
        super.name = "Friendly Pig";
    }
}
