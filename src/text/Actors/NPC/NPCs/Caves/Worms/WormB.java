/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves.Worms;

import text.Actors.PuzzleObjects.Bone;
import text.Actors.NPC.NPCActorFetchQuest;
import text.Actors.Player;
import text.Actors.PuzzleObjects.SciencePowder;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class WormB extends NPCActorFetchQuest{
    public WormB(){
        super("worm","HUMAN",new Bone());
        
        text = new String[]{"You should totally bring me bones some time. I love bones."};
        textFetched = new String[]{"Man I'm full, those bones really hit the spot."};
        
    }
    
    public boolean act(){
        boolean ret = super.act();
        
        if(this.done){
            if(oRan.nextInt(1000) == 23){
                this.done = false;
                SciencePowder SP = new SciencePowder();
                SP.x = this.x;
                SP.y = this.y;
                Player.The.current.addActor(SP);
            }
        }
        
        return ret;
    }
    
    public String toString(){
        return "Hungry Worm";
    }
}
