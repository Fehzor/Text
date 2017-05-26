/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Flavors;

import java.util.ArrayList;
import java.util.Observable;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class Flavor extends Puzzle{
    public static ArrayList<Puzzle> flavListWILDS = loadFlavListWilds();
    public static ArrayList<Puzzle> flavListGRAVE = loadFlavListGraves();
    public boolean first = true;
    
    public synchronized void update(Observable o, Object arg){
        World w = (World)o;
        if(first){
            first = false;
            this.initiate(w);
        }
    }
    
    public static Puzzle getRandomFlavor(int type){
        
        if(type == GRAVEYARD){
            int r = oRan.nextInt(flavListGRAVE.size());
            return flavListGRAVE.get(r);
        }
        
        int r = oRan.nextInt(flavListWILDS.size());
        return flavListWILDS.get(r);
        //return new GunPlantFlavor();
    }
    
    /**
     * Loads all of the puzzles into a list and returns that.
     */
    private static ArrayList<Puzzle> loadFlavListWilds(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        ret.add(new RevoltRockFlavor());
        ret.add(new SleepyRockFlavor());
        ret.add(new SurprisePlantFlavor());
        ret.add(new GunPlantFlavor());
        ret.add(new PostFactRockFlavor());

        return ret;
    }
    private static ArrayList<Puzzle> loadFlavListGraves(){
        ArrayList<Puzzle> ret = new ArrayList<>();
        
        ret.add(new TombStoneFlavorA());
        ret.add(new TombStoneFlavorB());
        
        return ret;
    }
    
}
