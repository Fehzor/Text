/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves.MolePeople;

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
public class MoleManA extends MultiNPCActor{
    public MoleManA(){
        super("moleman","HUMAN");
        
        this.name = "Mole Person";
        
        super.text = new String[]{
            "....We can see not the past, nor the future, nor the present.",
            "Our religion is one of Science and reason.",
            "The spiders worshipped Nature. We are beyond Nature. We eat spiders.",
            "There is no god in the traditional sense, save for Science.",
            "The deeper you go the closer to Science you will become.",
            "I'm sure you blindly follow something.",
            "Our society is the most advanced to ever be. This makes us civilized.",
            "Our society is the most advanced to ever be. This makes you barbaric.",
            "The worms that live here cannot see either.",
            "Evidence suggests you had help coming here, judging by your tools. That's technology.",
            "The surface has been theorized, but to see one from there!",
            "Our leader, Science, lives below. He created us long ago.. to think we were just mere moles!",
            "Day to day life is far less important than the future... to forsee is to become god.",
            "We lack names. We never had them before, and we don't have them now.",
            "In the old days we ate roots. Then the spiders came, and forced us below.",
            "Arachnophobia... the terror is real. Don't tell me you've met.",
            "The mole people don't actually eat spiders. The moles do. It's why we keep them.",
            "Spiders dare not come close to us now.",
            "Ganesha was nothing before humans endorsed him. Neither will be you.",
            "They say the future is bright. We believe, though we cannot see bright.", //20 lines
            "Global warming? It happened all right. Ages ago. No one stopped it, no one cared.",
            "The space program was a success. For the rich, of course.. But also for Science!",
            "Climate change wasn't real, until it was. Like boiling a frog. Are you a frog?",
            "We've studdied the ins and outs of this world.",
            "They say I'm an addict.... got any Science Powder? Not for me? Sigh.",
            "Our homes are said to rival what humanity left behind eons ago."
        };
        
        randomize();
    }
}
