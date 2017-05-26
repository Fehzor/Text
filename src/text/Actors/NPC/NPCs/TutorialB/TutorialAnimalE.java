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
public class TutorialAnimalE extends NPCActorImmediate{
    public TutorialAnimalE(){
        super("duck","ANIMAL_DEMO");
        super.text = new String[4];
        super.text[0] = "Duck: Welcome to duck country!";
        super.text[1] = "Typing 'm' into the command prompt (enter) will display the map.";
        super.text[2] = "On the map, S = Story parts, quack";
        super.text[3] = "And W represents a gateway to another area, and you're at the P, for player.";
;
        super.immediate = new String[4];
        super.immediate[0] = "Duck: Welcome to duck country :D!";
        super.immediate[1] = "Typing 'm' into the command prompt (enter) will display the map.";
        super.immediate[2] = "On the map, S = Story parts, quack";
        super.immediate[3] = "And W represents a gateway to another area, and you're at the P, for player.";
        super.name = "Friendly Duck";
    }
}
