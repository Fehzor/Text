/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import text.Actors.Interactable;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Messages.Prompt;
import text.Images.TextImageBasic;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class Floppy extends Interactable{
    public Floppy(int x, int y){
        super(x,y,loadImage(),"Floppy Disk");
        super.name = "Floppy Disk";
        System.out.println(name);
    }
    
    public static TextImageBasic loadImage(){
        ImageLoader.switchMap("LIGHTBRIGHT");
        return ImageLoader.loadImage("floppy.txt");
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        //ret.remove(1); //The floppy cannot be converted.
        
        Option save = Option.saveAnItem(this);
        
        Option load = Option.read(this);
        
        
        
        ret.add(0,load);
        
        ret.add(0,save);
        
        return ret;
    }
    
    public String toString(){
        return "Floppy";
    }
    
    public Floppy clone(){
        return new Floppy(5,5);
    }
}
