/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves.Worms;

import text.Actors.NPC.NPCs.Caves.MolePeople.*;
import text.Actors.NPC.NPCs.Caves.Moles.*;
import text.Actors.NPC.NPCs.Caves.Spiders.*;
import java.awt.Color;
import text.Actors.NPC.NPCs.TutorialA.*;
import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;
import text.Actors.NPC.NPCs.MultiNPCActor;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.Utility.SuperRandom;

/**
 *
 * @author FF6EB4
 */
public class WormA extends MultiNPCActor{
    public WormA(){
        super("worm","HUMAN");
        
        this.name = "Delightful Worm";
        
        super.text = new String[]{
            "Everything eats us. That's why we're here in the middle.",
            "The moles don't dare rise past us, for Arachnophobia lies in wait.",
            "The spiders don't dare burrow beneath us, for the mole people will consume their souls.",
            "We aren't blind. You just can't see our eyes.",
            "We consume the bones from above as they drift downwards. Life is good.",
            "I'm just not feeling hungry right now.",
            "Why are we so big? Why are you so small!?!?",
            "Snakelike, you say? It was Science that made us this way.",
            "Science may have made us large, but nature created us.",
            "To strike a balance is the key to anything.",
            "Perhaps you could bring us bones some time? Some of us would appreciate it.",
            "Science Powder? That's........ well after we eat...",
            "Oh, we know ALL about Science Powder....",
            "Science Powder is the reason I don't follow Science. It's just silly.",
            "Holy hand grenades? Why would we- oh, that.",
            "No, we don't have wars with goofy weapons? That's the surface worms.",
            "Jim? Never heard of him.",
            "We don't actually eat dirt. But we kind of poop out a dust like thing?",
            "If you wait long enough we WILL give a dump. Not about what you have to say.",
            "We tend to stick toghether. There is strength in numbers... kind of? I mean..", //20 lines
            "We don't really have much to say, being worms and all.",
            "LOL I love being a worm!",
            "If I had a suit why I'd...",
            "Ok tell me you don't love our walking animation? Isn't it hilarious?"
        };
        
        randomize();
    }
}
