/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories;

import java.util.ArrayList;
import text.WorldFrame.Puzzles.Flavors.TutorialEndPigs;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.CatMousePuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.MouseMousePuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.TutorialA;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.TutorialB;

/**
 *
 * @author FF6EB4
 */
public class CatMouseStory extends Story{
    public CatMouseStory(){
                super();
        super.name = "Cat Story";
        this.name = "Cat Story";
    }
    
    public ArrayList<Puzzle> loadStory(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        CatMousePuzzle CMP = new CatMousePuzzle();
        MouseMousePuzzle MOUSE = new MouseMousePuzzle();
        
        ret.add(CMP);
        ret.add(MOUSE);

        return ret;
    }
    
}
