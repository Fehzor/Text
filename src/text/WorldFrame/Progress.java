/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Puzzles.Flavors.CityDuckFlavor;
import text.WorldFrame.Puzzles.Flavors.Flavor;
import text.WorldFrame.Puzzles.Flavors.TutorialEndPigs;
import text.WorldFrame.Puzzles.Stories.*;
import text.WorldFrame.Puzzles.Stories.Story;
import text.WorldFrame.Puzzles.Stories.StoryPuzzles.ZombieFlavor;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import static text.WorldFrame.Templates.WorldTemplate.WILDS;
import static text.WorldFrame.Templates.WorldTemplate.CITY;

/**
 *
 * @author FF6EB4
 */
public class Progress implements Serializable{
    public static Progress The;
    public static HashMap<String,Story> allStories;
    public static HashMap<String,Flavor> allFlavors;
    
    private boolean first = true;
    
    public Progress(){
        The = this;
    }
    
    //PROGRESS WITH SPAWNING ALGORITHMS IS HERE
    public int graveyardSize = 0;
    public int wildSize = 2;
    public int citySize = 0;
    
    public int gameWorldLevel = 0;
    
    public int SCIENCE = 0;//0,1,2, has the player activated? If so, fought?
    
    //OTHER VARIABLES RELATING TO SPAWNINGS ALGORITHMS
    public ArrayList<String> animalChoices = new ArrayList<>();
    public HashMap<String,Double> animalFriendliness = new HashMap<>();
    
    
    //PROGRESS IN THE GAME IS DEFINED HERE VIA STORIES
    private HashMap<String, Integer> storyProgress = new HashMap<>();
    private ArrayList<String> storiesGraveYard = new ArrayList<>();
    private ArrayList<String> flavorsGraveYard = new ArrayList<>();
    
    private ArrayList<String> storiesWilds = new ArrayList<>();
    private ArrayList<String> flavorsWilds = new ArrayList<>();
    
    private ArrayList<String> storiesCity = new ArrayList<>();
    private ArrayList<String> flavorsCity = new ArrayList<>();
    
    public static Story getStory(int area){
        String s = "";
        
        if(area == GRAVEYARD){
            s = The.storiesGraveYard.get(oRan.nextInt(The.storiesGraveYard.size()));
        }
        if(area == WILDS){
            s = The.storiesWilds.get(oRan.nextInt(The.storiesWilds.size()));
        }
        if(area == CITY){
            s = The.storiesCity.get(oRan.nextInt(The.storiesCity.size()));
        }
        
        return allStories.get(s);
    }

    
    public static Flavor getFlavor(int area){
        String s = "";
        
        if(area == GRAVEYARD && The.flavorsGraveYard.size() > 0){
            s = The.flavorsGraveYard.get(oRan.nextInt(The.flavorsGraveYard.size()));
        }
        if(area == WILDS && The.flavorsWilds.size() > 0){
            s = The.flavorsWilds.get(oRan.nextInt(The.flavorsWilds.size()));
        }
        if(area == CITY && The.flavorsCity.size() > 0){
            s = The.flavorsCity.get(oRan.nextInt(The.flavorsCity.size()));
        }
        
        if(s.length() == 0){
            return null;
        }
        
        return allFlavors.get(s);
    }
    
    public static int getStoryProgress(String s){
        if(The.storyProgress.get(s) == null){
            return -1;
        } else {
            return The.storyProgress.get(s); 
        }
    }
    
    public static void advanceStory(String s){
        if(The.storyProgress.get(s) == null){
            return;
        }
        int a = The.storyProgress.get(s);
        The.storyProgress.put(s,a+1);
    }
    
    public static void putStory(String s, int area){
        
        if(The.storyProgress.get(s) != null){
            return;
        }
        
        if(area == GRAVEYARD){
            The.storiesGraveYard.add(s);
        }
        if(area == WILDS){
            The.storiesWilds.add(s);
        }
        if(area == CITY){
            The.storiesCity.add(s);
        }
        The.storyProgress.put(s, 0);
    }
    
    public static void addFlavor(String s, int area){
        if(area == GRAVEYARD){
            The.flavorsGraveYard.add(s);
        }
        if(area == WILDS){
            The.flavorsWilds.add(s);
        }
        if(area == CITY){
            The.flavorsCity.add(s);
        }
    }
    
    public static void removeFlavor(String s){
        try{
            The.flavorsGraveYard.remove(s);
        } catch (Exception E){}
        try{
            The.flavorsWilds.remove(s);
        } catch (Exception E){}
        try{
            The.flavorsCity.remove(s);
        } catch (Exception E){}
    }
    
    public static void removeStory(String s){
        try{
            The.storiesGraveYard.remove(s);
        } catch (Exception E){}
        try{
            The.storiesWilds.remove(s);
        } catch (Exception E){}
        try{
            The.storiesCity.remove(s);
        } catch (Exception E){}
        try{
            The.storyProgress.remove(s);
        } catch (Exception E){}
    }
    
    public static void loadAllStories(){
        allStories = new HashMap<>();
        
        //THESE STORIES ARE AT THE START
        Story add = new CombatIntroStory();
        if(The.first){
            putStory(add.name,GRAVEYARD); 
        }
        allStories.put(add.name,add);
        
        add = new TutorialStory();
        /*// [Temporarily disabled for testing when greyed out]
        if(The.first){
            putStory(add.name,WILDS); 
            The.animalChoices.add("Pig");
            The.animalFriendliness.put("Pig",1.0);
        }
        //*/
        allStories.put(add.name,add);
        
        add = new TestingStory();
        if(The.first){
            putStory(add.name,WILDS); 
            The.animalChoices.add("Pig");
            The.animalFriendliness.put("Pig",1.0);
        }
        allStories.put(add.name,add);
        
        
        //THESE STORIES ARE UNLOCKED LATER
        
        add = new CatMouseStory();
        allStories.put(add.name, add);
        
        add = new DuckCityStory();
        allStories.put(add.name,add);
        
        //ALSO LOAD FLAVORS HERE
        
        allFlavors = new HashMap<>();
        
        Flavor fadd = new ZombieFlavor();
        allFlavors.put("ZombieFlavor",fadd);
        
        fadd = new CityDuckFlavor();
        allFlavors.put("City Duck Flavor",fadd);
        
        fadd = new TutorialEndPigs();
        allFlavors.put("Tutorial Pigs", fadd);
        
        //No longer the first, no longer load these.
        The.first = false;
    }
}
