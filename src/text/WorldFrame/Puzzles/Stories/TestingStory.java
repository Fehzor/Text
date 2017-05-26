/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories;

import java.util.ArrayList;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.*;

/**
 *
 * @author FF6EB4
 */
public class TestingStory extends Story{
    public TestingStory(){
        super();
        super.name = "Test Story";
        this.name = "Test Story";
    }
    
    public ArrayList<Puzzle> loadStory(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        
        ret.add(new ZombieFlavor());

        
        return ret;
    }
}