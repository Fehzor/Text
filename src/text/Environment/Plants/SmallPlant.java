/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment.Plants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import text.Actors.Actor;
import text.Actors.Convertable;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Environment.Environment;
import text.Images.TextImageBasic;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Inventory.Resource;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.LootGenerator;

/**
 *
 * @author FF6EB4
 */
public class SmallPlant extends Actor implements Convertable{
    
    //These are lists of the small plants by species name
    public static ArrayList<String> smallPlantNames;
    public static HashMap<String,SmallPlant> smallPlants; //default small plants
    public static HashMap<String,Environment> smallPlantEnvironments; 
    
    //A random object used for generation
    public static Random oRan = new Random();
    
    //How many small plants of this type are there on average in a room?
    public int ratio = 0;
    
    //What prefix is added to this small plant's name?
    public String adjective;
    
    //Create a blank small plant
    public SmallPlant(){
        super();
    }
    
    /**
     * Creates a blank small plant of name name
    */
    private SmallPlant(String name){
        super();
        this.name = name;
    }
    
    /**
    *What the plant does every frame; can be overridden
    * 
    * @return 
    * True if it's 'active', false if not.
    */
    public boolean act(){
        this.depth = this.y;
        return true;
    }
    
    /**
     * Builds a small plant species and returns it based on an environment.
     * 
     * @param E 
     * An environment class
     * 
     * @return
     * A small plant species
     */
    public static SmallPlant makeSmallPlant(Environment E){
        if(smallPlants == null){
            loadSmallPlants();
        }
        int a = oRan.nextInt(smallPlantNames.size());
        String name = smallPlantNames.get(a);
        
        while(!check(name,E)){
            a = oRan.nextInt(smallPlantNames.size());
            name = smallPlantNames.get(a);
        }
        SmallPlant SP = smallPlants.get(name).clone();
        
        ImageBuilder.colorMergeImage((TextImageBasic)SP.image, E.soil.randomColor());
        
        SP.adjective = LiteralDictionary.getAdjective();
        
        return SP;
    }
    
    /**
     * Construct a clone of a given small plant.
     * @param SP 
     */
    public SmallPlant(SmallPlant SP){
        this(SP.name);
        this.image = SP.image.clone();
        this.ratio = SP.ratio;
        this.adjective = SP.adjective;
    }
    
    /**
     * Clone a small plant, as a method.
     * 
     * @return 
     * A clone of this small plant
     */
    public SmallPlant clone(){
        SmallPlant SP = new SmallPlant(this);
        return SP;
    }
    

    /**
     * Loads small plant information from the file small_plant_info.txt
     * 
     * Plant species are stored as follows:
     * Species_name [4 environment variables] population_density
     */
    public static void loadSmallPlants(){
        smallPlantNames = new ArrayList<>();
        smallPlants = new HashMap<>();
        smallPlantEnvironments = new HashMap<>();
        
        try{
            ImageLoader.switchMap("GREENSCALE"); //This is the color scale
            Scanner oScan = new Scanner(new File("small_plant_info.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                double a = oScan.nextDouble();
                double b = oScan.nextDouble();
                double c = oScan.nextDouble();
                double d = oScan.nextDouble();
                int e = oScan.nextInt();
                
                SmallPlant SP = new SmallPlant(name);
                SP.ratio = e;
                SP.image = ImageLoader.loadImage("small_plant_"+name+".txt");
                
                smallPlantNames.add(name);
                smallPlants.put(name,SP);
                smallPlantEnvironments.put(name, new Environment(a,b,c,d));
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading small plants!");
        }
    }
    
    /**
     * Returns whether a plant is acceptable for an environment.
     * @param name
     * @param E
     * @return 
     */
    public static boolean check(String name, Environment E){
        Environment check = smallPlantEnvironments.get(name);
        //System.out.println(E.waterLevel + " " + E.sunlight + " " + E.humidity + " " + E.temperature);
        double comp = E.compatible(check);
        double rand = Math.abs(oRan.nextGaussian()) * 0.35;
        
        
        
        if(rand < comp){
            //System.out.println(name + ": " + comp + " " + rand);
            return oRan.nextInt(1000) > 998;
        } else {
            //System.out.println(name + ": " + comp + " " + rand);
            return true;
        }
    }
    
    /**
     * These are the options that are pulled up whenever the player selects 
     * a small plant.
     * 
     * @return
     * A list containing-
     * Cancel = misclick
     * Convert = turn the plant into loot
     */
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ret.add(Option.cancel(this));
        
        ret.add(Option.convert(this, convert()));
        
        return ret;
    }
    
    /**
     * What loot the plant gives
     * 
     * @return 
     * A list of resources given by the plant.
     */
    public ArrayList<Resource> convert(){
        return LootGenerator.getLootClassic(this.toString(),(TextImageBasic)image);
    }
    
    /**
     * The plant's name and prefix.
     * 
     * @return
     * The plant's name and prefix.
     */
    public String toString(){
        return adjective.charAt(0)+adjective.substring(1).toLowerCase()+" "+name;
    }
}
