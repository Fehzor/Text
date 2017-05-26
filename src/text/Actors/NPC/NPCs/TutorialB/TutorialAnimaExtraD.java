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
public class TutorialAnimaExtraD extends NPCActor{
    public TutorialAnimaExtraD(){
        super("duck","ANIMAL_DEMO");
        super.text = new String[3];
        super.text[0] = "You seriously taking directions from those pigs? ";
        super.text[1] = "You couldn't figure out how to look at stuff or check your pockets without muddy bacon balloons telling you how?";
        super.text[2] = "You're a waste. Why even bother walking any further at this point? Death awaits.";

        super.name = "Angsty Duck";
    }
}
