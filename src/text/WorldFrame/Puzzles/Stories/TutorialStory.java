/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories;

import java.util.ArrayList;
import text.WorldFrame.Puzzles.Flavors.TutorialEndPigs;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.GentRockPuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.RockerRockPuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.TutorialA;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.TutorialB;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.ZombieFlavor;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.Zombie_aPuzzle;

/**
 *
 * @author FF6EB4
 */
public class TutorialStory extends Story{
    public TutorialStory(){
        super();
        super.name = "Tutorial Story";
        this.name = "Tutorial Story";
    }
    
    public ArrayList<Puzzle> loadStory(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        TutorialA a = new TutorialA();
        a.owner = this;
        
        TutorialB b = new TutorialB();
        b.owner = this;
        
        
        ret.add(a);
        ret.add(b);

        return ret;
    }
}



