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
public class TutorialAnimaExtraG extends NPCActorMultichat{
    public TutorialAnimaExtraG(){
        super("duck","ANIMAL_DEMO");
        
        super.text = new String[4];
        super.text[0] = "Go on far enough and you're in the wilds.";
        super.text[1] = "Bad news, there's no point in existing.";
        super.text[2] = "Good news, pressure's off. Eh, that's something, right?";
        super.text[3] = "Whatever.";


        super.name = "Angsty Duck";
    }
}
