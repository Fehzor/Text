/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import text.Utility.ImageLoader;
import text.Utility.ImageBuilder;
import text.WorldFrame.World;
import text.Actors.Interactable;
import java.io.Serializable;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.WorldFrame.*;
import text.Images.*;
import text.Utility.*;

/**
 *
 * @author FF6EB4
 */
public class Teleporter extends Interactable implements Serializable{
    public boolean open = true;
    public boolean handheld = true;
    ArrayList<Actor> contained;
    public int worldslots = 0; //How many worlds can this hold?? 1 or 3, usually.
    
    public Teleporter(int x, int y, boolean handheld){
        super(x,y,Teleporter.buildImage(handheld));
        this.handheld = handheld;
        if(!handheld){
            worldslots = 5;
            ((TextImageAnimated)this.image).go();
            ((TextImageAnimated)this.image).setFrameSkip(7);
        } else {
            worldslots = 3;
        }
        contained = new ArrayList<>();
    }
    
    public Teleporter(int x, int y, boolean handheld, World W){
        super(x,y,colorImage(Teleporter.buildImage(handheld),W));
        this.handheld = handheld;
        if(!handheld){
            worldslots = 5;
            ((TextImageAnimated)this.image).go();
            ((TextImageAnimated)this.image).setFrameSkip(7);
        } else {
            worldslots = 3;
        }
        contained = new ArrayList<>();
    }
    
    private static TextImage buildImage(boolean handheld){
        
        if(!handheld){
            ImageLoader.switchMap("GREYSCALE");
            return ImageLoader.loadAnimated("teleporter_frameA.txt","teleporter_frameB.txt");
        } else {
            ImageLoader.switchMap("LIGHTBRIGHT");
            return ImageLoader.loadImage("teleporter_held.txt");
        }
    }
    
    
    
    private static TextImage colorImage(TextImage img,World W){
        ColorTuple merge = W.E.soil.averageColor();
        ImageBuilder.colorMergeImage(img, merge);
        return img;
    }
    
    /**
     * Add a trap door to this tele
     * @param toAdd
     * The trapdoor to add
     * 
     * @return 
     * Whether it was added correctly.
     */
    public boolean addDoor(Door toAdd){
        if(contained.size() < this.worldslots){
            contained.add(toAdd);
            toAdd.stripped = true;
            toAdd.name = "World"+contained.size();
        } else {
            return false;
        }
        return true;
    }
    
    private void checkDeadDoors(){
        for(int i = contained.size()-1; i > 0; --i){
            if(contained.get(i).dead){
                contained.remove(i);
            }
        }
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor>ret = new ArrayList<>();
        
        this.checkDeadDoors();

        if(contained.size()<this.worldslots){
            ret.add(Option.attachTeleport(this));
        }
        
        if(held || !handheld){
            
            ret.addAll(this.contained);

            
        } else {
            return super.pollOptions();
        }
        return ret;
    }
    
    public String toString(){
        return "Teleporter";
    }
}
