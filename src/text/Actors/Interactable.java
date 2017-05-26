package text.Actors;

import text.Images.TextImage;
import text.Utility.ColorTuple;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ImageLoader;
import java.util.*;
import java.awt.Color;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Inventory.*;
import text.Utility.*;
import text.Frame.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FF6EB4
 */
public class Interactable extends Actor implements Convertable{
    public String name;
    public ColorTuple icon;
    
    public Interactable(){
        super(0,0);
        this.dead = false;
    }
    
    public Interactable(String s){
        this();
        super.name = s;
        this.dead = false;
    }
    
    public Interactable(TextImage image){
        super(0,0,image);
        icon = new ColorTuple(Color.BLACK,Color.RED,'X');
        //depth = -1;
        this.depth = y;
        this.dead = false;
    }
    
    public Interactable(TextImage image, String name){
        this(image);
        super.name = name;
        this.name = name;
        this.dead = false;
    }
    
    public Interactable(int x, int y, TextImage image){
        super(x,y,image);
        icon = new ColorTuple(Color.BLACK,Color.RED,'X');
        //depth = -1;
        this.depth = y;
        this.dead = false;
    }
    
    public Interactable(int x, int y, TextImage image, String name){
        super(x,y,image,name);
        icon = new ColorTuple(Color.BLACK,Color.RED,'X');
        //depth = -1;
        this.name = name;
        this.image = image;
        this.x = x;
        this.y = y;
        this.depth = y;
    }
    
    public Interactable clone(){
        Interactable ret = new Interactable(this.x, this.y, this.image, this.name);
        return ret;
    }
        
    public boolean act(){
        this.depth = y;
        return false;
    }
    
    public void outSideRoom(){}
    
    public String toString(){
        return this.name;
    }
    
    public ArrayList<Actor> pollOptions(){
        Option A = Option.cancel(this);
        
        Option B = Option.convert(this,convert());
        
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
    
    public ArrayList<Resource> convert(){
        return LootGenerator.getLootClassic(this.toString(),image);
    }
    
}
