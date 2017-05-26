/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment.Inanimate;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import text.Actors.Actor;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Environment.Environment;
import text.Utility.ImageLoader;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageBuilder;
import text.Utility.LootGenerator;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class Rock extends Interactable{
    public String adjective;
    
    public static ArrayList<TextImageBasic> imageList;
    public static ArrayList<TextImageBasic> tombImageList;
    
    public static int TYPE_ROCK = 0;
    public static int TYPE_TOMB = 1;
    
    public Rock(ColorTuple tint, Environment E){
        super("Rock");
        this.name = "Rock";
        this.dead = false;
        
        if(imageList == null){
            loadRocks();
        }
        
        int i = oRan.nextInt(imageList.size());
        
        this.image = imageList.get(i).clone();
        ColorTuple merge = E.soil.randomColor();
        ImageBuilder.colorMergeImage((TextImageBasic)this.image,merge);
        ImageBuilder.colorMergeImage((TextImageBasic)this.image,tint);
        
        this.adjective = LiteralDictionary.getAdjective();
        depth = y;
    }
    
    public Rock(ColorTuple tint){
        super("Rock");
        this.name = "Rock";
        this.dead = false;
        
        if(imageList == null){
            loadRocks();
        }
        
        int i = oRan.nextInt(imageList.size());
        
        this.image = imageList.get(i).clone();
        
        ImageBuilder.colorMergeImage((TextImageBasic)this.image,tint);
        
        this.adjective = LiteralDictionary.getAdjective();
        depth = y;
    }
    
    public Rock(ColorTuple tint,String name, int type,Environment E){
        super(name);
        this.name = name;
        this.dead = false;
        
        if(imageList == null){
            loadRocks();
        }
        
        int i = oRan.nextInt(imageList.size());
        this.image = imageList.get(i).clone();
        
        if(type == TYPE_TOMB){
            i = oRan.nextInt(tombImageList.size());
            this.image = tombImageList.get(i).clone();
        }
        
        ColorTuple merge = E.soil.randomColor();
        ImageBuilder.colorMergeImage((TextImageBasic)this.image,merge);
        ImageBuilder.colorMergeImage((TextImageBasic)this.image,tint);
        
        this.adjective = LiteralDictionary.getAdjective();
        depth = y;
    }
    
    public Rock(String name, int type,Environment E){
        super(name);
        this.name = name;
        this.dead = false;
        
        if(imageList == null){
            loadRocks();
        }
        
        int i = oRan.nextInt(imageList.size());
        this.image = imageList.get(i).clone();
        
        if(type == TYPE_TOMB){
            i = oRan.nextInt(tombImageList.size());
            this.image = tombImageList.get(i).clone();
        }
        
        ColorTuple merge = E.soil.randomColor();
        ImageBuilder.colorMergeImage((TextImageBasic)this.image,merge);
        
        this.adjective = LiteralDictionary.getAdjective();
        depth = y;
    }
    
    public Rock(String adj){
        super("Rock");
        this.name = "Rock";
        this.adjective = adj;
    }
    
    public Rock(){
        super("Rock");
        this.name = "Rock";
    }
    
    public Rock clone(){
        Rock ret = new Rock();
        ret.name = this.name;
        ret.image = this.image.clone();
        ret.adjective = this.adjective;
        ret.dead = false;
        ret.depth = this.depth;
        return ret;
    }
    
    public void loadRocks(){
        imageList = new ArrayList<>();
        tombImageList = new ArrayList<>();
        ImageLoader.switchMap("GREYSCALE");
        try{
            Scanner oScan = new Scanner(new File("rock_list.txt"));
            
            while(oScan.hasNextLine()){
                String location = oScan.next();
                TextImageBasic add = ImageLoader.loadImage(location);
                imageList.add(add);
            }
        } catch (Exception E){
            System.out.println("Something went wrong loading rock images!"+E);
        }
        try{
            Scanner oScan = new Scanner(new File("tomb_list.txt"));
            
            while(oScan.hasNextLine()){
                String location = oScan.next();
                TextImageBasic add = ImageLoader.loadImage(location);
                tombImageList.add(add);
            }
        } catch (Exception E){
            System.out.println("Something went wrong loading tombs!"+E);
        }
    }
    
    public String toString(){
        return adjective.charAt(0)+adjective.substring(1).toLowerCase()+" "+this.name;
    }
    
    public ArrayList<Actor> pollOptions(){
        Option A = Option.cancel(this);
        
        Option B = Option.convert(this,LootGenerator.getLootClassic(this.toString(),(TextImageBasic)image));
        
        Option C = Option.pickUp(this, icon);
        
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

        return aList;
    }
}
