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
import text.Actors.Searchable;
import text.Environment.Environment;
import text.Images.TextImageBasic;
import text.Inventory.Inventory;
import text.Inventory.Physical;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Inventory.Resource;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.LootGenerator;

/**
 *
 * @author FF6EB4
 */
public class Tree extends Searchable implements Convertable{
    public static ArrayList<String> treeNames;
    public static HashMap<String,Tree> trees;
    public static HashMap<String,Environment> treeEnvironments;
    public static Random oRan = new Random();
    
    public int ratio = 0;
    
    public String adjective;
    
    public Tree(){
        super("Tree");
        this.dead = false;
        this.name = "Tree";
        super.name = "Tree";
    }
    
    private Tree(String name){
        super("Tree");
        this.name = "Tree";
        super.name = "Tree";
        this.dead = false;
    }
    
    public boolean act(){
        return true;
    }
    
    public static Tree makeTree(Environment E){
        if(trees == null){
            loadTrees();
        }
        int a = oRan.nextInt(treeNames.size());
        String name = treeNames.get(a);
        
        while(!check(name,E)){
            a = oRan.nextInt(treeNames.size());
            name = treeNames.get(a);
        }
        try{
        Tree SP = trees.get(name).clone();
        
        ImageBuilder.colorMergeImage((TextImageBasic)SP.image, E.soil.randomColor());
        
        SP.adjective = LiteralDictionary.getAdjective();
        
        return SP;
        
        } catch (Exception Escepton){
            System.out.println("HEY! LISTEN! ---->"+name);
        }
        
        return null;
    }
    
    public Tree(Tree SP){
        this.image = SP.image.clone();
        this.ratio = SP.ratio;
        this.adjective = SP.adjective;
    }
    
    public Tree clone(){
        Tree SP = new Tree(this.name);
        
        SP.image = this.image.clone();
        
        SP.ratio = this.ratio;
        SP.adjective = this.adjective;
        return SP;
    }
    
    public static void loadTrees(){
        treeNames = new ArrayList<>();
        trees = new HashMap<>();
        treeEnvironments = new HashMap<>();
        
        try{
            ImageLoader.switchMap("TREESCALE");
            Scanner oScan = new Scanner(new File("tree_info.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                double a = oScan.nextDouble();
                double b = oScan.nextDouble();
                double c = oScan.nextDouble();
                double d = oScan.nextDouble();
                int e = oScan.nextInt();
                
                Tree SP = new Tree(name);
                SP.ratio = e;
                SP.image = ImageLoader.loadImage("tree_"+name+".txt");
                
                treeNames.add(name);
                trees.put(name,SP);
                treeEnvironments.put(name, new Environment(a,b,c,d));
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading trees!");
        }
    }
    
    public static boolean check(String name, Environment E){
        Environment check = treeEnvironments.get(name);
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
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        ret.remove(2);//Cannot pick it up.
        
        return ret;
    }
    
    public ArrayList<Resource> convert(){
        return LootGenerator.getLootClassic(this.toString(),(TextImageBasic)image);
    }
    
    public String toString(){
        return adjective.charAt(0)+adjective.substring(1).toLowerCase()+" "+name;
    }
}
