/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories;

import java.util.ArrayList;
import text.WorldFrame.Puzzles.Flavors.TutorialEndPigs;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.DuckCityPuzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.TutorialA;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.TutorialB;

/**
 *
 * @author FF6EB4
 */
public class DuckCityStory extends Story{
    public DuckCityStory(){
        super();
        super.name = "Duck City Story";
        this.name = "Duck City Story";
    }
    
    public ArrayList<Puzzle> loadStory(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        DuckCityPuzzle DCP = new DuckCityPuzzle();
        
        ret.add(DCP);

        return ret;
    }
}
