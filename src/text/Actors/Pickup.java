/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class Pickup extends Actor{
    
    ColorTuple loot;
    
    public Pickup(ColorTuple loot){
        super(0,0,new TextImageBasic(loot,true));
        this.loot = loot.clone();
    }
    
    public Pickup(int x, int y,ColorTuple loot){
        super(x,y,new TextImageBasic(loot,true));
        this.loot = loot.clone();
    }
    
    public boolean act(){
        try{
                    ArrayList<Actor> hits = current.HitScan.checkHit(x,y,0);
                    if( hits.size() > 1 ){
                        if(hits.contains(Player.The)){
                            this.dead = true;
                            Player.inv.put(loot);
                        }
                    }
                } catch (Exception E){System.err.println("Unexpected Error!");}
        
        
        return true;
    }
    
    public Pickup clone(){
        return new Pickup(this.loot);
    }
}
