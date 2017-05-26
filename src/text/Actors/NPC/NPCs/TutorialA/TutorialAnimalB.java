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
public class TutorialAnimalB extends NPCActor{
    public TutorialAnimalB(){
        super("pig","HUMAN");
        super.text = new String[3];
        super.text = new String[2];
        super.text[0] = "Pig: This world is made of letters and colors...";
        super.text[1] = "When you convert something, you break it down into those...";
        super.name = "Friendly Pig";
    }
}
