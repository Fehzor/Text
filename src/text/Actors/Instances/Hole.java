/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import java.awt.Point;
import text.Utility.ImageLoader;
import text.Utility.ImageBuilder;
import text.WorldFrame.World;
import text.Actors.Interactable;
import java.io.Serializable;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.WorldFrame.*;
import text.Images.*;
import text.Utility.*;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.Templates.WorldBuilder;
import text.WorldFrame.Templates.WorldTemplate;
import text.WorldFrame.Worlds.CaveWorld;
import text.WorldFrame.Worlds.RoomWorld;

/**
 *
 * @author FF6EB4
 */
public class Hole extends Interactable implements Serializable{
    
    Option enter = new Option(this){
        public boolean act(){
            CaveWorld CW = (CaveWorld)Player.The.current.owner;
            CW.swapRooms();
            return true;
        }
        public String toString(){return "Enter";}
    };
    
    
    public Hole(){
        super(Player.The.x,Player.The.y,Hole.buildImage("Trapdoor"));
        this.name = "Trapdoor";
        
    }
    
    private static TextImage buildImage(String name){
        ImageLoader.switchMap("CITYSCALE");
        
        return ImageLoader.loadAnimated(name.toLowerCase()+"_open.txt",name.toLowerCase()+"_closed.txt");
        //return ImageLoader.loadAnimated("teleporter_frameA.txt","teleporter_frameB.txt");
    }
    
    private static TextImage colorImage(TextImage img,World W){
        ColorTuple merge = W.E.soil.averageColor();
        ImageBuilder.colorMergeImage((TextImageAnimated)img, merge);
        return img;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor>ret = new ArrayList<>();
        
        ret.add(enter);
        
        ret.add(Option.cancel(this));
            
        return ret;
    }
    
    public String toString(){
        
        return name;
    }
};
