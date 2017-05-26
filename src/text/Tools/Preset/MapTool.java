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
import static text.Actors.Equipable.TOOL;
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
public class MapTool extends Tool{
        public MapTool(){
            super((TextImage)ImageLoader.loadImage("teleporter_held.txt"),"Map");
            this.name = "Map";
            this.slot = MAP;
        }
        
        public boolean act(){
        if(!held)return true;
  
        if(TextListener.firstPress(KeyEvent.VK_M)){
            Player.current.owner.displayMap();
        }
        
        
        return false;
    }

    public MapTool clone(){
        MapTool ret =  new MapTool();
        ret.image = this.image.clone();
        return ret;
    }
    
    public String toString(){
        return name;
    }
}
