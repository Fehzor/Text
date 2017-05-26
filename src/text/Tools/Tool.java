/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import text.Actors.*;
import text.Actors.Instances.*;
import text.Frame.TextListener;
import text.Images.TextImage;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;

/**
 *
 * @author FF6EB4
 */
public class Tool extends Equipable{
    public int activatorKey = KeyEvent.VK_SEMICOLON;
    
    public int animationTime = 10;
    
    public ArrayList<ToolEffect> effects;
    
    public TextImage animate;
    
    public double power = 1.0;
    
    public Tool(TextImage TIB, String name){
        super(TIB,name);
        this.name = name;
        effects = new ArrayList<>();
    }
    
    public Tool(TextImage TIB, TextImage animate, String name){
        super(TIB,name);
        this.name = name;
        this.animate = animate;
        effects = new ArrayList<>();
    }
    
    public boolean act(){
        if(!held)return true;
        if(TextListener.isHeld(activatorKey)){
            this.activate();
        }
        
        return false;
    }
    
    public Tool clone(){
        super.clone();
        return null;
    }
    
    public void activate(){
        
        this.animate.flip(Player.The.image.flipped());
        Player.The.image = this.animate;
        Player.The.paused = true;
        Player.The.animationTime = this.animationTime;
        
        ArrayList<Actor> aList = new ArrayList<>();
        
        for(int i = 0; i<effects.size(); ++i){
            try{
            ToolEffect TE = effects.get(i);
            ArrayList<Actor> toAdd = TE.getArea();
            for(Actor A : toAdd){
                if(!aList.contains(toAdd)){
                    aList.add(A);
                }
            }
            TE.applyEffect(aList, power);
            } catch (Exception E){}
            //If the player builds a crazy tool that doesn't work then it doesn't work
            
        }
    }
}
