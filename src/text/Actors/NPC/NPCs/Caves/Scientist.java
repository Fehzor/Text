/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.Caves;

import text.Actors.NPC.NPCActorFetchQuest;
import text.Actors.Player;
import text.Actors.PuzzleObjects.Bone;
import text.Actors.PuzzleObjects.SciencePowder;
import text.Combat.Enemy.Enemies.Caves.ScienceFight;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Progress;

/**
 *
 * @author FF6EB4
 */
public class Scientist extends NPCActorFetchQuest{
    
    int delay = 0;
    
    public Scientist(){
        super("science","HUMAN_TWO",new SciencePowder());
        
        text = new String[]{
            "...yes, I'm Science.",
            "I stay here, doing research... currently I'm working with a worm byproduct..",
            "The mole people LOVE it and I want to replicate its effects.",
            "Science Powder. Is what they call it.",
            "Could you perhaps go up and get some for me?"
        };
        textFetched = new String[]{"...Thanks for the 'Science Powder'. What does it do?",
        "Well, it sedates the mole people... it's how I operate on them.",
        "They follow blindly, but there are always limits with ethics, and well... ",
        "This is a drug.",
        "It's all in the name of building a perfect race. Fight me if you'd like.",
        "I'll win.",
        "I'm fighting for what I believe in, a better future."};
    }
    
    public boolean act(){
        boolean ret = super.act();
        
        if(this.done){
            delay++;
        }
        
        if(delay > 2){
            this.dead = true;
            Progress.The.SCIENCE = 1;
            ScienceFight add = new ScienceFight();
            add.x = this.x;
            add.y = this.y;
            Player.The.current.addActor(add);
        }
        
        return ret;
    }
    
    public String toString(){
        return "SCIENCE";
    }
}
