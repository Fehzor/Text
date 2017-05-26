/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.WaveOne;

import text.Actors.Actor;
import text.Actors.NPC.NPCActorFetchQuest;
import text.Actors.NPC.NPCBehaviourWalk;
import text.Frame.TextDisplay;
import static text.Utility.LootGenerator.oRan;

/**
 *
 * @author FF6EB4
 */
public class MouseMother extends NPCActorFetchQuest{
    public MouseMother(String before, String after,Actor child){
        super("mouse","ANIMAL_SCHEME_A",child);
        this.text = new String[]{
            before
        };
        
        this.textFetched = new String[]{
            after
        };
        
        behav = new NPCBehaviourWalk();
        
        this.name = "Motherly Mouse";
        
        this.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        this.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
    }
}
