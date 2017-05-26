/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import text.WorldFrame.Templates.WildTemplate;
import text.Environment.Plants.SmallPlant;
import text.WorldFrame.Worlds.GridWorld;
import text.Environment.Animals.Animal;
import java.util.*;
import java.io.*;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Player;
import text.Environment.*;
import text.Frame.TextDisplay;
//import text.Tools.ToolPart;
import static text.WorldFrame.Worlds.GridWorld.oRan;
import text.Editor.*;
import text.Images.TextImageBasic;
import text.Utility.ImageBuilder;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.MainWorld;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Flavors.Flavor;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Puzzles.Stories.Story;
import text.WorldFrame.Room;
import static text.WorldFrame.Templates.WorldTemplate.CITY;
import static text.WorldFrame.Templates.WorldTemplate.GAME;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import static text.WorldFrame.Templates.WorldTemplate.INDOOR;
import static text.WorldFrame.Templates.WorldTemplate.OTHER;
import static text.WorldFrame.Templates.WorldTemplate.WILDS;
import text.WorldFrame.World;

/**
 * A class specializing in randomly generating worlds.
 * @author FF6EB4
 */
public class WorldBuilder {
    private WorldBuilder WB = new WorldBuilder();
    public WorldBuilder(){}
    
    public static Random oRan = new Random();
    
    public static int TYPE_NONE = 0;
    public static int TYPE_GRID = 1;  
    
    
    
    //Create the main world.
    public static World generateMain(){
        World main = new MainWorld();
        Room R = new Room(0,main);
        R.id = -1;
        main.addRoom(R);
        return main;
    }
    
    public static World generateEditingWorld(){
        World main = new MainWorld();
        
        main.addRoom(new Room(-1,main));
        
        TextEditor TE = new TextEditor();
        ((MainWorld)main).center.addActor(TE);
        
        TextDisplay.Display.JP.addMouseListener(TE);
        
        return main;
    }
    
    public static World generate(WorldTemplate WT){
        World ret = null;
        if(WT.worldKind == WILDS || WT.worldKind == GRAVEYARD){
            ret = WildBuilder.generate(WT);
        }
        
        if(WT.worldKind == CITY){
            ret = CityBuilder.generate(WT);
        }
        
        if(WT.worldKind == INDOOR){
            ret = IndoorBuilder.generate(WT);
        }
        
        if(WT.worldKind == GAME){
            ret = GameBuilder.generate(WT);
        }
        
        for(int i = 0; i<WT.puzzList.size(); ++i){
            if(ret==null){
                return null;
            }
            Puzzle P = WT.puzzList.get(i);
            //System.out.println(WT.puzzList.size());
            //System.out.println("ADDING PUZZLE");
            
            ret.addPuzzle(P);
        }
        
        if(WT.puzzleKind != OTHER){
            ret.addPuzzle(Puzzle.getRandomPuzzle(WT.puzzleKind));
            ret.addPuzzle(Flavor.getRandomFlavor(WT.puzzleKind));
            
            try{
                ret.addPuzzle(Progress.getStory(WT.puzzleKind));
                
            } catch (Exception E){System.out.println("NO STORIES");}
            
            try{
                ret.addPuzzle(Progress.getFlavor(WT.puzzleKind));
                
            } catch (Exception E){System.out.println("NO FLAVORS");}
        }
        
        
        
        return ret;
    }
}
