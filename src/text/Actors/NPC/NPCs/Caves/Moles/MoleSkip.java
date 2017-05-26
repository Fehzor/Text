/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves.Moles;

import text.Actors.NPC.NPCs.Caves.Spiders.*;
import java.awt.Color;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCBehaviourWalk;
import text.Actors.NPC.NPCs.Caves.NPCActorSkipFloors;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class MoleSkip extends NPCActorSkipFloors{
    public MoleSkip(){
        super("mole","HUMAN");
        
        this.behav = new NPCBehaviourWalk();
        
        this.name = "Mole";
        
        super.text = new String[]{
            "I can burrow you down quickly, if you'd like... for a cost."
        };
    }
}