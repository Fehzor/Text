/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.Preset;

import java.awt.event.KeyEvent;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.Frame.TextListener;
import text.Images.TextImage;
import text.Utility.ImageLoader;
import text.Tools.Tool;

/**
 *
 * @author FF6EB4
 */
public class DirectionalTool extends Tool{
        public int COOLDOWN = 5;
        public int cool = 0;
    
        public DirectionalTool(){
            super((TextImage)(ImageLoader.loadImage("teleporter_held.txt")),"Directional Tool");
            this.name = "Directional Tool";
            this.slot = DIRECTIONAL;
        }
        
        public boolean act(){
        if(!held)return true;
        if(cool < COOLDOWN){
            cool+=1;
            return true;
        }
            
        if(TextListener.isHeld(KeyEvent.VK_J)){
            if(Player.The.x > 5){
                Player.The.x=-5;
                cool = 0;
            }
        }
        if(TextListener.isHeld(KeyEvent.VK_K)){
            if(Player.The.y < TextDisplay.SCREEN_SIZE_Y-5){
                Player.The.y=TextDisplay.SCREEN_SIZE_Y+5;
                cool = 0;
            }
        }
        if(TextListener.isHeld(KeyEvent.VK_L)){
            if(Player.The.x < TextDisplay.SCREEN_SIZE_X-5){
                Player.The.x=TextDisplay.SCREEN_SIZE_X+5;
                cool=0;
            }
        }
        if(TextListener.isHeld(KeyEvent.VK_I)){
            if(Player.The.y > 5){
                Player.The.y=-5;
                cool=0;
            }
        }
        
        return false;
    }

    public DirectionalTool clone(){
        DirectionalTool ret =  new DirectionalTool();
        ret.image = this.image.clone();
        return ret;
    }
    
    public String toString(){
        return name;
    }
}
