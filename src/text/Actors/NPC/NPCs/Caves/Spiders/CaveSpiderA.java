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
public class CaveSpiderA extends MultiNPCActor{
    public CaveSpiderA(){
        super("spider","GREYSCALE");
        
        ColorTuple orange = new ColorTuple(Color.ORANGE,Color.RED,' ');
        
        this.recolor(orange,orange,orange,orange);
        
        this.name = "Cave Spider";
        
        super.text = new String[]{
            "It's not often I see another. How deep are you planning to dig?",
            "The cave spiders worship nature. Many of us are hostile, so come prepared.",
            "If you dig deep enough... who knows what you might find!",
            "The surface is just above... it's why I like to stay here.",
            "Why am I not hostile? I can never choose between Rock, Paper and Scissors, that's why."
        };
        
        randomize();
    }
}
