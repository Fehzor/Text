/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves.Spiders;

import java.awt.Color;
import text.Actors.NPC.NPCs.TutorialA.*;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;
import text.Actors.NPC.NPCs.MultiNPCActor;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class CaveSpiderB extends MultiNPCActor{
    public CaveSpiderB(){
        super("spider","ANIMAL_SCHEME_B");
        
        ColorTuple orange = new ColorTuple(Color.ORANGE,Color.RED,' ');
        
        this.recolor(orange,orange,orange,orange);
        
        this.name = "Cave Spider";
        
        super.text = new String[]{
            "These depths are all we've ever known.",
            "What is the sun like?",
            "I'd venture onwards but I rather like it here.",
            "Our colors give us away. I was not born to fight.",
            "It always starts the same.",
            "When things wander into these depths we eat them, or they die trying to leave. Keep digging.",
            "It looks like you've dug yourself into quite the hole. There's only one way to go.",
            "Always digging onwards. How qaint.",
            "Will you tell me a story?"
        };
        
        randomize();
    }
}
