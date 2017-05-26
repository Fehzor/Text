/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles;

import java.util.*;
import java.io.Serializable;
import text.Actors.Instances.Door;
import text.Environment.Inanimate.Rock;
import text.Frame.TextDisplay;
import text.WorldFrame.Puzzles.Stories.Story;
import text.WorldFrame.Templates.WildTemplate;
import text.WorldFrame.Templates.WorldBuilder;
import static text.WorldFrame.Templates.WorldTemplate.WILDS;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class Puzzle implements Observer, Serializable{
    public static ArrayList<Puzzle> puzzListWILDS = loadPuzzList();
    
    public boolean first = true;
    public boolean solved = false;
    public boolean done = false;
    
    public Story owner;
    
    public int where = WILDS; //Refer to city types etc. Default to wilds.
    
    public synchronized void update(Observable o, Object arg){
        World w = (World)o;
        if(first){
            first = false;
            this.initiate(w);
        }
        
        if(done == false){
            this.update(w);
        }
        
        if(solved && done == false){
            solve(w);
            done = true;
        }
    }
    
    public synchronized void update(World w){
        //Override this method!
    }
    
    public synchronized void initiate(World w){
        first = false;
        //Override this method!
    }
    
    
    public synchronized void solve(World w){
        solved = true;
        done = true;
        
    }
    
    
    public static Puzzle getRandomPuzzle(int type){
        int r = oRan.nextInt(puzzListWILDS.size());
        return puzzListWILDS.get(r);
        
        //return new HeldRockPuzzle();
    }
    
    /**
     * Loads all of the puzzles into a list and returns that.
     */
    private static ArrayList<Puzzle> loadPuzzList(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        ret.add(new LonelyRockPuzzle());
        ret.add(new ParentalRockPuzzle());
        ret.add(new ObtrusiveRockPuzzle());
        ret.add(new VendingPuzzle());
        ret.add(new SuicidalRockPuzzle());
        ret.add(new GossipRockPuzzle());
        ret.add(new CrazyRockPuzzle());
        ret.add(new HeldRockPuzzle());
        
        return ret;
    }
}
