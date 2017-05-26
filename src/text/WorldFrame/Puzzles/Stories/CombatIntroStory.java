/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories;

import java.util.ArrayList;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.GentRockPuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.RockerRockPuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.ZombieFlavor;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.Zombie_aPuzzle;

/**
 *
 * @author FF6EB4
 */
public class CombatIntroStory extends Story {
    
    public CombatIntroStory(){
        super();
        super.name = "CombatIntro";
        this.name = "CombatIntro";
    }
    
    public ArrayList<Puzzle> loadStory(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        //System.out.println("LOADING GRP INTO STORY LIST");
        GentRockPuzzle GRP = new GentRockPuzzle();
        GRP.owner = this;
        
        RockerRockPuzzle RRP = new RockerRockPuzzle();
        RRP.owner = this;
        
        Zombie_aPuzzle ZAP = new Zombie_aPuzzle();
        ZAP.owner = this;
        
        ZombieFlavor ZF = new ZombieFlavor();

        ret.add(GRP);
        ret.add(RRP);
        ret.add(ZAP);
        ret.add(ZF);

        return ret;
    }
}
