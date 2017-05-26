/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Hero;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Convertable;
import static text.Actors.Equipable.MAP;
import static text.Actors.Equipable.TOOL;
import text.Combat.Hero.HeroController;
import static text.Combat.Hero.HeroController.generatePracticeSet;
import text.Actors.Pickup;
import text.Actors.Player;
import text.Combat.CombatSystem;
import text.Frame.TextDisplay;
import text.Frame.TextListener;
import text.Images.TextImage;
import text.Utility.ImageLoader;
import text.Inventory.Resource;
import text.Robits.Robit;
import text.Tools.Tool;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class Instrument extends Tool{
    
    public CombatSystem myCombatSystem;
    
    public double difficulty = .5;
    public String left = "df";
    public String right = "jk";
    
    public Instrument(){
        super((TextImage)ImageLoader.loadImage("whistle.txt"),"Whistle");
        this.name = "Whistle";
        this.slot = INSTRUMENT;
        
        String letterset = generatePracticeSet(false,left,3);
        myCombatSystem = new HeroController(letterset,(float)0.35);
    }
        
    public boolean act(){
        if(!held)return true;
  
        String letterset = "";
        boolean hero = false;
        
        if(TextListener.firstPress(KeyEvent.VK_G)){
            hero = true;
            letterset += generatePracticeSet(difficulty > .7,left,(int)Math.ceil(difficulty*10)+3);
            try{
            this.activate();
            } catch (Exception E){}
        }
        
        if(TextListener.firstPress(KeyEvent.VK_H)){
            hero = true;
            letterset += generatePracticeSet(difficulty > .7,right,(int)Math.ceil(difficulty*10)+3);
            try{
            this.activate();
            } catch (Exception E){}
        }
        
        if(hero){
            myCombatSystem = new HeroController(letterset,(float)this.difficulty);
        
            Player.The.current.addActor(myCombatSystem);
            
            Player.combat = myCombatSystem;
            
        }
        return false;
    }

    public Instrument clone(){
        Instrument ret =  new Instrument();
        ret.image = this.image.clone();
        return ret;
    }
    
    public String toString(){
        return name;
    }
}
