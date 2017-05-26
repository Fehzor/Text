/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves.Moles;

import text.Actors.NPC.NPCs.Caves.Spiders.*;
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
public class MoleA extends MultiNPCActor{
    public MoleA(){
        super("mole","HUMAN");
        
        this.name = "Mole";
        
        super.text = new String[]{
            "Hiya friend! Have you a delicious spider for me?",
            "I like to bathe in the mud. It's fun!",
            "Our masters live below and feed us excess. Life is good.",
            "The world as you know it is upside down, not so much for the moles!",
            "You only think you descend, when really you ascend!",
            "Monty? I don't think I've met anyone with that name...",
            "Guac-a-MOLEy? We love the stuff! ...I've actually never had it.. is it good?",
            "Worms are delicious! Om nom nom!",
            "What do you mean by 'domestic'?",
            "Rrrrrrrrrrr!",
            "Arachnophobia? I prefer not to think of that name, thank you.",
            "The surface? Is that what's at the bottom?",
            "Looks like you've REALLY dug yourself into a hole!",
            "Don't skip floors! We want your company!",
            "Subservience is a blessing, not a curse.",
            "It's said that there's an entire city down here!",
            "Science? It's hard to see... well, literally but also to see any other route!",
            "The simple life is the good life, above the surface as we are.",
            "There are pigs? What's a pig? I've never been that far down below.",
            "It's said many years ago that a human descended. It was he who taught us everything.",
            "Thousands of years ago half the moles began to worship Science.",
            "I'm sleepy...of course, there's no night or day. I can sleep whenever.",
            "Your eyes are large. How quaint."
        };
    }
}
