/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.TutorialB;

import text.Actors.NPC.NPCs.TutorialA.*;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;
import text.Actors.NPC.NPCActorMultichat;

/**
 *
 * @author FF6EB4
 */
public class TutorialAnimaExtraF extends NPCActorMultichat{
    public TutorialAnimaExtraF(){
        super("duck","ANIMAL_DEMO");
        super.text = new String[5];
        super.text[0] = "Sorry about my friend over there. He's weary from his lot in life. ";
        super.text[1] = "You're probably wondering if ducks write bills because we have bills. or not.";
        super.text[2] = "There's a sensible answer, I swear!";
        super.text[3] = "But ultimately?";
        super.text[4] = "Yes.";

        super.name = "Angsty Duck";
    }
}
