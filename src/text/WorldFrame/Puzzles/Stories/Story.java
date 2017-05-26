/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories;

import java.io.Serializable;
import java.util.ArrayList;
import text.Actors.Player;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.GameCoordinator;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WorldTemplate.CITY;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import static text.WorldFrame.Templates.WorldTemplate.WILDS;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class Story extends Puzzle{

    public ArrayList<Puzzle> partList = loadStory();
    public String name = "Story";
    
    public synchronized void initiate(World w){
        super.initiate(w);
        Player.The.current.owner.addPuzzle(this.getPuzz());
    }
    
    public ArrayList<Puzzle> loadStory(){
        return new ArrayList<>();
    }
    
    public Puzzle getPuzz(){
        int part = Progress.getStoryProgress(this.name);
        return partList.get(part);
    }
    
    public void advance(){
        Progress.advanceStory(this.name);
    }
    
    public void implementStory(){
        Player.The.current.owner.clearStory();
        Puzzle P = this.getPuzz();
        Player.The.current.owner.addPuzzle(P);
    }

}
