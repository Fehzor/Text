/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.Preset;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Convertable;
import text.Actors.Pickup;
import text.Actors.Player;
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
public class ConverterTool extends Tool{
        public ConverterTool(){
            super((TextImage)ImageLoader.loadImage("teleporter_held.txt"),"Conversion Tool");
            this.name = "Conversion Tool";
            this.slot = TOOL;
        }
        
        public boolean act(){
        if(!held)return true;
  
        if(TextListener.isHeld(KeyEvent.VK_SEMICOLON)){
            ArrayList<Pickup> pList = new ArrayList<>();
            for(Actor A : Player.The.current.getActors()){
                try{
                    ArrayList<Resource> give = ((Convertable)A).convert();
                    A.dead = true;
                    for(Resource R : give){
                        int xadd = oRan.nextInt(20) - 10;
                        int yadd = oRan.nextInt(10) - 5;
                        Pickup P = new Pickup(A.x + xadd,A.y + yadd,R.icon);
                        pList.add(P);
                    }
                } catch(Exception E){}
            }
            
            for(Pickup P : pList){
                Player.The.current.addActor(P);
            }
        }
        
        
        return false;
    }

    public ConverterTool clone(){
        ConverterTool ret =  new ConverterTool();
        ret.image = this.image.clone();
        return ret;
    }
    
    public String toString(){
        return name;
    }
}
