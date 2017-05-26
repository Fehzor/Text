/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.TutorialA;

import text.Actors.NPC.NPCActor;
import text.Actors.NPC.NPCActorImmediate;
import text.WorldFrame.Progress;

/**
 *
 * @author FF6EB4
 */
public class TutorialAnimalD extends NPCActorImmediate{
    public TutorialAnimalD(){
        super("pig","HUMAN");
        super.text = new String[3];
        super.text[0] = "Pig: This world changes constantly...";
        super.text[1] = "Whenever you're ready, close the game and restart it. Not that there's any rush.";
        super.text[2] = "In time, we'll both come to know the tragic extent of your failings.";
        super.immediate = new String[3];
        super.immediate[0] = "Pig: This world changes constantly...";
        super.immediate[1] = "Whenever you're ready, close the game and restart it. Not that there's any rush.";
        super.immediate[2] = "In time, we'll both come to know the tragic extent of your failings.";
        super.name = "Friendly Pig";
    }
    
    public boolean act(){
        super.act();
        
        Progress.The.wildSize = 3;
        
        
        return true;
    }
}
