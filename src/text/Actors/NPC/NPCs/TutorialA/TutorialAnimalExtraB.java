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
public class TutorialAnimalExtraB extends NPCActor{
    public TutorialAnimalExtraB(){
        super("pig","HUMAN");
        super.text = new String[3];
        super.text[0] = "Why, one of those egg heads declared me a racist!";
        super.text[1] = "Foolish squawk of feathers, I'm speciest. Pigs and ducks are not a matter of race. ";
        super.text[2] = "The silliness of their grand folly! I shall oink merrily in amusement!";


        super.name = "Friendly Pig";
    }
}
