/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import text.Actors.Instances.CityPart;
import text.Actors.Instances.Door;
import text.Actors.Interactable;
import text.Environment.Animals.Animal;
import text.Environment.Environment;
import text.Environment.Inanimate.*;
import text.Environment.Plants.SmallPlant;
import text.Environment.Plants.Tree;
import text.Game.GamePiece;
import text.Utility.ColorTuple;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class WorldTemplate implements Serializable{
    
    public int worldType;
    public int worldKind; 
    public int puzzleKind = OTHER;
    
    public static final int OTHER = 0;
    //Types
    public static final int GRID_WORLD = -1;
    public static final int LOST_WORLD = -2;
    public static final int GRID_CITY = -3;
    public static final int ROUND_WORLD = -4;
    public static final int CITY_HALLWAY = -5;
    public static final int INDOOR_ROOM = -6;
    public static final int GAME_ROOM = -7;
    
    //Kinds
    public static final int CITY = 1;
    public static final int WILDS = 2;
    public static final int WORDSEARCH = 3;
    public static final int INDOOR = 4;
    public static final int GAME = 5;
    public static final int GRAVEYARD = 6; //Uses wilds but diff puzz
    
    ColorTuple background;
    ColorTuple foreground;
    public int size;
    
    Environment E;
    
    public ArrayList<CityPart> cityPartsA = new ArrayList<>();
    public ArrayList<CityPart> cityPartsB = new ArrayList<>();
    public ArrayList<CityPart> cityPartsC = new ArrayList<>();
    
    public ArrayList<SmallPlant> smallPlantList = new ArrayList<>();;
    public ArrayList<Tree> treeList = new ArrayList<>();
    public ArrayList<Animal> animalList = new ArrayList<>();
    public ArrayList<Rock> rockList = new ArrayList<>();
    
    public ArrayList<Interactable> lootA = new ArrayList<>();
    public ArrayList<Interactable> lootB = new ArrayList<>();
    public ArrayList<Interactable> lootC = new ArrayList<>();
    
    public ArrayList<GamePiece> pieceList = new ArrayList<>();
    
    public Door toSet;
    public ArrayList<WorldTemplate> subWorlds = new ArrayList<>(); //I.e. a house's interior
    
    public ArrayList<Puzzle> puzzList = new ArrayList<>();
    
    int rooms;
    
    public String adjective = "Glitched";
    
    //Used mainly by the boardgame world
    public int[][] gameboard;
    
    public WorldTemplate clone(){
        WorldTemplate ret = new WorldTemplate();
        
        ret.worldType = this.worldType;
        ret.worldKind = this.worldKind;
        ret.puzzleKind = this.puzzleKind;
        
        ret.background = this.background;
        ret.foreground = this.foreground;
        ret.size = this.size;
        ret.rooms = this.rooms;
        
        
        ret.cityPartsA = new ArrayList<>();
        ret.cityPartsB = new ArrayList<>();
        ret.cityPartsC = new ArrayList<>();
        ret.smallPlantList = new ArrayList<>();
        ret.treeList = new ArrayList<>();
        ret.animalList = new ArrayList<>();
        ret.subWorlds = new ArrayList<>();
        ret.rockList = new ArrayList<>();
        ret.pieceList = new ArrayList<>();
        
        for(CityPart CP : cityPartsA){
            ret.cityPartsA.add(CP.clone());
        }
        for(CityPart CP : cityPartsA){
            ret.cityPartsB.add(CP.clone());
        }
        for(CityPart CP : cityPartsA){
            ret.cityPartsC.add(CP.clone());
        }
        for(SmallPlant SP : smallPlantList){
            ret.smallPlantList.add(SP.clone());
        }
        for(Tree T : treeList){
            ret.treeList.add(T.clone());
        }
        for(Animal A : animalList){
            ret.animalList.add(A.clone(this.E));
        }
        for(WorldTemplate WT : subWorlds){
            ret.subWorlds.add(WT.clone());
        }
        for(GamePiece GP : pieceList){
            ret.pieceList.add(GP);
        }
        for(Rock R : rockList){
            ret.rockList.add(R.clone());
        }
        
        for(Interactable l : lootA){
            ret.lootA.add(l.clone());
        }
        for(Interactable l : lootB){
            ret.lootB.add(l.clone());
        }
        for(Interactable l : lootC){
            ret.lootC.add(l.clone());
        }
        
        ret.toSet = this.toSet;

        if(this.E == null){
            ret.E = null;
        } else {
            ret.E = this.E.clone();
        }
        
        ret.gameboard = this.gameboard;
        
        ret.adjective = this.adjective;
        
        return ret;
    }
    
    public static void morph(WorldTemplate WT){
        ColorTuple A = WT.background;
        Color a = A.primary;
        int r = a.getRed();
        int b = a.getGreen();
        int g = a.getBlue();
        r += oRan.nextInt(30) - oRan.nextInt(30);
        b += oRan.nextInt(30) - oRan.nextInt(30);
        g += oRan.nextInt(30) - oRan.nextInt(30);
        if(r > 255) r = 255;
        if(r < 0) r = 0;
        if(b > 255) b = 255;
        if(b < 0) b = 0;
        if(g > 255) g = 255;
        if(g < 0) g = 0;
        
        Color gnu = new Color(r,b,g);
        A.primary = gnu;
        
        if(WT.worldKind == WT.WILDS){
            WildTemplate.morph(WT);
        }
        
        if(WT.worldKind == WT.CITY){
            CityTemplate.morph(WT);
        }
    }
}
