/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Interactable;
import text.Actors.NewsPaper;
import text.Environment.Animals.Animal;
import text.Environment.Environment;
import text.Environment.Plants.SmallPlant;
import text.Environment.Soil;
import text.Environment.Inanimate.Rock;
import text.Environment.Plants.Tree;
import text.Utility.ColorTuple;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageLoader;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Flavors.Flavor;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class WildTemplate extends WorldTemplate{

    public static ArrayList<Interactable> natLoot = getNatLoot();
    public static ArrayList<Interactable> spLoot = getSPLoot();
    
    public WildTemplate(){
        int r = oRan.nextInt(255);
        int g = oRan.nextInt(255);
        int b = oRan.nextInt(255);
        background = new ColorTuple(new Color(r,g,b),Color.WHITE,' ');
        foreground = new ColorTuple(new Color(255-r,255-g,255-b),Color.WHITE,' ');
        
        this.worldKind = WILDS;
        this.puzzleKind = WILDS;
        this.worldType = GRID_WORLD;
        E = new Environment();
        E.soil.addTint(background);
        
        smallPlantList = makeSmallPlantList(E,3);
        treeList = makeTreeList(E,1);
        //plantList = makePlantList(E,2);
        animalList = makeAnimalList(E,1);
        rockList = makeRockList(foreground,E,3);
        lootA = makeLootList();
        
        rooms = Progress.The.wildSize*Progress.The.wildSize;
        size = Progress.The.wildSize;
        
        this.adjective = LiteralDictionary.getAdjective();
        this.adjective = adjective.charAt(0)+adjective.substring(1).toLowerCase();
    }
    
    public WildTemplate(WildTemplate source){
        this.worldKind = source.worldKind;
        this.puzzleKind = source.puzzleKind;
        double a = source.E.waterLevel;
        double b = source.E.sunlight;
        double c = source.E.humidity;
        double d = source.E.temperature;
        Soil s = new Soil(source.E.soil);
        this.adjective = source.adjective;
        
        this.E = new Environment(a,b,c,d,s);
        this.smallPlantList = new ArrayList<>();
        this.smallPlantList.addAll(source.smallPlantList);
        //this.plantList = source.plantList;
        this.animalList = source.animalList;
        this.rockList = source.rockList;
        this.rooms = source.rooms;
        this.background = source.background;
        this.foreground = source.foreground;
    }
    
    /*
    public static ArrayList<Plant> makePlantList(Environment E, int numPlants){
        ArrayList<Plant> ret = new ArrayList<>();
        for(int i = 0; i<numPlants; ++i){
            ret.add(new Plant(E));
        }
        return ret;
    }
    */
    public static ArrayList<SmallPlant> makeSmallPlantList(Environment E, int numPlants){
        ArrayList<SmallPlant> ret = new ArrayList<>();
        for(int i = 0; i<numPlants; ++i){
            ret.add(SmallPlant.makeSmallPlant(E));
        }
        return ret;
    }
    
    public static ArrayList<Tree> makeTreeList(Environment E, int numPlants){
        ArrayList<Tree> ret = new ArrayList<>();
        for(int i = 0; i<numPlants; ++i){
            ret.add(Tree.makeTree(E));
        }
        return ret;
    }
    
    public static ArrayList<Animal> makeAnimalList(Environment E, int numAnimals){
        ArrayList<Animal> ret = new ArrayList<>();
        for(int i = 0; i<numAnimals; ++i){
            ret.add(Animal.createAnimalSpecies(E));
        }
        return ret;
    }

    public static ArrayList<Rock> makeRockList(ColorTuple tint, Environment E, int i) {
        ArrayList<Rock> ret = new ArrayList<>();
        
        for(int j = 0; j < i; ++j){
            ret.add(new Rock(tint, E));
        }
        
        return ret;
    }
    
    public static ArrayList<Interactable> makeLootList(){
        ArrayList<Interactable> ret = new ArrayList<>();
        
        for(int i = 0; i < 3; ++i){
            int index = oRan.nextInt(natLoot.size());
            
            for(int j = 0; j < 32; ++j){
                ret.add(natLoot.get(index).clone());
            }
        }
        
        for(int i = 0; i < 4; ++i){
            int index = oRan.nextInt(spLoot.size());
        ret.add(spLoot.get(index).clone());
        }
        
        
        return ret;
    }
    
    public static void morph(WorldTemplate WT){
        if(oRan.nextInt(100) < 35){
            SmallPlant SP = makeSmallPlantList(WT.E,1).get(0);
            WT.smallPlantList.set(0,SP);
        }
        if(oRan.nextInt(100) < 35){
            Tree T = makeTreeList(WT.E,1).get(0);
            WT.treeList.set(0,T);
        }
        if(WT.animalList.size() > 0 && oRan.nextInt(100) < 35){
            Animal A = makeAnimalList(WT.E,1).get(0);
            WT.animalList.set(0,A);
        }
    }
    
    ////////////

    private static ArrayList<Interactable> getNatLoot() {
        ArrayList<Interactable> ret = new ArrayList<>();
        
        NewsPaper NP = new NewsPaper();
        ret.add(NP);
        
        return ret;
    }
    
    private static ArrayList<Interactable> getSPLoot() {
        ArrayList<Interactable> ret = new ArrayList<>();
        
        NewsPaper NP = new NewsPaper();
        ret.add(NP);
        
        return ret;
    }
    
}
