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
public class TutorialAnimaExtraE extends NPCActor{
    public TutorialAnimaExtraE(){
        super("duck","ANIMAL_DEMO");
        super.text = new String[3];
        super.text[0] = "We got a bill passed to have the pigs explain things instead of assuming we know it.";;
        super.text[1] = "They're smart but they assume everyone knows their roles and how to do them.";
        super.text[2] = "Oh, and before you think you're clever. Yes, ducks write bills. There's no button to laugh so luckily you can keep it to yourself.";

        super.name = "Angsty Duck";
    }
}
