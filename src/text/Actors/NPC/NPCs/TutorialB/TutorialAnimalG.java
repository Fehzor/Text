/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.TutorialB;

import text.Actors.NPC.NPCs.TutorialA.*;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;

/**
 *
 * @author FF6EB4
 */
public class TutorialAnimalG extends NPCActor{
    public TutorialAnimalG(){
        super("pig","ANIMAL_DEMO");
        this.name = "Wealthy Pig";
        super.text = new String[4];
        super.text[0] = "Pig: Hey there..";
        super.text[1] = "Vending machines and other vendors take letters/colors to function.";
        super.text[2] = "This machine takes all kinds of letters/colors. Some take dim letters, or even just red.";
        super.text[3] = "When you go to pay, random letters etc. will be selected.";
;

    }
}
