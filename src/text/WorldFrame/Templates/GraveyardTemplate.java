/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.PuzzleObjects.TalkRock;
import text.Environment.Environment;
import text.Environment.Inanimate.Rock;
import text.Environment.Soil;
import text.Utility.ColorTuple;
import text.Utility.Diction.LiteralDictionary;
import text.WorldFrame.GameCoordinator;
import text.WorldFrame.Puzzles.Flavors.Flavor;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WildTemplate.makeAnimalList;
import static text.WorldFrame.Templates.WildTemplate.makeSmallPlantList;
import static text.WorldFrame.Templates.WildTemplate.makeTreeList;
import static text.WorldFrame.Templates.WorldTemplate.GRID_WORLD;
import static text.WorldFrame.Templates.WorldTemplate.WILDS;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class GraveyardTemplate extends WildTemplate{
        public GraveyardTemplate(){
        background = new ColorTuple(Color.DARK_GRAY,Color.WHITE,' ');
        foreground = new ColorTuple(Color.LIGHT_GRAY,Color.WHITE,' ');
        
        this.worldKind = GRAVEYARD;
        this.worldType = GRID_WORLD;
        this.puzzleKind = GRAVEYARD;
        E = new Environment(0,0,0,0);
        E.soil.addTint(background);
        
        smallPlantList = makeSmallPlantList(E,3);
        treeList = makeTreeList(E,1);
        //plantList = makePlantList(E,2);
        //animalList = makeAnimalList(E,1);
        rockList = makeRockList(foreground,E,3);
        rooms = GameCoordinator.The.prog.graveyardSize * GameCoordinator.The.prog.graveyardSize;
        size = GameCoordinator.The.prog.graveyardSize;
        
        this.adjective = LiteralDictionary.getAdjective();
        this.adjective = adjective.charAt(0)+adjective.substring(1).toLowerCase();
        
    }
        
    public GraveyardTemplate(WildTemplate source){
        this.worldKind = WILDS;
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
        
        //puzzList.add(Puzzle.getRandomPuzzle(WILDS));
        //puzzList.add(Puzzle.getRandomPuzzle(WILDS));
        //puzzList.add(Flavor.getRandomFlavor(WILDS));
    }
        
    public static ArrayList<Rock> makeRockList(ColorTuple tint, Environment E, int i) {
        ArrayList<Rock> ret = new ArrayList<>();
        
        for(int j = 0; j < i; ++j){
            ret.add(new TalkRock("Tombstone",1, E));
        }
        
        return ret;
    }
}
