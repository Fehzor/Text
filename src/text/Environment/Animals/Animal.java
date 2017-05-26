/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment.Animals;

import text.Utility.ImageBuilder;
import text.WorldFrame.World;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.awt.Point;
import text.Actors.*;
import text.Actors.Messages.Option;
import text.Actors.NPC.NPCBehaviourWalk;
import text.Images.*;
import text.Utility.*;
import text.WorldFrame.*;
import text.Behaviour.*;
import static text.Environment.Animals.AnimalTemplate.animalEnvironments;
import text.Environment.Environment;
import text.Inventory.Resource;
import text.Utility.Diction.LiteralDictionary;



/**
 *
 * @author FF6EB4
 */
public class Animal extends Actor implements Serializable, Convertable{
    public static Random oRan = new Random();
    
    public String adjective;
    
    public Behaviour behav;
    
    Environment E;
    
    public Nest home;
    
    public boolean moving = false;
    
    String dialogue = "";
    
    public Animal(){
        
    }
    
    public Animal(Room start){
        double d = Progress.The.animalFriendliness.get(name);
        
        if(oRan.nextInt(100) < d*100){
            behav= new NPCBehaviourWalk();
            
        } else {
            behav = new BehaviourFeral();
        }
        try{
            ArrayList<String> dia = AnimalTemplate.animalDialogue.get(name);
            
            String get = dia.get(oRan.nextInt(dia.size()));
            
            dialogue = get;
        } catch (Exception e){}
        super.current = start;
        
        if(AnimalTemplate.animalNames == null){
            AnimalTemplate.loadAnimals();
        }
        
        x = 50;
        y = 50;
        
        this.image = AnimalTemplate.generateSprite();
        
        try{
            ArrayList<String> dia = AnimalTemplate.animalDialogue.get(name);
            
            String get = dia.get(oRan.nextInt(dia.size()));
            
            dialogue = get;
        } catch (Exception e){}
    }
    
    public Animal(String name, World W, Room R){
        if(!Progress.The.animalFriendliness.containsKey(name)){
            Progress.The.animalFriendliness.put(name,(double)0);
        }
        double d = Progress.The.animalFriendliness.get(name);
        
        if(oRan.nextInt(100) < d*100){
            behav= new NPCBehaviourWalk();
            
        } else {
            behav = new BehaviourFeral();
        }
        
        try{
            ArrayList<String> dia = AnimalTemplate.animalDialogue.get(name);
            
            String get = dia.get(oRan.nextInt(dia.size()));
            
            dialogue = get;
        } catch (Exception e){}
        
        super.name = name;

        super.image = AnimalTemplate.generateSprite(name);
        
        ColorTuple theme = W.E.soil.randomColor();
        
        ImageBuilder.colorMergeImage((TextImageAnimated)this.image, theme);
        
        super.current = R;
        
        

    }
    
    private Animal(String name, Environment E){
        if(!Progress.The.animalFriendliness.containsKey(name)){
            Progress.The.animalFriendliness.put(name,(double)0);
        }
        double d = Progress.The.animalFriendliness.get(name);
        
        if(oRan.nextInt(100) < d*100){
            behav= new NPCBehaviourWalk();
            
        } else {
            behav = new BehaviourFeral();
        }
        
        try{
            ArrayList<String> dia = AnimalTemplate.animalDialogue.get(name);
            
            String get = dia.get(oRan.nextInt(dia.size()));
            
            dialogue = get;
        } catch (Exception e){}
        
        super.name = name;

        super.image = AnimalTemplate.generateSprite(name);
        
        ColorTuple theme = E.soil.randomColor();

        ImageBuilder.colorMergeImage((TextImageAnimated)this.image, theme);
        
        

    }
    
    public Animal clone(Environment E){
        Animal ret = new Animal(this.name,E);
        ret.image = this.image.clone();
        //ret.behav = this.behav;
        ret.adjective = this.adjective;
        
        
        
        return ret;
    }
    
    //Attribute all behaviours to the behaviour class assigned to this animal.
    public boolean act(){
        if(paused){
            return true;
        }
        behav.act(this);
        this.depth = y;
        
        //Deal with the sprite moving etc.
        if(moving){
            ((TextImageAnimated)image).go();
        } else {
            ((TextImageAnimated)image).stop();
            ((TextImageAnimated)image).resetFrame();
        }
        return true;
    }
    
    public boolean worldStep(World W){
        return behav.worldStep(this, W);
    }
    
    public void roomSwitch(){
        behav.roomSwitch(this);
    }
    
    public void worldSwitch(){
        behav.worldSwitch(this);
    }
    
    public void move(){
        moving = true;
    }
    
    public void stop(){
        moving = false;
    }
    
    public void outSideRoom(){
        behav.outSideRoom(this);
    }
    
    /**
     * Creates an animal species based on the environment.
     * @param E
     * @return 
     */
    public static Animal createAnimalSpecies(Environment E){
        if(AnimalTemplate.animalNames == null){
            AnimalTemplate.loadAnimals();
        }
        int start = oRan.nextInt(Progress.The.animalChoices.size());
        
        while(!check(Progress.The.animalChoices.get(start),E)){
            start = oRan.nextInt(Progress.The.animalChoices.size());
        }
        
        Animal ret = new Animal(Progress.The.animalChoices.get(start),E);
        ret.adjective = LiteralDictionary.getAdjective();
        
        return ret;
    }

    /**
     * Creates an animal species based on the environment.
     * @param E
     * @return 
     */
    public static Animal createAnimalSpecies(World W, Room R){
        if(AnimalTemplate.animalNames == null){
            AnimalTemplate.loadAnimals();
        }
        int start = oRan.nextInt(AnimalTemplate.animalNames.size());
        
        while(!check(AnimalTemplate.animalNames.get(start),W.E)){
            start = oRan.nextInt(AnimalTemplate.animalNames.size());
        }
        
        Animal ret = new Animal(AnimalTemplate.animalNames.get(start),W,R);
        ret.adjective = LiteralDictionary.getAdjective();
        
        
        
        return ret;
    }
        
    public static boolean check(String name, Environment E){
        Environment check = animalEnvironments.get(name);
        //System.out.println(animalEnvironments);
        double comp = E.compatible(check);
        double rand = Math.abs(oRan.nextGaussian()) * 0.35;
        //System.out.println(comp + " " + rand);
        
        if(rand < comp){
            return oRan.nextInt(1000) > 998;
        } else {
            return true;
        }
    }
    
    public ArrayList<Actor> pollOptions(){
        Option A = Option.cancel(this);
        
        Option A1 = Option.display(this, "Chat Up", dialogue);
        
        Option B = Option.convert(this,convert());
        
        Option C = Option.pickUp(this, new ColorTuple(Color.BLACK,Color.WHITE,'A'));
        
        Option D = Option.setDown(this);
        
        ArrayList<Actor> aList = new ArrayList<Actor>();
        aList.add(A);
        if(dialogue.length() > 0 && behav instanceof NPCBehaviourWalk){
            aList.add(A1);
        }
        aList.add(B);
        
        if(!held){
            aList.add(C);
        }
        if(held){
            aList.add(D);
        }
        
        return aList;
    }
    
    public String toString(){
        return adjective.charAt(0)+adjective.substring(1).toLowerCase()+" "+name;
    }
    
    
    public ArrayList<Resource> convert(){
        return LootGenerator.getLootClassic(this.toString(),image);
    }
}
