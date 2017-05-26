/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Robits;

import text.Utility.ImageLoader;
import text.Environment.Animals.*;
import text.Utility.ImageBuilder;
import text.WorldFrame.World;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.awt.Point;
import text.Actors.*;
import text.Actors.Messages.Option;
import text.Images.*;
import text.Utility.*;
import text.WorldFrame.*;
import text.Behaviour.*;
import static text.Environment.Animals.AnimalTemplate.animalEnvironments;
import text.Environment.Environment;
import text.Inventory.Inventory;
import text.Utility.Diction.LiteralDictionary;



/**
 * @author FF6EB4
 */
public class Robit extends Actor implements Serializable{
    public static Random oRan = new Random();
    
    public String adjective;
    
    public Behaviour behav;

    public boolean moving = false;
    
    public Inventory inv = new Inventory(3);
     
    public Robit(){
        super.name = "Robit";
        this.name = "Robit";
        //behav = new Behaviour();
        
        //*/
        behav = new BehaviourAnd();
        ((BehaviourAnd)behav).addBehaviour(new BehaviourFollow());
        ((BehaviourAnd)behav).addBehaviour(new BehaviourConvertRocks());
        //*/
        
        //behav = new BehaviourFollow();
        
        x = 50;
        y = 50;
        
        this.image = RobitTemplate.robitSprites.get(oRan.nextInt(RobitTemplate.robitSprites.size()));
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
    

    public ArrayList<Actor> pollOptions(){
        Option A = Option.cancel(this);
        
        Option B = Option.convert(this,LootGenerator.getLootClassic(this.toString(),image));
        
        Option C = Option.pickUp(this, new ColorTuple(Color.BLACK,Color.WHITE,'A'));
        
        Option D = Option.setDown(this);
        
        ArrayList<Actor> aList = new ArrayList<Actor>();
        aList.add(A);
        aList.add(B);
        
        if(!held){
            aList.add(C);
        }
        if(held){
            aList.add(D);
        }
        
        Option E = Option.checkInventory(this, inv);
        Option F = Option.takeInventory(this, inv);
        
        aList.add(E);
        aList.add(F);
        
        return aList;
        
    }
    
    public String toString(){
        return name;
    }
    
}
